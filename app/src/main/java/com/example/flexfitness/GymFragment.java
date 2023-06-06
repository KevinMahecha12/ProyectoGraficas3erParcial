package com.example.flexfitness;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GymFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private SearchView searchView;
    private Button btnGurdarGym, btnMostrarRutaGym;
    private LatLng latlang;
    private String location;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GymFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GymFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GymFragment newInstance(String param1, String param2) {
        GymFragment fragment = new GymFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gym, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        obtenerLocacion();

        btnGurdarGym = view.findViewById(R.id.btnGuardarGym);
        btnMostrarRutaGym = view.findViewById(R.id.btnMostrarRutaGym);

        searchView = view.findViewById(R.id.svBuscarGym);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                location = searchView.getQuery().toString();
                List<Address> addressesList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder((getContext()));

                    try {
                        addressesList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addressesList.size() > 0) {
                        Address address = addressesList.get(0);
                        latlang = new LatLng(address.getLatitude(), address.getLongitude());

                        map.clear();
                        map.addMarker(new MarkerOptions().position(latlang).title(location));
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlang, 10));
                    } else {
                        Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                    }
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                map = googleMap;
            }
        });

        // Guardar locación

        btnGurdarGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Double> coordenadas = new HashMap<>();
                coordenadas.put("lat", latlang.latitude);
                coordenadas.put("lang", latlang.longitude);


                Map<String, Object> actualizaciones = new HashMap<>();
                actualizaciones.put("latLng", coordenadas);
                actualizaciones.put("gimnasio", location);

                DocumentReference usuariosRef = db.collection("usuarios").document(firebaseAuth.getCurrentUser().getUid());

                usuariosRef.update(actualizaciones).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Se ha guardado el gimnasio correctamente.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "No se pudo guardar el gimnasio, intentelo más tarde.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnMostrarRutaGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verEnGooglemaps();
            }
        });

        return view;
    }

    private void obtenerLocacion() {
        DocumentReference usuariosRef = db.collection("usuarios").document(firebaseAuth.getCurrentUser().getUid());

        usuariosRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();

                            if (document.exists()) {

                                Map<String, Object> data = document.getData();
                                if (data != null) {

                                    Map<String, Object> latLngMap = (Map<String, Object>) data.get("latLng");
                                    if (latLngMap != null) {

                                        Double lat = (Double) latLngMap.get("lat");
                                        Double lng = (Double) latLngMap.get("lang");

                                        latlang = new LatLng(lat, lng);

                                        map.clear();
                                        map.addMarker(new MarkerOptions().position(latlang).title(data.get("gimnasio").toString()));
                                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlang, 10));
                                    }

                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "No existe el usuario.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void verEnGooglemaps() {
        double lat = latlang.latitude;
        double lng = latlang.longitude;

        Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + lat + "," + lng + "&travelmode=driving");

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }
}
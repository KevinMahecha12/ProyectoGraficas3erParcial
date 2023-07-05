package aplicacionfitness.directoriofitness.flexfitness;

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

import com.example.flexfitness.R;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
                // Verificar si el SearchView está vacío
                if (searchView.getQuery().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Primero introduzca algo en la barra de búsqueda!", Toast.LENGTH_SHORT).show();
                } else {
                    String lugar = searchView.getQuery().toString();

                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

                    try {
                        List<Address> addresses = geocoder.getFromLocationName(lugar, 1);

                        if (addresses != null && !addresses.isEmpty()) {
                            // El lugar existe en Google Maps
                            Address address = addresses.get(0);

                            // Obtén las coordenadas y realiza la actualización en la base de datos
                            double latitud = address.getLatitude();
                            double longitud = address.getLongitude();

                            Map<String, Double> coordenadas = new HashMap<>();
                            coordenadas.put("lat", latitud);
                            coordenadas.put("lang", longitud);

                            Map<String, Object> actualizaciones = new HashMap<>();
                            actualizaciones.put("latLng", coordenadas);
                            actualizaciones.put("gimnasio", lugar);

                            DocumentReference usuariosRef = db.collection("usuarios").document(firebaseAuth.getCurrentUser().getUid());

                            usuariosRef.update(actualizaciones).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getContext(), "Se ha guardado el gimnasio correctamente.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "No se pudo guardar el gimnasio, inténtelo más tarde.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            // El lugar no existe en Google Maps
                            Toast.makeText(getContext(), "El lugar no existe en Google Maps.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnMostrarRutaGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchView.getQuery().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Primero introduzca algo en la barra de búsqueda!", Toast.LENGTH_SHORT).show();
                } else {
                    String lugar = searchView.getQuery().toString();

                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

                    try {
                        List<Address> addresses = geocoder.getFromLocationName(lugar, 1);

                        if (addresses != null && !addresses.isEmpty()) {
                            // El lugar existe en Google Maps
                            Address address = addresses.get(0);
                            double latitud = address.getLatitude();
                            double longitud = address.getLongitude();
                            LatLng latlang = new LatLng(latitud, longitud);

                            // Llama al método verEnGooglemaps()
                            verEnGooglemaps(latlang);
                        } else {
                            // El lugar no existe en Google Maps
                            Toast.makeText(getContext(), "El lugar no existe en Google Maps.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

    public void verEnGooglemaps(LatLng latlang) {
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

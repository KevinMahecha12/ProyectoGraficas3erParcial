package com.example.flexfitness;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjerciciosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjerciciosFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference docRutinas = db.collection("rutinas");

    Calendar calendar = Calendar.getInstance();
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    TextView txtDia, txtToca;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EjerciciosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjerciciosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjerciciosFragment newInstance(String param1, String param2) {
        EjerciciosFragment fragment = new EjerciciosFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        txtDia = root.findViewById(R.id.txtDia);
        txtToca = root.findViewById(R.id.txtToca);

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                txtDia.setText("¡Hoy es Lunes!");
                txtToca.setText("¡Dia de Pierna!");
                ejerciciosLunesViernes(root);
                break;
            case Calendar.TUESDAY:
                txtDia.setText("¡Hoy es Martes!");
                txtToca.setText("¡Dia de Hombro y Tricep!");
                //Aqui definimos los ejercicios
                break;
            case Calendar.WEDNESDAY:
                txtDia.setText("¡Hoy es Miércoles!");
                txtToca.setText("¡Dia de Espalda!");
                //Aqui definimos los ejercicios
                ejerciciosMiercoles(root);
                break;
            case Calendar.THURSDAY:
                txtDia.setText("¡Hoy es Jueves!");
                txtToca.setText("¡Dia de Pecho y Bicep!");
                //Aqui definimos los ejercicios
                break;
            case Calendar.FRIDAY:
                txtDia.setText("¡Hoy es Viernes!");
                txtToca.setText("¡Dia de Pierna!");
                //Aqui definimos los ejercicios
                ejerciciosLunesViernes(root);
                break;
            case Calendar.SATURDAY:
                txtDia.setText("¡Hoy es Sábado!");
                txtToca.setText("¡Dia de Descanso!");
                //Aqui definimos los ejercicios
                break;
            case Calendar.SUNDAY:
                txtDia.setText("¡Hoy es Domingo!");
                txtToca.setText("¡Dia de Descanso!");
                break;
            default:
                txtDia.setText("¡Hoy es Día no válido!");
                break;
        }//switch dia de la semana



        return root;
    }//onCreateView

    public void ejerciciosLunesViernes(View root){
        DocumentReference docRef = docRutinas.document("espalda");

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ArrayList<ClaseEjercicio> arrayEjercicios = new ArrayList<>();
                RecyclerView recycler = root.findViewById(R.id.recycler);

                if (documentSnapshot.contains("ejercicios")) {
                    Map<String, Object> ejerciciosMap = (Map<String, Object>) documentSnapshot.getData().get("ejercicios");
                    List<String> ejerciciosList = new ArrayList<>(ejerciciosMap.keySet());

                    // Seleccionar aleatoriamente 3 ejercicios
                    Collections.shuffle(ejerciciosList);
                    List<String> ejerciciosAleatorios = ejerciciosList.subList(0, 4);

                    // Utilizar los ejercicios aleatorios como desees
                    for (String ejercicio : ejerciciosAleatorios) {
                        Map<String, Object> ejercicioMap = (Map<String, Object>) ejerciciosMap.get(ejercicio);
                        String video = (String) ejercicioMap.get("video");
                        String desc = (String) ejercicioMap.get("desc");

                        //ClaseEjercicio ej = new ClaseEjercicio(ejercicio, desc, video);
                        arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                        Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    //Log.d("TAG", "El documento no contiene el campo 'ejercicios'");
                    Toast.makeText(getContext(), "El documento no contiene el campo 'ejercicios'", Toast.LENGTH_SHORT).show();
                }

                for (ClaseEjercicio ejercicio : arrayEjercicios){
                    Toast.makeText(getContext(), "Ejercicio: " + ejercicio.titulo + " video: " + ejercicio.desc, Toast.LENGTH_SHORT).show();

                }

                ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                recycler.setAdapter(adaptador);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Log.d("TAG", "Error al obtener el documento: " + e.getMessage());
                Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }//ejerciciosLunes

    public void ejerciciosMartes(){

    }//ejerciciosMartes

    public void ejerciciosMiercoles(View root){
        DocumentReference docRef = docRutinas.document("espalda");

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ArrayList<ClaseEjercicio> arrayEjercicios = new ArrayList<>();
                RecyclerView recycler = root.findViewById(R.id.recycler);

                    if (documentSnapshot.contains("ejercicios")) {
                        Map<String, Object> ejerciciosMap = (Map<String, Object>) documentSnapshot.getData().get("ejercicios");
                        List<String> ejerciciosList = new ArrayList<>(ejerciciosMap.keySet());

                        // Seleccionar aleatoriamente 3 ejercicios
                        Collections.shuffle(ejerciciosList);
                        List<String> ejerciciosAleatorios = ejerciciosList.subList(0, 4);

                        // Utilizar los ejercicios aleatorios como desees
                        for (String ejercicio : ejerciciosAleatorios) {
                            Map<String, Object> ejercicioMap = (Map<String, Object>) ejerciciosMap.get(ejercicio);
                            String video = (String) ejercicioMap.get("video");
                            String desc = (String) ejercicioMap.get("desc");

                            //ClaseEjercicio ej = new ClaseEjercicio(ejercicio, desc, video);
                            arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                            Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //Log.d("TAG", "El documento no contiene el campo 'ejercicios'");
                        Toast.makeText(getContext(), "El documento no contiene el campo 'ejercicios'", Toast.LENGTH_SHORT).show();
                    }

                for (ClaseEjercicio ejercicio : arrayEjercicios){
                    Toast.makeText(getContext(), "Ejercicio: " + ejercicio.titulo + " video: " + ejercicio.desc, Toast.LENGTH_SHORT).show();

                }

                ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                recycler.setAdapter(adaptador);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Log.d("TAG", "Error al obtener el documento: " + e.getMessage());
                Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }//ejerciciosMiercoles

    public void ejerciciosJueves(){

    }//ejerciciosJueves

    public void ejerciciosViernes(){

    }//ejerciciosViernes

}//EjerciciosFragment
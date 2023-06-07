package com.example.flexfitness;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    private int diaAlarma = 0;
    private int mesAlarma = 0;
    private int anoAlarma = 0;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String uid = firebaseAuth.getCurrentUser().getUid();
    DocumentReference docRef = db.collection("usuarios").document(uid);

    RadioButton rbMensual, rbAnual;
    Button btnFecha, btnRegistrarMembresia, btnLogout, btnBorrar;
    LinearLayout nuevaMembresia, membresiaExistente;
    EditText edtFechaInicio, edtFechaFinal;
    TextView txtEstatus;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
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
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        btnFecha = root.findViewById(R.id.btnFecha);
        btnRegistrarMembresia = root.findViewById(R.id.btnGuardarMembresia);
        btnLogout = root.findViewById(R.id.btnLogout);
        btnBorrar = root.findViewById(R.id.btnBorrarMembresia);
        rbAnual = root.findViewById(R.id.rbAnual);
        rbMensual = root.findViewById(R.id.rbMensual);
        nuevaMembresia = root.findViewById(R.id.layoutNuevaMembresia);
        membresiaExistente = root.findViewById(R.id.layoutMembresiaExistente);
        edtFechaInicio = root.findViewById(R.id.edtFechaInicio);
        edtFechaFinal = root.findViewById(R.id.edtFechaFin);
        txtEstatus = root.findViewById(R.id.txtEstatusMembresia);

        nuevaMembresia.setVisibility(View.INVISIBLE);
        membresiaExistente.setVisibility(View.INVISIBLE);

        existeMembresia();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFechaMembresia();
            }
        });

        btnRegistrarMembresia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarMembresia();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarMembresia();
            }
        });

        return root;
    }

    public void borrarMembresia(){
        Map<String, Object> membresiaMap = new HashMap<>();

        docRef.update("membresia", membresiaMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Actualización exitosa
                        Toast.makeText(getContext(), "Membresia Borrada Correctamente", Toast.LENGTH_SHORT).show();
                        existeMembresia();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error al actualizar
                        Toast.makeText(getContext(), "PUTAMADE", Toast.LENGTH_SHORT).show();
                    }
                });
    }//borrarMembresia

    public void existeMembresia(){
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.contains("membresia.fechaInicio")) {
                    membresiaExistente.setVisibility(View.VISIBLE);
                    nuevaMembresia.setVisibility(View.INVISIBLE);

                    Log.d("FirestoreData", documentSnapshot.getData().toString());

                    Map<String, Object> membresia = documentSnapshot.getData();
                    Map<String, Object> fechas = (Map<String, Object>) membresia.get("membresia");

                    Timestamp fechaInicioTimestamp = (Timestamp) fechas.get("fechaInicio");
                    Timestamp fechaFinalTimestamp = (Timestamp) fechas.get("fechaFinal");

                    // Convertir los timestamps a objetos Date
                    Date fechaInicio = fechaInicioTimestamp.toDate();
                    Date fechaFinal = fechaFinalTimestamp.toDate();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String fechaFormateadaInicio = dateFormat.format(fechaInicio);
                    String fechaFormateadaFinal = dateFormat.format(fechaFinal);

                    edtFechaInicio.setText("Inicio: " + fechaFormateadaInicio);
                    edtFechaFinal.setText("Final: " + fechaFormateadaFinal);
                    txtEstatus.setText("Aqui estan los datos registrados de tu membresía:");

                } else {
                    nuevaMembresia.setVisibility(View.VISIBLE);
                    membresiaExistente.setVisibility(View.INVISIBLE);
                    txtEstatus.setText("¡Parece que aún no has registrado tu membresía!");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Algo salio mal en ExisteMembresia", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setFechaMembresia(){
        //Instancia para calendario
        Calendar horarioHoy = Calendar.getInstance();
        //Obtener los valores actuales del sistema
        int anioActual = horarioHoy.get(Calendar.YEAR);
        int mesActual = horarioHoy.get(Calendar.MONTH);
        int diaActual = horarioHoy.get(Calendar.DAY_OF_MONTH);

        //Fecha para elegir la cita
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                anoAlarma = i;
                mesAlarma = i1;
                diaAlarma = i2;
            }
        }, anioActual, mesActual, diaActual);
        datePickerDialog.setTitle("Fecha de Cita");
        datePickerDialog.show();
    }//setFechaAlarma

    public void registrarMembresia(){
        String tipoMembresia = "";

        //Toast.makeText((Context) this, (CharSequence) rbAnual, Toast.LENGTH_SHORT).show();

        if(diaAlarma == 0 || mesAlarma == 0 || anoAlarma == 0){
            Toast.makeText(getContext(), "Seleccione la fecha en la que inició el periodo de su membresía.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(rbAnual.isChecked()){
            tipoMembresia = "Anual";
        }else if(rbMensual.isChecked()){
            tipoMembresia = "Mensual";
        }else{
            Toast.makeText(getContext(), "Seleccione el tipo de membresia que registrará.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Fecha de Inicio
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, anoAlarma);
        calendar.set(Calendar.MONTH, mesAlarma); // Los meses comienzan desde 0 (enero = 0)
        calendar.set(Calendar.DAY_OF_MONTH, diaAlarma);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date fechaInicio = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaFormateadaInicio = dateFormat.format(fechaInicio);

        //Fecha de fin
        int tempoAno = anoAlarma;
        int tempoMes = mesAlarma;

        if(tipoMembresia == "Anual"){
            tempoAno += 1;
        }

        if(tipoMembresia == "Mensual"){
            if (mesAlarma == 11){
                tempoMes = 0;
            }else{
                tempoMes+=1;
            }
        }//tipoMembresia

        calendar.set(Calendar.YEAR, tempoAno);
        calendar.set(Calendar.MONTH, tempoMes); // Los meses comienzan desde 0 (enero = 0)
        calendar.set(Calendar.DAY_OF_MONTH, diaAlarma);

        Date fechaFinal = calendar.getTime();
        String fechaFormateadaFinal = dateFormat.format(fechaFinal);

        //Empieza la firestoreada

        Map<String, Object> membresiaMap = new HashMap<>();
        membresiaMap.put("fechaFinal", fechaFinal);
        membresiaMap.put("fechaInicio", fechaInicio);
        membresiaMap.put("tipo", tipoMembresia);

                docRef.update("membresia", membresiaMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Actualización exitosa
                        Toast.makeText(getContext(), "Membresia Registrada Correctamente", Toast.LENGTH_SHORT).show();
                        existeMembresia();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error al actualizar
                        Toast.makeText(getContext(), "PUTAMADE", Toast.LENGTH_SHORT).show();
                    }
                });

    }//registrarMembresia

    public void logOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }


}//PerfilFragment
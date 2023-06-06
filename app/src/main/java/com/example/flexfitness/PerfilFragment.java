package com.example.flexfitness;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    private int diaAlarma = 0;
    private int mesAlarma = 0;
    private int anoAlarma = 0;

    RadioButton rbMensual, rbAnual;
    Button btnFecha, btnRegistrarMembresia;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        btnFecha = root.findViewById(R.id.btnFecha);
        btnRegistrarMembresia = root.findViewById(R.id.btnGuardarMembresia);
        rbAnual = root.findViewById(R.id.rbAnual);
        rbMensual = root.findViewById(R.id.rbMensual);

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


        return root;
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

        Date fecha = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String fechaFormateadaInicio = dateFormat.format(fecha);

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

        fecha = calendar.getTime();
        String fechaFormateadaFinal = dateFormat.format(fecha);

        Toast.makeText(getContext(), "Registrado, fecha inicio: " + fechaFormateadaInicio + " fecha final: " + fechaFormateadaFinal, Toast.LENGTH_SHORT).show();

    }//registrarMembresia


}//PerfilFragment
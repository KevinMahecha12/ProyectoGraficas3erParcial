package com.example.flexfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.flexfitness.databinding.ActivityMainBinding;
import com.example.flexfitness.databinding.ActivityMenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.Time;
import java.util.Calendar;
import java.util.Timer;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding binding;

    private int diaAlarma = 0;
    private int mesAlarma = 0;
    private int anoAlarma = 0;

    RadioButton rbMensual, rbAnual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new PerfilFragment());

        //Fragment fragmentPerfil = getSupportFragmentManager().findFragmentById(R.id.)
        rbMensual = findViewById(R.id.rbMensual);
        rbAnual = findViewById(R.id.rbAnual);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.itemPerfil:
                    PerfilFragment pF = new PerfilFragment();
                    replaceFragment(new PerfilFragment());

                    break;

                case R.id.itemEjercicios:
                    replaceFragment(new EjerciciosFragment());
                    break;

                case R.id.itemGym:
                    replaceFragment(new GymFragment());
                    break;

                case R.id.itemTimer:
                    replaceFragment(new TimerFragment());
                    break;

                case R.id.itemLector:
                    replaceFragment(new LectorFragment());
                    break;

            }//switch

            return true;
        });

    }//onCreate

    public void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }//Fragment

   public void setFechaMembresia(View view){
        //Instancia para calendario
        Calendar horarioHoy = Calendar.getInstance();
        //Obtener los valores actuales del sistema
        int anioActual = horarioHoy.get(Calendar.YEAR);
        int mesActual = horarioHoy.get(Calendar.MONTH);
        int diaActual = horarioHoy.get(Calendar.DAY_OF_MONTH);

        //Fecha para elegir la cita
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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

    public void registrarMembresia(View view){
        String tipoMembresia = "";

        //Toast.makeText((Context) this, (CharSequence) rbAnual, Toast.LENGTH_SHORT).show();

        if(diaAlarma == 0 || mesAlarma == 0 || anoAlarma == 0){
            Toast.makeText(this, "Seleccione la fecha en la que inició el periodo de su membresía.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(rbAnual.isChecked()){
            tipoMembresia = "Anual";
        }else if(rbMensual.isChecked()){
            tipoMembresia = "Mensual";
        }else{
            Toast.makeText(this, "Seleccione el tipo de membresia que registrará.", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Registrado, tipo " + tipoMembresia, Toast.LENGTH_SHORT).show();

    }//registrarMembresia



}//MenuActivity
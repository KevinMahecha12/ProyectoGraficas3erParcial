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

}//MenuActivity
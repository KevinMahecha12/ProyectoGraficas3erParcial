package com.example.flexfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //AQUI SE LEERAN LAS SHARED PREFERENCES PARA MANDAR A LOGIN O MENU INDEPENDIENTEMENTE
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }//run
        };//tarea


        Timer tiempo = new Timer();
        tiempo.schedule(tarea,2000);

    }//onCreate

}//Main
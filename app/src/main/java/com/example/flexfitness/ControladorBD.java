package com.example.flexfitness;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ControladorBD extends SQLiteOpenHelper {


    public ControladorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase nombreBD) {

        String sqlPesos = "CREATE TABLE pesos ("
                + "id_maquina INT PRIMARY KEY,"
                + "nombre_maquina TEXT,"
                + "peso_registrado INT,"
                + "record_peso INT"
                + ")";

        String ejercicios = "CREATE TABLE ejercicios ("
                + "id INT PRIMARY KEY,"
                + "dia TEXT,"
                + "ejercicio TEXT,"
                + "descripcion TEXT,"
                + "video TEXT"
                + ")";

        try {
            nombreBD.execSQL(sqlPesos);
            nombreBD.execSQL(ejercicios);
        } catch (SQLException e) {
            Toast.makeText(null, "Error al crear la tabla 'pesos' O 'sqlEjercicios'.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase nombreBD, int oldVersion, int newVersion) {

    }
}

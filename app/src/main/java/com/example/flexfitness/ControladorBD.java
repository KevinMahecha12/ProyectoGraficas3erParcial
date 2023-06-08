package com.example.sqlite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ControladorBD extends SQLiteOpenHelper {


    public ControladorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase nombreBD) {

        // tabla parque
        String sqlParque = "CREATE TABLE parque ("
                + "id_parque INT PRIMARY KEY,"
                + "nombre TEXT,"
                + "ubicacion TEXT,"
                + "tamano REAL,"
                + "aforo_maximo INT"
                + ")";

        try {
            nombreBD.execSQL(sqlParque);
        } catch (SQLException e) {
            Toast.makeText(null, "Error al crear la tabla 'parque'.", Toast.LENGTH_SHORT).show();
        }

        // tabla guardabosques
        String sqlGuardabosques = "CREATE TABLE guardabosques ("
                + "id_guardabosque INT PRIMARY KEY,"
                + "nombre TEXT,"
                + "apellido TEXT,"
                + "edad INT,"
                + "experiencia INT"
                + ")";

        try {
            nombreBD.execSQL(sqlGuardabosques);
        } catch (SQLException e) {
            Toast.makeText(null, "Error al crear la tabla 'guardabosques'.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase nombreBD, int oldVersion, int newVersion) {

    }
}

package aplicacionfitness.directoriofitness.flexfitness;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flexfitness.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class EjerciciosFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference docRutinas = db.collection("rutinas");

    Calendar calendar = Calendar.getInstance();
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    TextView txtDia, txtToca;
    ControladorBD admin;

    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EjerciciosFragment() {
        // Required empty public constructor
    }

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

        admin = new ControladorBD(getContext(), "pesos.db", null, 1);
    }//onCreate

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        txtDia = root.findViewById(R.id.txtDia);
        txtToca = root.findViewById(R.id.txtToca);

        //Si estamos en otro dia, eliminar lo guardado
        eliminarEjerciciosDiaAnterior();

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                txtDia.setText("¡Hoy es Lunes!");
                txtToca.setText("¡Dia de Pierna!");
                ejerciciosLunes(root);
                break;
            case Calendar.TUESDAY:
                txtDia.setText("¡Hoy es Martes!");
                txtToca.setText("¡Dia de Hombro y Tricep!");
                //Aqui definimos los ejercicios
                ejerciciosMartes(root);
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
                ejerciciosJueves(root);
                break;
            case Calendar.FRIDAY:
                txtDia.setText("¡Hoy es Viernes!");
                txtToca.setText("¡Dia de Pierna!");
                //Aqui definimos los ejercicios
                ejerciciosViernes(root);
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

    public void eliminarEjerciciosDiaAnterior(){
        String dia = "";

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                dia = "Lunes";
                break;
            case Calendar.TUESDAY:
                dia = "Martes";
                break;
            case Calendar.WEDNESDAY:
                dia = "Miercoles";
                break;
            case Calendar.THURSDAY:
                dia = "Jueves";
                break;
            case Calendar.FRIDAY:
                dia = "Viernes";
                break;
            case Calendar.SATURDAY:
                dia = "Sabado";
                break;
            case Calendar.SUNDAY:
                dia = "Domingo";
                break;
            default:
                txtDia.setText("¡Hoy es Día no válido!");
                break;
        }//switch dia de la semana

        String ejercicios = "CREATE TABLE ejercicios ("
                + "id INT PRIMARY KEY,"
                + "dia TEXT,"
                + "ejercicio TEXT,"
                + "descripcion TEXT,"
                + "video TEXT"
                + ")";
        SQLiteDatabase dbw = admin.getWritableDatabase();
        try {
            dbw.execSQL(ejercicios);
        } catch (SQLException e) {

        }

        String selectQuery = "SELECT COUNT(*) FROM ejercicios WHERE dia = ?";
        String[] selectionArgs = { dia };

        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, selectionArgs);

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            if (count == 0) {
                String deleteQuery = "DELETE FROM ejercicios";
                db = admin.getReadableDatabase();
                db.execSQL(deleteQuery);
            }
        }//si no hay ningun registro

    }//eliminarEjerciciosDiasAnterior

    public void ejerciciosLunes(View root){
        DocumentReference docRef = docRutinas.document("pierna");
        ArrayList<ClaseEjercicio> arrayEjercicios = new ArrayList<>();
        RecyclerView recycler = root.findViewById(R.id.recycler);

        String countQuery = "SELECT COUNT(*) FROM ejercicios";
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            if (count == 0) {
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

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

                                SQLiteDatabase db = admin.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("dia", "Lunes");
                                values.put("ejercicio", ejercicio);
                                values.put("descripcion", desc);
                                values.put("video", video);

                                long resultado = db.insert("ejercicios", null, values);
                                if (resultado != -1) {
                                    // El registro se insertó correctamente
                                } else {
                                    Toast.makeText(getContext(), "No se guardo", Toast.LENGTH_SHORT).show();
                                }

                                db.close();

                                //Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //Log.d("TAG", "El documento no contiene el campo 'ejercicios'");
                            Toast.makeText(getContext(), "El documento no contiene el campo 'ejercicios'", Toast.LENGTH_SHORT).show();
                        }

                        ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                        recycler.setAdapter(adaptador);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    }//onSuccess
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                //IF NO ESTA VACÍA
                String selectQuery = "SELECT * FROM ejercicios";

                db = admin.getReadableDatabase();
                cursor = db.rawQuery(selectQuery, null);

                // Itera sobre el cursor para obtener los registros
                if (cursor.moveToFirst()) {
                    do {
                        // Lee los valores de las columnas según corresponda
                        @SuppressLint("Range") String ejercicio = cursor.getString(cursor.getColumnIndex("ejercicio")); // Reemplaza "columna1" con el nombre real de la columna
                        @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex("descripcion")); // Reemplaza "columna2" con el nombre real de la columna
                        @SuppressLint("Range") String video = cursor.getString(cursor.getColumnIndex("video"));

                        // Realiza las acciones correspondientes con los valores del registro
                        arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                    } while (cursor.moveToNext());
                }//if existen registros

                cursor.close();
                db.close();

                ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                recycler.setAdapter(adaptador);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }//else
        }//if move first

        cursor.close();

    }//ejerciciosLunes

    public void ejerciciosMartes(View root){
        DocumentReference docRefHombro = docRutinas.document("hombro");
        DocumentReference docRefTricep = docRutinas.document("tricep");
        ArrayList<ClaseEjercicio> arrayEjercicios = new ArrayList<>();
        RecyclerView recycler = root.findViewById(R.id.recycler);

        String countQuery = "SELECT COUNT(*) FROM ejercicios";
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            if (count == 0) {

                docRefHombro.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Map<String, Object> ejerciciosMap = (Map<String, Object>) documentSnapshot.getData().get("ejercicios");
                        List<String> ejerciciosList = new ArrayList<>(ejerciciosMap.keySet());

                        // Seleccionar aleatoriamente 3 ejercicios
                        Collections.shuffle(ejerciciosList);
                        List<String> ejerciciosAleatorios = ejerciciosList.subList(0, 3);

                        // Utilizar los ejercicios aleatorios como desees
                        for (String ejercicio : ejerciciosAleatorios) {
                            Map<String, Object> ejercicioMap = (Map<String, Object>) ejerciciosMap.get(ejercicio);
                            String video = (String) ejercicioMap.get("video");
                            String desc = (String) ejercicioMap.get("desc");

                            //ClaseEjercicio ej = new ClaseEjercicio(ejercicio, desc, video);
                            arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));
                            SQLiteDatabase db = admin.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("dia", "Martes");
                            values.put("ejercicio", ejercicio);
                            values.put("descripcion", desc);
                            values.put("video", video);

                            long resultado = db.insert("ejercicios", null, values);
                            if (resultado != -1) {
                                // El registro se insertó correctamente
                            } else {
                                Toast.makeText(getContext(), "No se guardo", Toast.LENGTH_SHORT).show();
                            }

                            db.close();

                            //Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                        }

                        ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                        recycler.setAdapter(adaptador);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    }//onSuccess
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.d("TAG", "Error al obtener el documento: " + e.getMessage());
                        Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//onFailure
                });

                docRefTricep.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Map<String, Object> ejerciciosMap = (Map<String, Object>) documentSnapshot.getData().get("ejercicios");
                        List<String> ejerciciosList = new ArrayList<>(ejerciciosMap.keySet());

                        // Seleccionar aleatoriamente 3 ejercicios
                        Collections.shuffle(ejerciciosList);
                        List<String> ejerciciosAleatorios = ejerciciosList.subList(0, 3);

                        // Utilizar los ejercicios aleatorios como desees
                        for (String ejercicio : ejerciciosAleatorios) {
                            Map<String, Object> ejercicioMap = (Map<String, Object>) ejerciciosMap.get(ejercicio);
                            String video = (String) ejercicioMap.get("video");
                            String desc = (String) ejercicioMap.get("desc");

                            //ClaseEjercicio ej = new ClaseEjercicio(ejercicio, desc, video);
                            arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                            SQLiteDatabase db = admin.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("dia", "Martes");
                            values.put("ejercicio", ejercicio);
                            values.put("descripcion", desc);
                            values.put("video", video);

                            long resultado = db.insert("ejercicios", null, values);
                            if (resultado != -1) {
                                // El registro se insertó correctamente
                            } else {
                                Toast.makeText(getContext(), "No se guardo", Toast.LENGTH_SHORT).show();
                            }

                            db.close();

                            //Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                        }

                        ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                        recycler.setAdapter(adaptador);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    }//onSuccess
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.d("TAG", "Error al obtener el documento: " + e.getMessage());
                        Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//onFailure
                });

            }else{

                //IF NO ESTA VACÍA
                String selectQuery = "SELECT * FROM ejercicios";

                db = admin.getReadableDatabase();
                cursor = db.rawQuery(selectQuery, null);

                // Itera sobre el cursor para obtener los registros
                if (cursor.moveToFirst()) {
                    do {
                        // Lee los valores de las columnas según corresponda
                        @SuppressLint("Range") String ejercicio = cursor.getString(cursor.getColumnIndex("ejercicio")); // Reemplaza "columna1" con el nombre real de la columna
                        @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex("descripcion")); // Reemplaza "columna2" con el nombre real de la columna
                        @SuppressLint("Range") String video = cursor.getString(cursor.getColumnIndex("video"));

                        // Realiza las acciones correspondientes con los valores del registro
                        arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                    } while (cursor.moveToNext());
                }//if existen registros

                cursor.close();
                db.close();

                ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                recycler.setAdapter(adaptador);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            }//else
        }//if move first

        cursor.close();

    }//ejerciciosMartes

    public void ejerciciosMiercoles(View root){
        DocumentReference docRef = docRutinas.document("espalda");
        ArrayList<ClaseEjercicio> arrayEjercicios = new ArrayList<>();
        RecyclerView recycler = root.findViewById(R.id.recycler);

        String countQuery = "SELECT COUNT(*) FROM ejercicios";
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            if (count == 0) {

                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

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
                                SQLiteDatabase db = admin.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("dia", "Miercoles");
                                values.put("ejercicio", ejercicio);
                                values.put("descripcion", desc);
                                values.put("video", video);

                                long resultado = db.insert("ejercicios", null, values);
                                if (resultado != -1) {
                                    // El registro se insertó correctamente
                                } else {
                                    Toast.makeText(getContext(), "No se guardo", Toast.LENGTH_SHORT).show();
                                }

                                db.close();

                                //Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                            }//for
                        } else {
                            //Log.d("TAG", "El documento no contiene el campo 'ejercicios'");
                            Toast.makeText(getContext(), "El documento no contiene el campo 'ejercicios'", Toast.LENGTH_SHORT).show();
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

            }else{
                //IF NO ESTA VACÍA
                String selectQuery = "SELECT * FROM ejercicios";

                db = admin.getReadableDatabase();
                cursor = db.rawQuery(selectQuery, null);

                // Itera sobre el cursor para obtener los registros
                if (cursor.moveToFirst()) {
                    do {
                        // Lee los valores de las columnas según corresponda
                        @SuppressLint("Range") String ejercicio = cursor.getString(cursor.getColumnIndex("ejercicio")); // Reemplaza "columna1" con el nombre real de la columna
                        @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex("descripcion")); // Reemplaza "columna2" con el nombre real de la columna
                        @SuppressLint("Range") String video = cursor.getString(cursor.getColumnIndex("video"));

                        // Realiza las acciones correspondientes con los valores del registro
                        arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                    } while (cursor.moveToNext());
                }//if existen registros

                cursor.close();
                db.close();

                ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                recycler.setAdapter(adaptador);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        }//if cursor move first

        cursor.close();

    }//ejerciciosMiercoles

    public void ejerciciosJueves(View root){
        DocumentReference docRefPecho = docRutinas.document("pecho");
        DocumentReference docRefBicep = docRutinas.document("bicep");
        ArrayList<ClaseEjercicio> arrayEjercicios = new ArrayList<>();
        RecyclerView recycler = root.findViewById(R.id.recycler);

        String countQuery = "SELECT COUNT(*) FROM ejercicios";
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            if (count == 0) {

                docRefPecho.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Map<String, Object> ejerciciosMap = (Map<String, Object>) documentSnapshot.getData().get("ejercicios");
                        List<String> ejerciciosList = new ArrayList<>(ejerciciosMap.keySet());

                        // Seleccionar aleatoriamente 3 ejercicios
                        Collections.shuffle(ejerciciosList);
                        List<String> ejerciciosAleatorios = ejerciciosList.subList(0, 3);

                        // Utilizar los ejercicios aleatorios como desees
                        for (String ejercicio : ejerciciosAleatorios) {
                            Map<String, Object> ejercicioMap = (Map<String, Object>) ejerciciosMap.get(ejercicio);
                            String video = (String) ejercicioMap.get("video");
                            String desc = (String) ejercicioMap.get("desc");

                            //ClaseEjercicio ej = new ClaseEjercicio(ejercicio, desc, video);
                            arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                            SQLiteDatabase db = admin.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("dia", "Jueves");
                            values.put("ejercicio", ejercicio);
                            values.put("descripcion", desc);
                            values.put("video", video);

                            long resultado = db.insert("ejercicios", null, values);
                            if (resultado != -1) {
                                // El registro se insertó correctamente
                            } else {
                                Toast.makeText(getContext(), "No se guardo", Toast.LENGTH_SHORT).show();
                            }

                            db.close();

                            //Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                        }//for

                        ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                        recycler.setAdapter(adaptador);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    }//onSuccess
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//onFailure
                });

                docRefBicep.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Map<String, Object> ejerciciosMap = (Map<String, Object>) documentSnapshot.getData().get("ejercicios");
                        List<String> ejerciciosList = new ArrayList<>(ejerciciosMap.keySet());

                        // Seleccionar aleatoriamente 3 ejercicios
                        Collections.shuffle(ejerciciosList);
                        List<String> ejerciciosAleatorios = ejerciciosList.subList(0, 3);

                        // Utilizar los ejercicios aleatorios como desees
                        for (String ejercicio : ejerciciosAleatorios) {
                            Map<String, Object> ejercicioMap = (Map<String, Object>) ejerciciosMap.get(ejercicio);
                            String video = (String) ejercicioMap.get("video");
                            String desc = (String) ejercicioMap.get("desc");

                            //ClaseEjercicio ej = new ClaseEjercicio(ejercicio, desc, video);
                            arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));
                            SQLiteDatabase db = admin.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("dia", "Jueves");
                            values.put("ejercicio", ejercicio);
                            values.put("descripcion", desc);
                            values.put("video", video);

                            long resultado = db.insert("ejercicios", null, values);
                            if (resultado != -1) {
                                // El registro se insertó correctamente
                            } else {
                                Toast.makeText(getContext(), "No se guardo", Toast.LENGTH_SHORT).show();
                            }

                            db.close();

                            //Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                        }//for

                        ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                        recycler.setAdapter(adaptador);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    }//onSuccess
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }//onFailure
                });

            } else {

                //IF NO ESTA VACÍA
                String selectQuery = "SELECT * FROM ejercicios";

                db = admin.getReadableDatabase();
                cursor = db.rawQuery(selectQuery, null);

                // Itera sobre el cursor para obtener los registros
                if (cursor.moveToFirst()) {
                    do {
                        // Lee los valores de las columnas según corresponda
                        @SuppressLint("Range") String ejercicio = cursor.getString(cursor.getColumnIndex("ejercicio")); // Reemplaza "columna1" con el nombre real de la columna
                        @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex("descripcion")); // Reemplaza "columna2" con el nombre real de la columna
                        @SuppressLint("Range") String video = cursor.getString(cursor.getColumnIndex("video"));

                        // Realiza las acciones correspondientes con los valores del registro
                        arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                    } while (cursor.moveToNext());
                }//if existen registros

                cursor.close();
                db.close();

                ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                recycler.setAdapter(adaptador);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            }//else

        }//IF cursor move to first

        cursor.close();

    }//ejerciciosJueves (La que esta bien)

    public void ejerciciosViernes(View root){
        DocumentReference docRef = docRutinas.document("pierna");
        ArrayList<ClaseEjercicio> arrayEjercicios = new ArrayList<>();
        RecyclerView recycler = root.findViewById(R.id.recycler);

        String countQuery = "SELECT COUNT(*) FROM ejercicios";
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            int count = cursor.getInt(0);
            if (count == 0) {
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

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

                                SQLiteDatabase db = admin.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("dia", "Viernes");
                                values.put("ejercicio", ejercicio);
                                values.put("descripcion", desc);
                                values.put("video", video);

                                long resultado = db.insert("ejercicios", null, values);
                                if (resultado != -1) {
                                    // El registro se insertó correctamente
                                } else {
                                    Toast.makeText(getContext(), "No se guardo", Toast.LENGTH_SHORT).show();
                                }

                                db.close();

                                //Toast.makeText(getContext(), "Ejercicio aleatorio: " + ejercicio + " video: " + desc, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            //Log.d("TAG", "El documento no contiene el campo 'ejercicios'");
                            Toast.makeText(getContext(), "El documento no contiene el campo 'ejercicios'", Toast.LENGTH_SHORT).show();
                        }

                        ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                        recycler.setAdapter(adaptador);
                        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                    }//onSuccess
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error al obtener el documento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                //IF NO ESTA VACÍA
                String selectQuery = "SELECT * FROM ejercicios";

                db = admin.getReadableDatabase();
                cursor = db.rawQuery(selectQuery, null);

                // Itera sobre el cursor para obtener los registros
                if (cursor.moveToFirst()) {
                    do {
                        // Lee los valores de las columnas según corresponda
                        @SuppressLint("Range") String ejercicio = cursor.getString(cursor.getColumnIndex("ejercicio")); // Reemplaza "columna1" con el nombre real de la columna
                        @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex("descripcion")); // Reemplaza "columna2" con el nombre real de la columna
                        @SuppressLint("Range") String video = cursor.getString(cursor.getColumnIndex("video"));

                        // Realiza las acciones correspondientes con los valores del registro
                        arrayEjercicios.add(new ClaseEjercicio(ejercicio, desc, video));

                    } while (cursor.moveToNext());
                }//if existen registros

                cursor.close();
                db.close();

                ReciclerViewAdaptador adaptador = new ReciclerViewAdaptador(getContext(), arrayEjercicios);
                recycler.setAdapter(adaptador);
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }//else
        }//if move first

        cursor.close();
    }//ejerciciosViernes


}//EjerciciosFragment
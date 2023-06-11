package aplicacionfitness.directoriofitness.flexfitness;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flexfitness.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LectorFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    EditText peso;
    TextView maquina,pesoregistrado;
    Button registrarPesoButton;

    private int codigoMaquinaSeleccionada = -1;
    private String nombreMaquinaSeleccionada = "";
    ControladorBD admin;
    public LectorFragment() {
        // Required empty public constructor
    }

    public static LectorFragment newInstance(String param1, String param2) {
        LectorFragment fragment = new LectorFragment();
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

        SQLiteDatabase bd = admin.getReadableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lector, container, false);
        peso = view.findViewById(R.id.PESO);
        maquina = view.findViewById(R.id.MAQUINA);
        registrarPesoButton = view.findViewById(R.id.REGISTRAR_PESO);
        pesoregistrado = view.findViewById(R.id.RECORD_PESO);
        registrarPesoButton.setEnabled(false);

        Button scanButton = view.findViewById(R.id.LEER_QR);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanearCodigoBarra();
            }
        });

        registrarPesoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarPeso();
            }
        });

        return view;
    }

    public void escanearCodigoBarra() {
        IntentIntegrator intentIntegrator = IntentIntegrator.forSupportFragment(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Lector - CDP");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getActivity(), "Lectura cancelada.", Toast.LENGTH_SHORT).show();
            } else {
                int codigoLeido = Integer.parseInt(intentResult.getContents());
                buscarMaquinaPorCodigo(codigoLeido);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void buscarMaquinaPorCodigo(int codigo) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference maquinasRef = db.collection("maquinas");

        maquinasRef.whereEqualTo("codigo", codigo)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
                                String nombreMaquina = documentSnapshot.getString("nombre");
                                codigoMaquinaSeleccionada = codigo;
                                nombreMaquinaSeleccionada = nombreMaquina;
                                maquina.setText(nombreMaquina);
                                maquina.setVisibility(View.VISIBLE);
                                registrarPesoButton.setEnabled(true);
                                mostrarPesoRegistrado();
                            } else {
                                maquina.setText("No se encontró la máquina");
                                maquina.setVisibility(View.VISIBLE);
                                registrarPesoButton.setEnabled(false);
                                limpiarRecordPeso();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error al buscar la máquina", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void limpiarRecordPeso() {
        SQLiteDatabase db = admin.getReadableDatabase();
        int pesoRegistrado = obtenerPesoRegistrado(codigoMaquinaSeleccionada, db);
        if (pesoRegistrado == -1) {
            pesoregistrado.setText("");
            pesoregistrado.setVisibility(View.GONE);
        }
    }



    private void mostrarPesoRegistrado() {
        if (codigoMaquinaSeleccionada != -1) {
            SQLiteDatabase db = admin.getReadableDatabase();
            int pesoRegistrado = obtenerPesoRegistrado(codigoMaquinaSeleccionada, db);
            if (pesoRegistrado != -1) {
                pesoregistrado.setText("Peso Registrado: " + pesoRegistrado);
                pesoregistrado.setVisibility(View.VISIBLE);
            } else {
                pesoregistrado.setText("");
                pesoregistrado.setVisibility(View.GONE);
            }
        }
    }


    private int obtenerPesoRegistrado(int codigo, SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT peso_registrado FROM pesos WHERE id_maquina = ?", new String[]{String.valueOf(codigo)});
        int pesoRegistrado = -1;
        if (cursor.moveToFirst()) {
            pesoRegistrado = cursor.getInt(0); // Utiliza el índice de columna directamente (0 en este caso)
        }
        cursor.close();
        return pesoRegistrado;
    }
    private void registrarPeso() {
        String pesoRegistrado = peso.getText().toString().trim();

        if (!pesoRegistrado.isEmpty() && codigoMaquinaSeleccionada != -1) {

            SQLiteDatabase db = admin.getWritableDatabase();

            int pesoNuevo = Integer.parseInt(pesoRegistrado);
            int recordActual = obtenerRecordActual(codigoMaquinaSeleccionada, db);

            ContentValues valores = new ContentValues();
            valores.put("id_maquina", codigoMaquinaSeleccionada);
            valores.put("nombre_maquina", nombreMaquinaSeleccionada);
            valores.put("peso_registrado", pesoNuevo);

            if (pesoNuevo > recordActual) {
                valores.put("record_peso", pesoNuevo);

                pesoregistrado.setText("!Felicidades!, es tu nuevo récord de peso/tiempo: " + pesoNuevo);
                pesoregistrado.setVisibility(View.VISIBLE);
            } else {
                valores.put("record_peso", recordActual);
            }

            int registrosActualizados = db.update("pesos", valores, "id_maquina = ?", new String[]{String.valueOf(codigoMaquinaSeleccionada)});

            if (registrosActualizados > 0) {
                Toast.makeText(getActivity(), "Peso/tiempo registrado exitosamente.", Toast.LENGTH_SHORT).show();
                // Aquí puedes realizar cualquier acción adicional después de actualizar el peso.
            } else {
                long resultado = db.insert("pesos", null, valores);
                if (resultado != -1) {
                    Toast.makeText(getActivity(), "Peso/tiempo registrado exitosamente.", Toast.LENGTH_SHORT).show();
                    // Aquí puedes realizar cualquier acción adicional después de registrar el peso.
                } else {
                    Toast.makeText(getActivity(), "Error al registrar el peso/tiempo.", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(getActivity(), "Ingrese el peso/tiempo y seleccione una máquina.", Toast.LENGTH_SHORT).show();
        }
    }



    private int obtenerRecordActual(int codigo, SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT record_peso FROM pesos WHERE id_maquina = ?", new String[]{String.valueOf(codigo)});
        int recordActual = 0;
        if (cursor.moveToFirst()) {
            recordActual = cursor.getInt(0); // Utiliza el índice de columna directamente (0 en este caso)
        }
        cursor.close();
        return recordActual;
    }


}


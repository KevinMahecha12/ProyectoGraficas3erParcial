package com.example.flexfitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    private EditText email, nombre, apellido, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        email = findViewById(R.id.EMAIL);
        nombre = findViewById(R.id.NOMBRE);
        apellido = findViewById(R.id.APELLIDO);
        contraseña = findViewById(R.id.CONTRASEÑA);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void registrar(View view) {
        String emailValue = email.getText().toString();
        String contraseñaValue = contraseña.getText().toString();
        String nombreValue = nombre.getText().toString();
        String apellidoValue = apellido.getText().toString();

        if (emailValue.isEmpty() || contraseñaValue.isEmpty() || nombreValue.isEmpty() || apellidoValue.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
            if (emailValue.isEmpty()) {
                email.setError("Campo obligatorio");
                email.requestFocus();
            }
            if (contraseñaValue.isEmpty()) {
                contraseña.setError("Campo obligatorio");
                contraseña.requestFocus();
            }
            if (nombreValue.isEmpty()) {
                nombre.setError("Campo obligatorio");
                nombre.requestFocus();
            }
            if (apellidoValue.isEmpty()) {
                apellido.setError("Campo obligatorio");
                apellido.requestFocus();
            }
            return;
        }

        if (contraseñaValue.length() < 6) {
            contraseña.setError("La contraseña debe tener al menos 6 caracteres");
            contraseña.requestFocus();
            return;
        }

        firebaseAuth.fetchSignInMethodsForEmail(emailValue)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<String> signInMethods = task.getResult().getSignInMethods();
                        if (signInMethods != null && signInMethods.size() > 0) {
                            Toast.makeText(getApplicationContext(), "El correo electrónico ya está registrado", Toast.LENGTH_SHORT).show();
                            email.setError("El correo electrónico ya está registrado");
                            email.requestFocus();
                        } else {
                            firebaseAuth.createUserWithEmailAndPassword(emailValue, contraseñaValue)
                                    .addOnCompleteListener(registerTask -> {
                                        if (registerTask.isSuccessful()) {
                                            FirebaseUser user = firebaseAuth.getCurrentUser();
                                            String userID = user.getUid();

                                            // Guardar información adicional en Firestore
                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            DocumentReference usuarioRef = db.collection("usuarios").document(userID);

                                            Map<String, Object> nuevoUsuario = new HashMap<>();
                                            nuevoUsuario.put("email", emailValue);
                                            nuevoUsuario.put("nombre", nombreValue);
                                            nuevoUsuario.put("apellido", apellidoValue);

                                            usuarioRef.set(nuevoUsuario)
                                                    .addOnSuccessListener(aVoid -> {
                                                        Toast.makeText(getApplicationContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();

                                                    })
                                                    .addOnFailureListener(e -> {
                                                        Toast.makeText(getApplicationContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                                        Log.e("RegistroActivity", "Error al registrar el usuario", e);
                                                        Toast.makeText(getApplicationContext(), "Error al registrar el usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    });
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                            Log.e("RegistroActivity", "Error al registrar el usuario", registerTask.getException());
                                            Toast.makeText(getApplicationContext(), "Error al registrar el usuario: " + registerTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Error al verificar el correo electrónico", Toast.LENGTH_SHORT).show();
                        Log.e("RegistroActivity", "Error al verificar el correo electrónico", task.getException());
                        Toast.makeText(getApplicationContext(), "Error al verificar el correo electrónico: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void volver(View view) {
        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}



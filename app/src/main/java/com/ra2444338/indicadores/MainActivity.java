package com.ra2444338.indicadores;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import   com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button loginButton, forgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();


        EditText emailEditText = findViewById(R.id.txt_login);
        EditText passwordEditText = findViewById(R.id.txt_senha);
        loginButton = findViewById(R.id.button_login);
        forgotPasswordButton = findViewById(R.id.button_forgot_password);


        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(email, password);
            }
        });


        forgotPasswordButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(MainActivity.this, "Digite seu email", Toast.LENGTH_SHORT).show();

            } else {
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this,
                                        "Email enviado para definição da nova senha", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Email não digitado.", Toast.LENGTH_SHORT).show();

                            }
                        });
            }

        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Login com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, Homepage.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, "Erro: Usuário ou senha inexistente", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
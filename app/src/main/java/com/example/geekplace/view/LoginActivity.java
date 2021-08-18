package com.example.geekplace.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geekplace.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    ///
        Button logar = findViewById(R.id.logar);
        Button cadastrar = findViewById(R.id.cadastrar);

        TextView email = findViewById(R.id.login_email);
        TextView senha = findViewById(R.id.login_senha);

        logar.setOnClickListener(v -> {
            String a = email.getText().toString();
            String b = senha.getText().toString();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            if (a.isEmpty() || b.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Digite seus dados", Toast.LENGTH_SHORT).show();
            } else {
                auth.signInWithEmailAndPassword(a, b).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Verifique seus dados", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        cadastrar.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, CadastroActivity.class)));
    }
}
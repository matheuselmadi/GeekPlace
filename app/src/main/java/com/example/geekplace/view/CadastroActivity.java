package com.example.geekplace.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geekplace.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button salvar = findViewById(R.id.cadastro_salvar);

        TextView email = findViewById(R.id.cadastro_login);
        TextView senha = findViewById(R.id.cadastro_senha);

        salvar.setOnClickListener(v -> {
            String a = email.getText().toString();
            String b = senha.getText().toString();
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            if (a.isEmpty() || b.isEmpty()) {
                Toast.makeText(CadastroActivity.this, "Digite seus dados", Toast.LENGTH_SHORT).show();
            } else {
                firebaseAuth.createUserWithEmailAndPassword(a, b).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                        Toast.makeText(CadastroActivity.this, "Cadrastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CadastroActivity.this, "Erro: "+task.getException(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
}
package com.example.geekplace.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geekplace.R;
import com.example.geekplace.model.Movie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdicionarFilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_filme);
        Button salvar = findViewById(R.id.filme_botao_salvar);

        TextView filme_nome = findViewById(R.id.filme_nome_editar);
        TextView filme_nota = findViewById(R.id.filme_nota_editar);
        TextView filme_descricao = findViewById(R.id.filme_descricao_editar);

        salvar.setOnClickListener(v -> {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            DocumentReference documentReference = firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid()).document();
            documentReference.set(new Movie( filme_nome.getText().toString(),
                    filme_nota.getText().toString(),
                    filme_descricao.getText().toString()
            ))
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(AdicionarFilmeActivity.this, "Filme adicionado com sucesso", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AdicionarFilmeActivity.this, "Erro ao adicionar o Filme", Toast.LENGTH_SHORT).show();
                        finish();
                    });
        });

    }
}
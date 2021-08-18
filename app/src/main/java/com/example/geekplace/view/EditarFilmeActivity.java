package com.example.geekplace.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.geekplace.R;
import com.example.geekplace.model.Movie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarFilmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_receita);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Movie movie = (Movie) bundle.getSerializable("movie");

        String doc = bundle.getSerializable("doc") + "";

        Button salvar = findViewById(R.id.filme_botao_salvar_editar);

        TextView nome = findViewById(R.id.filme_nome_editar);
        TextView nota = findViewById(R.id.filme_nota_editar);
        TextView filme_descricao_editar = findViewById(R.id.filme_descricao_editar);

        nome.setText(movie.getNome());
        nota.setText(movie.getNota());
        filme_descricao_editar.setText(movie.getDescricao());


        salvar.setOnClickListener(v -> {
            String a = nome.getText().toString();
            String b = nota.getText().toString();
            String c= filme_descricao_editar.getText().toString();


            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseFirestore.collection(firebaseAuth.getCurrentUser().getUid())
                    .document(doc)
                    .update("nome", a,
                            "nota", b,
                            "descricao", c
                            )
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(EditarFilmeActivity.this, "Filme editado com sucesso", Toast.LENGTH_SHORT).show();
                         finish();
                    })
                    .addOnFailureListener(ee -> {
                        Toast.makeText(EditarFilmeActivity.this, "Erro ao editar Filme", Toast.LENGTH_SHORT).show();
                        finish();
                    });
        });
    }
}
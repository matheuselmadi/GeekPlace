package com.example.geekplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.geekplace.R;
import com.example.geekplace.model.Movie;

public class MeusFilmesDetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_filmes_detalhes);

        Movie movie = (Movie) getIntent().getExtras().getSerializable("movie");
        TextView nome = findViewById(R.id.meu_filme_nome);
        TextView nota = findViewById(R.id.meu_filme_nota);
        TextView descricao = findViewById(R.id.meu_filme_descricao);

        nome.setText(movie.getNome());
        nota.setText(movie.getNota());
        descricao.setText(movie.getDescricao());
    }
}
package com.example.geekplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geekplace.model.ResultMovie;
import com.example.geekplace.model.ResultSerie;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailSerieActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_serie);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ResultSerie movie = (ResultSerie) bundle.getSerializable("serie");

        floatingActionButton = findViewById(R.id.floatactionbuttonSerie);
        floatingActionButton.setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content), "Adicionado aos favoritos", Snackbar.LENGTH_LONG).show();
            floatingActionButton.setImageResource(R.drawable.ic_baseline_favorite_24);
        });
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayoutSerie);
        collapsingToolbarLayout.setTitle(movie.getName());

        TextView textView = findViewById(R.id.second_activity_textViewSerie);
        ImageView imageView = findViewById(R.id.second_activity_imageViewSerie);

        RequestOptions options = new RequestOptions()
                .error(R.mipmap.ic_launcher_round);
        Glide.with(this).load("https://www.themoviedb.org/t/p/w220_and_h330_face"+movie.getBackdropPath()).apply(options).into(imageView);

        textView.setText(movie.getOverview());
    }
}
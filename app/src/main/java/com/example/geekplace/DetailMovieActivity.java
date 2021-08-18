package com.example.geekplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geekplace.model.ResultMovie;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class DetailMovieActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ResultMovie movie = (ResultMovie) bundle.getSerializable("movie");

        floatingActionButton = findViewById(R.id.floatactionbutton);
        floatingActionButton.setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content), "Adicionado aos favoritos", Snackbar.LENGTH_LONG).show();
            floatingActionButton.setImageResource(R.drawable.ic_baseline_favorite_24);
        });

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle(movie.getTitle());

        TextView textView = findViewById(R.id.second_activity_textView);
        ImageView imageView = findViewById(R.id.second_activity_imageView);
        RequestOptions options = new RequestOptions()
                .error(R.mipmap.ic_launcher_round);
        Glide.with(this).load("https://www.themoviedb.org/t/p/w220_and_h330_face"+movie.getBackdropPath()).apply(options).into(imageView);

        textView.setText(movie.getOverview());

    }
}
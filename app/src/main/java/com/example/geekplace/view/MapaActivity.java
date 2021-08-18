package com.example.geekplace.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.geekplace.R;

public class MapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        getSupportFragmentManager().beginTransaction().replace(R.id.mapaactivity, new MapsFragment()).commit();
    }
}
package com.example.geekplace.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.geekplace.R;
import com.example.geekplace.model.Movie;
import com.example.geekplace.viewmodel.MeusFilmesAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MeusFilmesFragment extends Fragment {
    MeusFilmesAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meus_filmes, container, false);
        RecyclerView rv = view.findViewById(R.id.recycler_view_meus_filmes);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String identificadorUsuario = firebaseAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection(identificadorUsuario);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new MeusFilmesAdapter(new FirestoreRecyclerOptions.Builder<Movie>()
                .setQuery(query, Movie.class)
                .build(), getContext(), rv, getActivity());


        rv.setLayoutManager(linearLayoutManager);
        rv.setHasFixedSize(true);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_meus_filmes);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AdicionarFilmeActivity.class);
            getContext().startActivity(intent);
        });

        return view;
    }
}
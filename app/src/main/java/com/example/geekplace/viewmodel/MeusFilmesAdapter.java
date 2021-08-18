package com.example.geekplace.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geekplace.R;
import com.example.geekplace.model.Movie;
import com.example.geekplace.view.EditarFilmeActivity;
import com.example.geekplace.view.MeusFilmesDetalhesActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class MeusFilmesAdapter extends FirestoreRecyclerAdapter<Movie, MeusFilmesAdapter.MyMoviesAdapterHolder> {

    private Context context;
    RecyclerView recyclerView;
    FirestoreRecyclerOptions<Movie> movies;
    FragmentActivity mainActivity;


    public MeusFilmesAdapter(FirestoreRecyclerOptions movies, Context context, RecyclerView recyclerView, FragmentActivity mainActivity) {
        super(movies);
        this.context = context;
        this.movies = movies;
        this.recyclerView = recyclerView;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onBindViewHolder(MyMoviesAdapterHolder holder, int position,Movie movie) {
        holder.nome.setText(movie.getNome() + "");

        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, MeusFilmesDetalhesActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movie);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

        holder.edit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditarFilmeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movie);
            DocumentSnapshot documentSnapshot = movies.getSnapshots().getSnapshot(holder.getAdapterPosition());
            bundle.putSerializable("doc", documentSnapshot.getId());
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

      holder.delete.setOnClickListener(v -> getSnapshots().getSnapshot(holder.getAdapterPosition()).getReference().delete());
    }

    @Override
    public MyMoviesAdapterHolder onCreateViewHolder(ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext())
                .inflate(R.layout.item_card_meu_filme, group, false);
        return new MyMoviesAdapterHolder(view);
    }

    public class MyMoviesAdapterHolder extends RecyclerView.ViewHolder {

        TextView nome;
        LinearLayout linearLayout;
        ImageView edit, delete;

        public MyMoviesAdapterHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.item_card_simples);
            nome = itemView.findViewById(R.id.nome);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}



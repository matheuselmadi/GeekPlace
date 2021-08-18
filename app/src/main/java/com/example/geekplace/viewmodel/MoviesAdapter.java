package com.example.geekplace.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geekplace.R;
import com.example.geekplace.DetailMovieActivity;
import com.example.geekplace.model.ResultMovie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private List<ResultMovie> movies;
    private LayoutInflater layoutInflater;
    private Context context;
    public MoviesAdapter(Context context, List<ResultMovie> movies) {
        this.movies = movies;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MoviesAdapter.ViewHolder holder, int position) {
        holder.nome.setText(movies.get(holder.getAdapterPosition()).getTitle());
        holder.datalancamento.setText(movies.get(holder.getAdapterPosition()).getReleaseDate());
        holder.popularity.setText(""+movies.get(holder.getAdapterPosition()).getVoteAverage());
        Glide.with(context).load("https://www.themoviedb.org/t/p/w220_and_h330_face"+ movies.get(holder.getAdapterPosition()).getBackdropPath()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movie", movies.get(holder.getAdapterPosition()));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView datalancamento;
        TextView popularity;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nome);
            datalancamento = itemView.findViewById(R.id.datalancamentomovie);
            popularity = itemView.findViewById(R.id.popularidademovie);
            imageView = itemView.findViewById(R.id.capamovie);
        }
    }
}

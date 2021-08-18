package com.example.geekplace.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geekplace.R;
import com.example.geekplace.model.ExampleMovie;
import com.example.geekplace.model.ResultMovie;
import com.example.geekplace.model.SimpleMovie;
import com.example.geekplace.viewmodel.MoviesAdapter;
import com.example.geekplace.viewmodel.movie.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewMovies);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<SimpleMovie> movies = new ArrayList<>();
        movies.add(new SimpleMovie("Batman", "Legal e bom"));
        movies.add(new SimpleMovie("Nemo", "Legal e ruim"));
        movies.add(new SimpleMovie("Vingadores", "Legal e brabu"));
        movies.add(new SimpleMovie("Branquelas", "Lendario"));
        movies.add(new SimpleMovie("Shreck", "Ta escrito errado"));
        movies.add(new SimpleMovie("Dota 2", "Epico"));

        getData();

        return view;
    }
    //System.out.println("https://www.themoviedb.org/t/p/w220_and_h330_face"+ resultMovie.getBackdropPath());
    //
    private final String API_KEY = "e79a859ffb63de1677a00f1b7665f552";
    private  final String LANGUAGE = "pt-BR";
    private  final String PAGE = "1";
    private List<ResultMovie> resultMovies = new ArrayList<>();
    private void getData(){


        Call<ExampleMovie> call = ApiClient
                .getInstance()
                .getApi()
                .getExample(API_KEY, LANGUAGE, PAGE);
        call.enqueue(new Callback<ExampleMovie>() {
            @Override
            public void onResponse(Call<ExampleMovie> call, Response<ExampleMovie> response) {
                if(response.isSuccessful() && response.body().getResults() !=null){
                    resultMovies = response.body().getResults();
                        MoviesAdapter moviesAdapter = new MoviesAdapter(getActivity(), resultMovies);
                        recyclerView.setAdapter(moviesAdapter);
                }
            }

            @Override
            public void onFailure(Call<ExampleMovie> call, Throwable t) {

            }
        });
    }
}
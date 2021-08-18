package com.example.geekplace.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geekplace.R;
import com.example.geekplace.model.ExampleSerie;
import com.example.geekplace.model.ResultSerie;
import com.example.geekplace.model.SimpleMovie;
import com.example.geekplace.viewmodel.MoviesAdapter;
import com.example.geekplace.viewmodel.SeriesAdapter;
import com.example.geekplace.viewmodel.serie.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewSeries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<SimpleMovie> movies = new ArrayList<>();
        movies.add(new SimpleMovie("Batman", "Legal e bom"));
        movies.add(new SimpleMovie("Nemo", "Legal e ruim"));
        movies.add(new SimpleMovie("Vingadores", "Legal e brabu"));
        movies.add(new SimpleMovie("Branquelas", "Lendario"));
        movies.add(new SimpleMovie("Shreck", "Ta escrito errado"));
        movies.add(new SimpleMovie("Dota 2", "Epico"));

        //MoviesAdapter moviesAdapter = new MoviesAdapter(getActivity(), movies);
        //recyclerView.setAdapter(moviesAdapter);
        getData();

        return view;
    }
    private static final String API_KEY = "e79a859ffb63de1677a00f1b7665f552";
    private static final String LANGUAGE = "pt-BR";
    private static final String PAGE = "1";
    private static List<ResultSerie> resultSeries = new ArrayList<>();
    private void getData(){

        Call<ExampleSerie> call = ApiClient
                .getInstance()
                .getApi()
                .getExample(API_KEY, LANGUAGE, PAGE);

        call.enqueue(new Callback<ExampleSerie>() {
            @Override
            public void onResponse(Call<ExampleSerie> call, Response<ExampleSerie> response) {
                if(response.isSuccessful() && response.body().getResults() != null){
                    resultSeries = response.body().getResults();
                    for(ResultSerie resultSerie : resultSeries){
                        SeriesAdapter seriesAdapter = new SeriesAdapter(getActivity(), resultSeries);
                        recyclerView.setAdapter(seriesAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ExampleSerie> call, Throwable t) {

            }
        });
    }
}

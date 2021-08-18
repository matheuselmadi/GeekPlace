package com.example.geekplace.viewmodel.serie;

import com.example.geekplace.model.ExampleSerie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("popular")
    Call<ExampleSerie> getExample(
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") String page
    );
}

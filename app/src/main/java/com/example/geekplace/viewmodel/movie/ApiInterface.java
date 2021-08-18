package com.example.geekplace.viewmodel.movie;

import com.example.geekplace.model.ExampleMovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("popular")
    Call<ExampleMovie> getExample(
            @Query("api_key") String apikey,
            @Query("language") String language,
            @Query("page") String page
    );
}

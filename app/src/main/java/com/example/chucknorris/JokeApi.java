package com.example.chucknorris;

import com.example.chucknorris.response.JokeSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JokeApi {
    @GET("jokes/search")
    Call<JokeSearchResponse> searchMovie(
            @Query("query") String query
    );
}
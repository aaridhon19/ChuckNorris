package com.example.chucknorris.request;

import com.example.chucknorris.JokeApi;
import com.example.chucknorris.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static JokeApi jokeApi = retrofit.create(JokeApi.class);

    public static JokeApi getJokeApi() {
        return jokeApi;
    }
}
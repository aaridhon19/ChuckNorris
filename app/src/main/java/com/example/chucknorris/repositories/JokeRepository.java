package com.example.chucknorris.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.chucknorris.models.JokeModel;
import com.example.chucknorris.request.JokeApiClient;

import java.util.List;


public class JokeRepository {
    private static JokeRepository instance;

    private JokeApiClient jokeApiClient;

    public static JokeRepository getInstance() {
        if (instance == null) {
            instance = new JokeRepository();
        }
        return instance;
    }

    private JokeRepository() {
        jokeApiClient = JokeApiClient.getInstance();
    }

    public MutableLiveData<List<JokeModel>> getJokes() {
        return jokeApiClient.getJokes();
    }

    public void searchJokesApi(String query){
        jokeApiClient.searchJokesApi(query);
    }
}
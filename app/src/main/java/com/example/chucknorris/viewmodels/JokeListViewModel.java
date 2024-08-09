package com.example.chucknorris.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.chucknorris.models.JokeModel;
import com.example.chucknorris.repositories.JokeRepository;

import java.util.List;

public class JokeListViewModel extends ViewModel {
    private JokeRepository jokeRepository;

    public JokeListViewModel() {
        jokeRepository = JokeRepository.getInstance();
    }

    public MutableLiveData<List<JokeModel>> getJokes() {
        return jokeRepository.getJokes();
    }

    public void searchJokesApi(String query){
        jokeRepository.searchJokesApi(query);
    }
}
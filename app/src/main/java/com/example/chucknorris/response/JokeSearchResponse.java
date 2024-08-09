package com.example.chucknorris.response;

import com.example.chucknorris.models.JokeModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JokeSearchResponse {

    @SerializedName("result")
    @Expose
    private List<JokeModel> jokes;

    public List<JokeModel> getJokes(){
        return jokes;
    }

    @Override
    public String toString() {
        return "JokeSearchResponse{" +
                "jokes=" + jokes +
                '}';
    }
}
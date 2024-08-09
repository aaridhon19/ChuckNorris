package com.example.chucknorris.request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.chucknorris.AppExecutors;
import com.example.chucknorris.models.JokeModel;
import com.example.chucknorris.response.JokeSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class JokeApiClient {
    private MutableLiveData<List<JokeModel>> mJokes;
    private static JokeApiClient instance;
    private RetrieveJokesRunnable retrieveJokesRunnable;
    public static JokeApiClient getInstance() {
        if (instance == null) {
            instance = new JokeApiClient();
        }
        return instance;
    }

    private JokeApiClient() {
        mJokes = new MutableLiveData<>();
    }
    public MutableLiveData<List<JokeModel>> getJokes() {
        return mJokes;
    }

    public void searchJokesApi(String query) {
        if (retrieveJokesRunnable != null) {
            retrieveJokesRunnable = null;
        }

        retrieveJokesRunnable = new RetrieveJokesRunnable(query);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveJokesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);

    }

    private class RetrieveJokesRunnable implements Runnable {

        private String query;
        boolean cancelRequest;

        public RetrieveJokesRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getJokes(query).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<JokeModel> list = new ArrayList<>(((JokeSearchResponse) response.body()).getJokes());
                    mJokes.postValue(list);
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error " + error);
                    mJokes.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mJokes.postValue(null);
            }
        }

        private Call<JokeSearchResponse> getJokes(String query) {
            return Service.getJokeApi().searchMovie(query);
        }
    }
}
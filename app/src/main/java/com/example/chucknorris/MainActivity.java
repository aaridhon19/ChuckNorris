package com.example.chucknorris;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chucknorris.adapters.JokeRecylerView;
import com.example.chucknorris.models.JokeModel;
import com.example.chucknorris.viewmodels.JokeListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private JokeRecylerView jokeRecyclerAdapter;
    private JokeListViewModel jokeListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpSearchViewAndButton();

        recyclerview = findViewById(R.id.recyclerView);

        jokeListViewModel = new ViewModelProvider(this).get(JokeListViewModel.class);

        configureRecyclerView();

        ObserveAnyChange();
    }

    private void ObserveAnyChange() {
        jokeListViewModel.getJokes().observe(this, new Observer<List<JokeModel>>() {
            @Override
            public void onChanged(List<JokeModel> jokeModels) {
                if (jokeModels != null) {
                    jokeRecyclerAdapter.setJokes(jokeModels);
                }
            }
        });
    }

    private void configureRecyclerView() {
        jokeRecyclerAdapter = new JokeRecylerView();
        recyclerview.setAdapter(jokeRecyclerAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpSearchViewAndButton() {
        final SearchView searchView = findViewById(R.id.searchView);
        final Button searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchView.getQuery().toString();
                if (!query.isEmpty()) {
                    jokeListViewModel.searchJokesApi(query);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                jokeListViewModel.searchJokesApi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
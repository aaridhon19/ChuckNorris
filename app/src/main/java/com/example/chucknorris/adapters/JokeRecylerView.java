package com.example.chucknorris.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chucknorris.R;
import com.example.chucknorris.models.JokeModel;

import java.util.List;

public class JokeRecylerView extends RecyclerView.Adapter<JokeViewHolder> {

    private List<JokeModel> mJokes;
    public JokeRecylerView() {}

    @NonNull
    @Override
    public JokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeViewHolder holder, int position) {
        holder.jokeValue.setText(mJokes.get(position).getValue());

        Glide.with(holder.itemView.getContext())
                .load(mJokes.get(position).getUrl())
                .into(holder.jokeImage);
    }

    @Override
    public int getItemCount() {
        return mJokes != null ? mJokes.size() : 0;
    }

    public void setJokes(List<JokeModel> jokes) {
        this.mJokes = jokes;
        notifyDataSetChanged();
    }
}
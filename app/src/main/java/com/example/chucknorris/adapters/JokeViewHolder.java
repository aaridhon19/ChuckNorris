package com.example.chucknorris.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chucknorris.R;

public class JokeViewHolder  extends RecyclerView.ViewHolder {
    TextView jokeValue;
    ImageView jokeImage;

    public JokeViewHolder(@NonNull View itemView) {
        super(itemView);
        jokeValue = itemView.findViewById(R.id.joke_value);
        jokeImage = itemView.findViewById(R.id.joke_image);
    }
}
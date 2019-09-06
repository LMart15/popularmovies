package com.udacity.popularmovies.movies.movie_trailers.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailerDto;

public class MovieTrailersDiffUtil extends DiffUtil.ItemCallback<TrailerDto> {

    @Override
    public boolean areItemsTheSame(@NonNull TrailerDto oldItem, @NonNull TrailerDto newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull TrailerDto oldItem, @NonNull TrailerDto newItem) {
        return oldItem.equals(newItem);
    }
}

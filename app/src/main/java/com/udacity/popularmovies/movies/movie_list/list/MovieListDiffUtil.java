package com.udacity.popularmovies.movies.movie_list.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.udacity.popularmovies.movies.movie_list.networking.model.MovieDto;

public class MovieListDiffUtil extends DiffUtil.ItemCallback<MovieDto> {

    @Override
    public boolean areItemsTheSame(@NonNull MovieDto oldItem, @NonNull MovieDto newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull MovieDto oldItem, @NonNull MovieDto newItem) {
        return oldItem.equals(newItem);
    }
}

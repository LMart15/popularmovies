package com.udacity.popularmovies.movies.movie_list.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.udacity.popularmovies.movies.movie_list.networking.model.ResultDto;

public class MovieListDiffUtil extends DiffUtil.ItemCallback<ResultDto> {

    @Override
    public boolean areItemsTheSame(@NonNull ResultDto oldItem, @NonNull ResultDto newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ResultDto oldItem, @NonNull ResultDto newItem) {
        return oldItem.equals(newItem);
    }
}

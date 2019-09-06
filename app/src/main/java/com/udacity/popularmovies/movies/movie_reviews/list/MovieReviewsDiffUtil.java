package com.udacity.popularmovies.movies.movie_reviews.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.udacity.popularmovies.movies.movie_reviews.networking.model.ReviewDto;

public class MovieReviewsDiffUtil extends DiffUtil.ItemCallback<ReviewDto> {

    @Override
    public boolean areItemsTheSame(@NonNull ReviewDto oldItem, @NonNull ReviewDto newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ReviewDto oldItem, @NonNull ReviewDto newItem) {
        return oldItem.equals(newItem);
    }
}

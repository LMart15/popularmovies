package com.udacity.popularmovies.movies.movie_reviews.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.BaseAdapter;
import com.udacity.popularmovies.movies.movie_reviews.networking.model.ReviewDto;


public class MovieReviewsAdapter extends BaseAdapter<ReviewDto, MovieReviewsViewHolder> {


    public MovieReviewsAdapter(@NonNull final DiffUtil.ItemCallback<ReviewDto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MovieReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review, parent, false);
        return new MovieReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieReviewsViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

}

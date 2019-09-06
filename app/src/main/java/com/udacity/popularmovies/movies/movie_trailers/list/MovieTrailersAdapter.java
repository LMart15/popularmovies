package com.udacity.popularmovies.movies.movie_trailers.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.BaseAdapter;
import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailerDto;


public class MovieTrailersAdapter extends BaseAdapter<TrailerDto, MovieTrailersViewHolder> {

    @Nullable
    private TrailerClickCallBack trailerClickCallBack;

    public MovieTrailersAdapter(@NonNull final DiffUtil.ItemCallback<TrailerDto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MovieTrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new MovieTrailersViewHolder(view, trailerClickCallBack);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieTrailersViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void setTrailerClickCallback(@Nullable TrailerClickCallBack trailerClickCallBack) {
        this.trailerClickCallBack = trailerClickCallBack;
    }

    public interface TrailerClickCallBack {
        void onTrailerClicked(TrailerDto trailer);
    }

}

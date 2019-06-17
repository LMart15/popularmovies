package com.udacity.popularmovies.movies.movie_list.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.BaseAdapter;
import com.udacity.popularmovies.movies.movie_list.networking.model.ResultDto;


public class MovieListAdapter extends BaseAdapter<ResultDto, MovieListViewHolder> {

    @Nullable
    private MovieClickCallBack movieClickCallBack;

    public MovieListAdapter(@NonNull final DiffUtil.ItemCallback<ResultDto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieListViewHolder(view, movieClickCallBack);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public void setMovieClickCallback(@Nullable MovieClickCallBack movieClickCallback) {
        this.movieClickCallBack = movieClickCallback;
    }

    public interface MovieClickCallBack {
        void onMovieClicked(ResultDto movie);
    }

}

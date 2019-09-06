package com.udacity.popularmovies.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.udacity.popularmovies.movies.movie_reviews.MovieReviewsViewModel;
import com.udacity.popularmovies.movies.movie_trailers.MovieTrailersViewModel;

public class MovieExtrasViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final int movieId;

    public MovieExtrasViewModelFactory(Application application, int movieId) {
        this.application = application;
        this.movieId = movieId;
    }

    @SuppressWarnings(value = "unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieTrailersViewModel.class)) {
            return (T) new MovieTrailersViewModel(application, movieId);
        } else if (modelClass.isAssignableFrom(MovieReviewsViewModel.class)) {
            return (T) new MovieReviewsViewModel(application, movieId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}

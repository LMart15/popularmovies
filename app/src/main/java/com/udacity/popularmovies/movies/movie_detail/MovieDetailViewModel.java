package com.udacity.popularmovies.movies.movie_detail;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.popularmovies.movies.movie_list.networking.model.MovieDto;
import com.udacity.popularmovies.movies.movie_list.repository.MovieDb;

import java.util.concurrent.Executors;

class MovieDetailViewModel extends AndroidViewModel {

    private final MovieDb movieDb;

    @NonNull
    private final MutableLiveData<Boolean> isFavMovieObservable = new MutableLiveData<>();

    public MovieDetailViewModel(Application application) {
        super(application);

        movieDb = MovieDb.getInstance(application.getApplicationContext());

    }

    void setFavoriteMovie(MovieDto movie) {
        Executors.newSingleThreadExecutor().execute(() -> movieDb.movieDao().insertMovie(movie));
    }

    void removeMovieFromFavorites(MovieDto movie) {
        Executors.newSingleThreadExecutor().execute(() -> movieDb.movieDao().removeMovie(movie));
    }

    void isFavoriteMovie(String movieId) {

        Executors.newSingleThreadExecutor().execute(() -> {
            Integer isFav = movieDb.movieDao().isFavMovie(movieId);

            if (isFav == 0) {
                isFavMovieObservable.postValue(false);
            } else {
                isFavMovieObservable.postValue(true);
            }

        });
    }

    LiveData<Boolean> getIsFavoriteMovie() {
        return isFavMovieObservable;
    }
}

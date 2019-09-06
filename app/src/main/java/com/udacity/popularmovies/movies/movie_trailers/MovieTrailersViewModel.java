package com.udacity.popularmovies.movies.movie_trailers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.udacity.popularmovies.movies.movie_trailers.networking.MovieTrailersNetworkClient;
import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailersDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class MovieTrailersViewModel extends ViewModel {

    @NonNull
    private final MovieTrailersNetworkClient movieTrailersNetworkClient;

    @NonNull
    private final MutableLiveData<TrailersDto> movieTrailersObservable = new MutableLiveData<>();


    public MovieTrailersViewModel(Application application, int movieId) {

        movieTrailersNetworkClient = new MovieTrailersNetworkClient(application.getApplicationContext());

        loadMovieTrailersObservable(movieId);
    }

    private void loadMovieTrailersObservable(Integer movieId) {

        movieTrailersNetworkClient.getTrailers(movieId, new Callback<TrailersDto>() {
            @Override
            public void onResponse(Call<TrailersDto> call, Response<TrailersDto> response) {
                movieTrailersObservable.postValue(response.body());
            }

            @Override
            public void onFailure(Call<TrailersDto> call, Throwable t) {
                Timber.d(t);
            }
        });
    }

    LiveData<TrailersDto> getMovieTrailersObservable() {

        return movieTrailersObservable;
    }

}

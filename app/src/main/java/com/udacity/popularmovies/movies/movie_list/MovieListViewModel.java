package com.udacity.popularmovies.movies.movie_list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.movies.movie_list.networking.MovieListNetworkClient;
import com.udacity.popularmovies.movies.movie_list.networking.model.MoviesDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MovieListViewModel extends AndroidViewModel {

    private final String DEFAULT_SORT_TYPE = getApplication().getString(R.string.movies_api_sort_type_popular);

    @NonNull
    private final MovieListNetworkClient moviesNetworkClient;

    @NonNull
    private final MutableLiveData<MoviesDto> moviesObservable = new MutableLiveData<>();


    public MovieListViewModel(Application application) {
        super(application);
        moviesNetworkClient = new MovieListNetworkClient(getApplication().getApplicationContext());

        loadMoviesObservable(DEFAULT_SORT_TYPE);
    }

    private void loadMoviesObservable(String sortType) {

        moviesNetworkClient.getMovies(sortType, new Callback<MoviesDto>() {
            @Override
            public void onResponse(Call<MoviesDto> call, Response<MoviesDto> response) {
                moviesObservable.postValue(response.body());
            }

            @Override
            public void onFailure(Call<MoviesDto> call, Throwable t) {

            }
        });
    }

    LiveData<MoviesDto> getMoviesListObservable() {
        return moviesObservable;
    }

    void sortMovies(String sortType) {
        loadMoviesObservable(sortType);
    }
}

package com.udacity.popularmovies.movies.movie_list;

import android.app.Application;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.movies.movie_list.networking.MovieListNetworkClient;
import com.udacity.popularmovies.movies.movie_list.networking.model.MovieDto;
import com.udacity.popularmovies.movies.movie_list.networking.model.MoviesDto;
import com.udacity.popularmovies.movies.movie_list.repository.MovieDb;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class MovieListViewModel extends AndroidViewModel {

    private final MovieDb movieDb;

    @NonNull
    private final MovieListNetworkClient moviesNetworkClient;

    @NonNull
    private final MediatorLiveData<List<MovieDto>> moviesObservable = new MediatorLiveData<>();

    @NonNull
    private final MutableLiveData<Integer> sortObservable = new MutableLiveData<>();

    public MovieListViewModel(Application application) {
        super(application);

        movieDb = MovieDb.getInstance(application.getApplicationContext());
        moviesNetworkClient = new MovieListNetworkClient(getApplication().getApplicationContext());

        moviesObservable.addSource(sortObservable, this::loadMoviesObservable);
    }

    private void loadMoviesObservable(Integer sortType) {

        SparseArray<String> sortMap = new SparseArray<>();

        sortMap.put(0, getApplication().getString(R.string.movies_api_sort_type_popular));
        sortMap.put(1, getApplication().getString(R.string.movies_api_sort_type_top_rated));
        sortMap.put(2, getApplication().getString(R.string.movies_api_sort_type_favorite));

        if (sortType.equals(2)) {
            Executors.newSingleThreadExecutor().execute(() -> moviesObservable.postValue(movieDb.movieDao().getMovies()));
        } else {

            moviesNetworkClient.getMovies(sortMap.get(sortType), new Callback<MoviesDto>() {
                @Override
                public void onResponse(Call<MoviesDto> call, Response<MoviesDto> response) {
                    moviesObservable.postValue(Objects.requireNonNull(response.body()).getResults());
                }

                @Override
                public void onFailure(Call<MoviesDto> call, Throwable t) {
                    Timber.d(t);
                }
            });
        }
    }

    LiveData<List<MovieDto>> getMoviesListObservable() {
        return moviesObservable;
    }

    @NonNull
    MutableLiveData<Integer> getSortObservable() {
        return sortObservable;
    }

    void setSortObservable(Integer sortType) {
        sortObservable.postValue(sortType);
    }

}

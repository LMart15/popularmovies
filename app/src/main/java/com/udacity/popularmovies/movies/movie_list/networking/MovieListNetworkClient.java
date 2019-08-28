package com.udacity.popularmovies.movies.movie_list.networking;

import android.content.Context;

import androidx.annotation.NonNull;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.ServiceGenerator;
import com.udacity.popularmovies.movies.movie_list.networking.model.MoviesDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MovieListNetworkClient {

    @NonNull
    private final MovieListService movieService;

    public MovieListNetworkClient(Context context) {
        movieService = ServiceGenerator.createService(context.getString(R.string.base_url), MovieListService.class);
    }


    public void getMovies(@NonNull final String sortType, final Callback<MoviesDto> moviesDtoCallback) {

        Call<MoviesDto> moviesDtoCall = movieService.getMovies(sortType);

        moviesDtoCall.enqueue(new Callback<MoviesDto>() {
            @Override
            public void onResponse(Call<MoviesDto> call, Response<MoviesDto> response) {
                moviesDtoCallback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<MoviesDto> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

}

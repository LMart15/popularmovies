package com.udacity.popularmovies.movies.movie_trailers.networking;

import android.content.Context;

import androidx.annotation.NonNull;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.ServiceGenerator;
import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailersDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MovieTrailersNetworkClient {

    @NonNull
    private final MovieTrailersService movieTrailersService;

    public MovieTrailersNetworkClient(Context context) {
        movieTrailersService = ServiceGenerator.createService(context.getString(R.string.movies_base_url), MovieTrailersService.class);
    }


    public void getTrailers(@NonNull final Integer movieId, final Callback<TrailersDto> trailersDtoCallback) {

        Call<TrailersDto> movieTrailersDtoCall = movieTrailersService.getTrailers(movieId);

        movieTrailersDtoCall.enqueue(new Callback<TrailersDto>() {
            @Override
            public void onResponse(Call<TrailersDto> call, Response<TrailersDto> response) {
                trailersDtoCallback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<TrailersDto> call, Throwable t) {
                Timber.e(t);
            }
        });
    }
}

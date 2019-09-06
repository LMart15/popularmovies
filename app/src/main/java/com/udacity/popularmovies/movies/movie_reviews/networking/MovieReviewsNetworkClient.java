package com.udacity.popularmovies.movies.movie_reviews.networking;

import android.content.Context;

import androidx.annotation.NonNull;

import com.udacity.popularmovies.R;
import com.udacity.popularmovies.core.ServiceGenerator;
import com.udacity.popularmovies.movies.movie_reviews.networking.model.ReviewsDto;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MovieReviewsNetworkClient {

    @NonNull
    private final MovieReviewsService movieReviewsService;

    public MovieReviewsNetworkClient(Context context) {
        movieReviewsService = ServiceGenerator.createService(context.getString(R.string.movies_base_url), MovieReviewsService.class);
    }


    public void getReviews(@NonNull final Integer movieId, final Callback<ReviewsDto> reviewsDtoCallback) {

        Call<ReviewsDto> movieReviewsDtoCall = movieReviewsService.getReviews(movieId);

        movieReviewsDtoCall.enqueue(new Callback<ReviewsDto>() {
            @Override
            public void onResponse(Call<ReviewsDto> call, Response<ReviewsDto> response) {
                reviewsDtoCallback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ReviewsDto> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

}

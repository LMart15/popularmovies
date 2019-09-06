package com.udacity.popularmovies.movies.movie_reviews.networking;


import com.udacity.popularmovies.movies.movie_reviews.networking.model.ReviewsDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface MovieReviewsService {

    @GET("movie/{movie_id}/reviews")
    Call<ReviewsDto> getReviews(@Path("movie_id") Integer movieId);

}

package com.udacity.popularmovies.movies.movie_trailers.networking;


import com.udacity.popularmovies.movies.movie_trailers.networking.model.TrailersDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface MovieTrailersService {

    @GET("movie/{movie_id}/videos")
    Call<TrailersDto> getTrailers(@Path("movie_id") Integer movieId);

}

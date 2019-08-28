package com.udacity.popularmovies.movies.movie_list.networking;


import com.udacity.popularmovies.movies.movie_list.networking.model.MoviesDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface MovieListService {

    @GET("movie/{sort_type}")
    Call<MoviesDto> getMovies(@Path("sort_type") String sortType);

}

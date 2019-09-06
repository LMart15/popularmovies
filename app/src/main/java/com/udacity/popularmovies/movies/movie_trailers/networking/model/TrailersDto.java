
package com.udacity.popularmovies.movies.movie_trailers.networking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersDto {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<TrailerDto> trailerDtos = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TrailerDto> getTrailers() {
        return trailerDtos;
    }

    public void setTrailers(List<TrailerDto> trailerDtos) {
        this.trailerDtos = trailerDtos;
    }

}

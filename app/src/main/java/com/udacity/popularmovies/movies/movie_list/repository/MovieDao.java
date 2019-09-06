package com.udacity.popularmovies.movies.movie_list.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.udacity.popularmovies.movies.movie_list.networking.model.MovieDto;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieDao {

    @Insert(onConflict = REPLACE)
    void insertMovie(MovieDto movie);

    @Delete
    void removeMovie(MovieDto movie);

    @Query("SELECT * FROM MovieDto")
    List<MovieDto> getMovies();

    @Query("SELECT COUNT(*) FROM MovieDto WHERE id = :movie_id")
    Integer isFavMovie(String movie_id);

}

package com.udacity.popularmovies.movies.movie_list.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.udacity.popularmovies.movies.movie_list.networking.model.MovieDto;

@Database(entities = {MovieDto.class}, version = 2)
public abstract class MovieDb extends RoomDatabase {

    public abstract MovieDao movieDao();

    private static MovieDb movieDb;

    public static MovieDb getInstance(Context context) {
        if (null == movieDb) {
            movieDb = buildDatabaseInstance(context);
        }
        return movieDb;
    }

    private static MovieDb buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, MovieDb.class, "movie_db").build();
    }

}

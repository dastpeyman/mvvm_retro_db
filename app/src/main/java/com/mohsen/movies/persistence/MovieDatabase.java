package com.mohsen.movies.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.mohsen.movies.models.Movie;
import com.mohsen.movies.models.MovieDetail;

@Database(entities ={Movie.class,MovieDetail.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MovieDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "movies_db";

    private static MovieDatabase instance;

    public static MovieDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MovieDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }

    public abstract MovieDao getmovieDao();
    public abstract MovieDetailDao getMovieDetailDao();

}







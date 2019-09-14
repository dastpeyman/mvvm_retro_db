package com.mohsen.movies.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mohsen.movies.models.Movie;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieDao {

    @Insert(onConflict = IGNORE)
    long[] insertMovies(Movie... movies);

    @Insert(onConflict = REPLACE)
    void insertMovie(Movie movie);

    @Query("UPDATE movies SET Title = :title,  Year= :year, Poster = :poster, Type = :type " +
            "WHERE imdbID = :movie_id")
    void updateMovie(String movie_id, String title, String year, String poster, String type);

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :query || '%'  ")
//            +"ORDER BY social_rank DESC LIMIT (:pageNumber * 30)")
    LiveData<List<Movie>> searchMovie(String query);

    @Query("SELECT * FROM movies WHERE imdbID = :movie_id")
    LiveData<Movie> getMovie(String movie_id);

}










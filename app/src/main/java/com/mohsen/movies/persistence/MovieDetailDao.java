package com.mohsen.movies.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mohsen.movies.models.MovieDetail;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface MovieDetailDao {

    @Insert(onConflict = REPLACE)
    void insertMovieDetail(MovieDetail movieDetail);

    @Query("UPDATE movie_detail SET Title = :title,  Year= :year, Poster = :poster, Type = :type , Rated= :rated ,Country= :country " +
            "WHERE imdbID = :movie_id")
    void updateMovieDetail(String movie_id, String title, String year, String poster, String type, String rated,String country);


    @Query("SELECT * FROM movie_detail WHERE imdbID = :movie_id")
    LiveData<MovieDetail> getMovieDetail(String movie_id);

}










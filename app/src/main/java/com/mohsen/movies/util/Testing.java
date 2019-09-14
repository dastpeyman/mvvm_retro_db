package com.mohsen.movies.util;

import android.util.Log;

import com.mohsen.movies.models.Movie;

import java.util.List;

public class Testing {

    public static void printmovies(List<Movie>list, String tag){
        for(Movie movie: list){
            Log.d(tag, "onChanged: " + movie.getTitle());
        }
    }
}

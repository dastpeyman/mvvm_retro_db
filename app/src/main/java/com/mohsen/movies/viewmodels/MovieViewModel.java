package com.mohsen.movies.viewmodels;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.mohsen.movies.models.MovieDetail;
import com.mohsen.movies.repositories.MovieRepository;
import com.mohsen.movies.util.Resource;


public class MovieViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
    }

    public LiveData<Resource<MovieDetail>> getMovie(String movieId){

        return movieRepository.movieApi(movieId);
    }

}






















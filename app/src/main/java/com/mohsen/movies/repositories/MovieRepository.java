package com.mohsen.movies.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mohsen.movies.AppExecutors;
import com.mohsen.movies.models.Movie;
import com.mohsen.movies.models.MovieDetail;
import com.mohsen.movies.persistence.MovieDao;
import com.mohsen.movies.persistence.MovieDatabase;
import com.mohsen.movies.persistence.MovieDetailDao;
import com.mohsen.movies.requests.ServiceGenerator;
import com.mohsen.movies.requests.responses.ApiResponse;
import com.mohsen.movies.requests.responses.movieResponse;
import com.mohsen.movies.requests.responses.movieSearchResponse;
import com.mohsen.movies.util.Constants;
import com.mohsen.movies.util.NetworkBoundResource;
import com.mohsen.movies.util.Resource;

import java.util.List;

public class MovieRepository {

    private static final String TAG = "MovieRepository";

    private static MovieRepository instance;
    private MovieDao movieDao;
    private MovieDetailDao movieDetailDao;

    public static MovieRepository getInstance(Context context){
        if(instance == null){
            instance = new MovieRepository(context);
        }
        return instance;
    }


    private MovieRepository(Context context) {
        movieDao = MovieDatabase.getInstance(context).getmovieDao();
        movieDetailDao = MovieDatabase.getInstance(context).getMovieDetailDao();
    }


    public LiveData<Resource<List<Movie>>> searchmoviesApi(final String query){
        return new NetworkBoundResource<List<Movie>, movieSearchResponse>(AppExecutors.getInstance()){

            @Override
            protected void saveCallResult(@NonNull movieSearchResponse item) {

                if(item.getMovies() != null){ // movie list will be null if the api key is expired
//                    Log.d(TAG, "saveCallResult: movie response: " + item.toString());

                    Movie[] movies = new Movie[item.getMovies().size()];

                    int index = 0;
                    for(long rowid: movieDao.insertMovies((Movie[]) (item.getMovies().toArray(movies)))){
                        if(rowid == -1){
                            Log.d(TAG, "saveCallResult: CONFLICT... This movie is already in the cache");
                            // if the movie already exists... I don't want to set the ingredients or timestamp b/c
                            // they will be erased
                            movieDao.updateMovie(
                                    movies[index].getImdbID(),
                                    movies[index].getTitle(),
                                    movies[index].getYear(),
                                    movies[index].getPoster(),
                                    movies[index].getType()
                            );
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Movie> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return movieDao.searchMovie(query);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<movieSearchResponse>> createCall() {
                return ServiceGenerator.getmovieApi()
                        .searchMovie(
                                Constants.BASE_URL,
                                Constants.API_KEY,
                                Constants.text
//                                String.valueOf(pageNumber)
                        );
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<MovieDetail>>  movieApi(final String movieId){
        return new NetworkBoundResource<MovieDetail, movieResponse>(AppExecutors.getInstance()){
            @Override
            protected void saveCallResult(@NonNull movieResponse item) {

                // will be null if API key is expired
                if(item.getMovieDetail()!=null){
                    item.getMovieDetail().setImdbID(movieId);
                    movieDetailDao.insertMovieDetail(item.getMovieDetail());
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable MovieDetail data) {
                return true;
            }


            @NonNull
            @Override
            protected LiveData<MovieDetail> loadFromDb() {
                return movieDetailDao.getMovieDetail(movieId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<movieResponse>> createCall() {
                return ServiceGenerator.getmovieApi().getMovie(
                        Constants.BASE_URL,
                        Constants.API_KEY,
                        movieId
                );
            }
        }.getAsLiveData();
    }
}













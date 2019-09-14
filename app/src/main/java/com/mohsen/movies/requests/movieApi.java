package com.mohsen.movies.requests;

import android.arch.lifecycle.LiveData;

import com.mohsen.movies.requests.responses.ApiResponse;
import com.mohsen.movies.requests.responses.movieResponse;
import com.mohsen.movies.requests.responses.movieSearchResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface movieApi {

    // SEARCH
    @GET
    LiveData<ApiResponse<movieSearchResponse>> searchMovie(
            @Url String url,
            @Query("apikey") String key,
            @Query("s") String query
//            @Query("page") String page
    );

    // GET movie REQUEST
    @GET
    LiveData<ApiResponse<movieResponse>> getMovie(
            @Url String url,
            @Query("apikey") String key,
            @Query("i") String movie_id
    );
}

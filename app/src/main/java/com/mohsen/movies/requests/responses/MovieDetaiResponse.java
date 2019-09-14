package com.mohsen.movies.requests.responses;

import android.support.annotation.Nullable;

import com.mohsen.movies.models.MovieDetail;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetaiResponse {

    @SerializedName("Search")
    @Expose()
    private MovieDetail movieDetail;

    @SerializedName("error")
    @Expose()
    private String error;

    public String getError() {
        return error;
    }

    @Nullable
    public MovieDetail getMovie(){
        return movieDetail;
    }

    @Override
    public String toString() {
        return "MovieDetailResponse{" +
                "movie=" + movieDetail +
                '}';
    }
}

package com.mohsen.movies.requests.responses;

import android.support.annotation.Nullable;

import com.mohsen.movies.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class movieSearchResponse {

    @SerializedName("totalResults")
    @Expose()
    private String totalResults;

    @SerializedName("Search")
    @Expose()
    private List<Movie> movies;

    @SerializedName("Response")
    @Expose()
    private String Response;

    public String getError() {
        return Response;
    }

    public String getCount() {
        return totalResults;
    }

    @Nullable
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                "Search=" + movies +
                ",totalResults='" + totalResults + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }
}

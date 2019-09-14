package com.mohsen.movies.requests.responses;

public class CheckMovieApiKey {

    protected static boolean isMovieApiKeyValid(movieSearchResponse response){
        return response.getError() == null;
    }

//    protected static boolean ismovieApiKeyValid(movieResponse response){
//        return response.getError() == null;
//    }
}

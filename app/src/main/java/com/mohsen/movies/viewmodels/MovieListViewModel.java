package com.mohsen.movies.viewmodels;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import com.mohsen.movies.models.Movie;
import com.mohsen.movies.repositories.MovieRepository;
import com.mohsen.movies.util.Resource;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {

    private static final String TAG = "movieListViewModel";

    public static final String QUERY_EXHAUSTED = "No more results.";
    public enum ViewState {MOVIES}

    private MutableLiveData<ViewState> viewState;
    private MediatorLiveData<Resource<List<Movie>>> movies = new MediatorLiveData<>();
    private MovieRepository movieRepository;

    // query extras
    private boolean isQueryExhausted;
    private boolean isPerformingQuery;
    private int pageNumber;
    private String query;
    private String batman;
    private boolean cancelRequest;
    private long requestStartTime;

    public MovieListViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance(application);
        init();

    }

    private void init(){
        if(viewState == null){
            viewState = new MutableLiveData<>();
            viewState.setValue(ViewState.MOVIES);
            executeSearch();
        }
    }
    public LiveData<ViewState> getViewstate(){
        return viewState;
    }

    public LiveData<Resource<List<Movie>>> getMovies(){
        return movies;
    }

    public int getPageNumber(){
        return pageNumber;
    }

    public void setViewCategories(){
        viewState.setValue(ViewState.MOVIES);
    }

    public void searchmoviesApi(String query){
        if(!isPerformingQuery){
            if(pageNumber == 0){
                pageNumber = 1;
            }
            this.pageNumber = pageNumber;
            this.query = query;
            isQueryExhausted = false;
            executeSearch();
        }
    }

    public void searchNextPage(){
        if(!isQueryExhausted && !isPerformingQuery){
            pageNumber++;
            executeSearch();
        }
    }

    private void executeSearch(){
        requestStartTime = System.currentTimeMillis();
        cancelRequest = false;
        isPerformingQuery = true;
        viewState.setValue(ViewState.MOVIES);
        final LiveData<Resource<List<Movie>>> repositorySource = movieRepository.searchmoviesApi("batman");
        movies.addSource(repositorySource, new Observer<Resource<List<Movie>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Movie>> listResource) {
                if(!cancelRequest){
                    if(listResource != null){
                        if(listResource.status == Resource.Status.SUCCESS){
                            Log.d(TAG, "onChanged: REQUEST TIME: " + (System.currentTimeMillis() - requestStartTime) / 1000 + " seconds.");
                            Log.d(TAG, "onChanged: page number: " + pageNumber);
                            Log.d(TAG, "onChanged: " + listResource.data);

                            isPerformingQuery = false;
                            if(listResource.data != null){
                                if(listResource.data.size() == 0 ){
                                    Log.d(TAG, "onChanged: query is exhausted...");
                                    movies.setValue(
                                            new Resource<List<Movie>>(
                                                    Resource.Status.ERROR,
                                                    listResource.data,
                                                    QUERY_EXHAUSTED
                                            )
                                    );
                                    isQueryExhausted = true;
                                }
                            }
                            movies.removeSource(repositorySource);
                        }
                        else if(listResource.status == Resource.Status.ERROR){
                            Log.d(TAG, "onChanged: REQUEST TIME: " + (System.currentTimeMillis() - requestStartTime) / 1000 + " seconds.");
                            isPerformingQuery = false;
                            if(listResource.message.equals(QUERY_EXHAUSTED)){
                                isQueryExhausted = true;
                            }
                            movies.removeSource(repositorySource);
                        }
                        movies.setValue(listResource);
                    }
                    else{
                        movies.removeSource(repositorySource);
                    }
                }
                else{
                    movies.removeSource(repositorySource);
                }
            }
        });
    }



    public void cancelSearchRequest(){
        if(isPerformingQuery){
            Log.d(TAG, "cancelSearchRequest: canceling the search request.");
            cancelRequest = true;
            isPerformingQuery = false;
            pageNumber = 1;
        }
    }
}
















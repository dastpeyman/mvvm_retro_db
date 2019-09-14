package com.mohsen.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.mohsen.movies.adapters.OnMovieListener;
import com.mohsen.movies.adapters.MovieRecyclerAdapter;
import com.mohsen.movies.models.Movie;
import com.mohsen.movies.util.Resource;
import com.mohsen.movies.util.VerticalSpacingItemDecorator;
import com.mohsen.movies.viewmodels.MovieListViewModel;

import java.util.List;

import static com.mohsen.movies.viewmodels.MovieListViewModel.QUERY_EXHAUSTED;


public class MovieListActivity extends BaseActivity implements OnMovieListener {

    private static final String TAG = "movieListActivity";

    private MovieListViewModel mMovieListViewModel;
    private RecyclerView mRecyclerView;
    private MovieRecyclerAdapter mAdapter;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mRecyclerView = findViewById(R.id.movie_list);
        mSearchView = findViewById(R.id.search_view);

        mMovieListViewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);

        initRecyclerView();

//        initSearchView();
//        searchmoviesApi();
//        searchmoviesApi();
        subscribeObservers();
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
    }

    private void subscribeObservers(){
        mMovieListViewModel.getMovies().observe(this, new Observer<Resource<List<Movie>>>() {
            @Override
            public void onChanged(@Nullable Resource<List<Movie>> listResource) {
                if(listResource != null){
                    Log.d(TAG, "onChanged: status: " + listResource.status);

                    if(listResource.data != null){
                        switch (listResource.status){
                            case LOADING:{
                                if(mMovieListViewModel.getPageNumber() > 1){
                                    mAdapter.displayLoading();
                                }
                                else{
                                    mAdapter.displayLoading();
                                }
                                break;
                            }

                            case ERROR:{
                                Log.e(TAG, "onChanged: cannot refresh the cache." );
                                Log.e(TAG, "onChanged: ERROR message: " + listResource.message );
                                Log.e(TAG, "onChanged: status: ERROR, #movies: " + listResource.data.size());
                                mAdapter.hideLoading();
                                mAdapter.setmovies(listResource.data);
                                Toast.makeText(MovieListActivity.this, listResource.message, Toast.LENGTH_SHORT).show();

                                if(listResource.message.equals(QUERY_EXHAUSTED)){
                                    mAdapter.setQueryExhausted();
                                }
                                break;
                            }

                            case SUCCESS:{
                                Log.d(TAG, "onChanged: cache has been refreshed.");
                                Log.d(TAG, "onChanged: status: SUCCESS, #movies: " + listResource.data.size());
                                mAdapter.hideLoading();
                                mAdapter.setmovies(listResource.data);
                                break;
                            }
                        }
                    }
                }
            }
        });

        mMovieListViewModel.getViewstate().observe(this, new Observer<MovieListViewModel.ViewState>() {
            @Override
            public void onChanged(@Nullable MovieListViewModel.ViewState viewState) {
                if(viewState != null){
                    switch (viewState){

                        case MOVIES:{
                            // movies will show automatically from other observer
                            break;
                        }

//                        case CATEGORIES:{
//                            displaySearchCategories();
//                            break;
//                        }
                    }
                }
            }
        });
    }

    private RequestManager initGlide(){

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    private void searchmoviesApi(){
        mRecyclerView.smoothScrollToPosition(0);
        mMovieListViewModel.searchmoviesApi("batman");
//        mSearchView.clearFocus();
    }

    private void initRecyclerView(){
        ViewPreloadSizeProvider<String> viewPreloader = new ViewPreloadSizeProvider<>();
        mAdapter = new MovieRecyclerAdapter(this, initGlide(), viewPreloader);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(30);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        RecyclerViewPreloader<String> preloader = new RecyclerViewPreloader<String>(
                Glide.with(this),
                mAdapter,
                viewPreloader,
                30);

        mRecyclerView.addOnScrollListener(preloader);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!mRecyclerView.canScrollVertically(1)
                        && mMovieListViewModel.getViewstate().getValue() == MovieListViewModel.ViewState.MOVIES){
                    mMovieListViewModel.searchNextPage();
                }
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

//    private void initSearchView(){
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//
//                searchmoviesApi(s);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });
//    }

    @Override
    public void onmovieClick(int position) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra("movie", mAdapter.getSelectedmovie(position));
        startActivity(intent);
    }

//    @Override
//    public void onCategoryClick(String category) {
//
//        searchmoviesApi(category);
//    }



    @Override
    public void onBackPressed() {
        if(mMovieListViewModel.getViewstate().getValue() == MovieListViewModel.ViewState.MOVIES){
            super.onBackPressed();
        }
        else{
            mMovieListViewModel.cancelSearchRequest();
            mMovieListViewModel.setViewCategories();
        }
    }
}


















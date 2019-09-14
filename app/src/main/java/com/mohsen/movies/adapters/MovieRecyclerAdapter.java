package com.mohsen.movies.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.mohsen.movies.R;
import com.mohsen.movies.models.Movie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        ListPreloader.PreloadModelProvider<String>
{

    private static final int movie_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;
    private static final int EXHAUSTED_TYPE = 4;

    private List<Movie> mMovies;
    private OnMovieListener mOnmovieListener;
    private RequestManager requestManager;
    private ViewPreloadSizeProvider<String> preloadSizeProvider;

    public MovieRecyclerAdapter(OnMovieListener mOnmovieListener,
                                RequestManager requestManager,
                                ViewPreloadSizeProvider<String> viewPreloadSizeProvider) {
        this.mOnmovieListener = mOnmovieListener;
        this.requestManager = requestManager;
        this.preloadSizeProvider = viewPreloadSizeProvider;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = null;
        switch (i){

            case movie_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_movie_list_item, viewGroup, false);
                return new MovieViewHolder(view, mOnmovieListener, requestManager, preloadSizeProvider);
            }

            case LOADING_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_list_item, viewGroup, false);
                return new LoadingViewHolder(view);
            }

            case EXHAUSTED_TYPE:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_search_exhausted, viewGroup, false);
                return new SearchExhaustedViewHolder(view);
            }

            default:{
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_movie_list_item, viewGroup, false);
                return new MovieViewHolder(view, mOnmovieListener, requestManager, preloadSizeProvider);
            }
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int itemViewType = getItemViewType(i);
        if(itemViewType == movie_TYPE){
            ((MovieViewHolder)viewHolder).onBind(mMovies.get(i));
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(mMovies.get(position).getTitle().equals("LOADING...")){
            return LOADING_TYPE;
        }
        else if(mMovies.get(position).getTitle().equals("EXHAUSTED...")){
            return EXHAUSTED_TYPE;
        }
        else{
            return movie_TYPE;
        }
    }

    // display loading during search request
    public void displayOnlyLoading(){
        clearmoviesList();
        Movie movie = new Movie();
        movie.setTitle("LOADING...");
        mMovies.add(movie);
        notifyDataSetChanged();
    }

    private void clearmoviesList(){
        if(mMovies == null){
            mMovies = new ArrayList<>();
        }
        else{
            mMovies.clear();
        }
        notifyDataSetChanged();
    }

    public void setQueryExhausted(){
        hideLoading();
        Movie exhaustedmovie = new Movie();
        exhaustedmovie.setTitle("EXHAUSTED...");
        mMovies.add(exhaustedmovie);
        notifyDataSetChanged();
    }

    public void hideLoading(){
        if(isLoading()){
            if(mMovies.get(0).getTitle().equals("LOADING...")){
                mMovies.remove(0);
            }
            else if(mMovies.get(mMovies.size() - 1).equals("LOADING...")){
                mMovies.remove(mMovies.size() - 1);
            }
            notifyDataSetChanged();
        }
    }

    // pagination loading
    public void displayLoading(){
        if(mMovies == null){
            mMovies = new ArrayList<>();
        }
        if(!isLoading()){
            Movie movie = new Movie();
            movie.setTitle("LOADING...");
            mMovies.add(movie);
            notifyDataSetChanged();
        }
    }

    private boolean isLoading(){
        if(mMovies != null){
            if(mMovies.size() > 0){
                if(mMovies.get(mMovies.size() - 1).getTitle().equals("LOADING...")){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public int getItemCount() {
        if(mMovies != null){
            return mMovies.size();
        }
        return 0;
    }

    public void setmovies(List<Movie> movies){
        mMovies = movies;
        notifyDataSetChanged();
    }

    public Movie getSelectedmovie(int position){
        if(mMovies != null){
            if(mMovies.size() > 0){
                return mMovies.get(position);
            }
        }
        return null;
    }

    @NonNull
    @Override
    public List<String> getPreloadItems(int position) {
        String url = mMovies.get(position).getPoster();
        if(TextUtils.isEmpty(url)){
            return Collections.emptyList();
        }
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull String item) {
        return requestManager.load(item);
    }
}
















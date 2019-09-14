package com.mohsen.movies.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.ViewPreloadSizeProvider;
import com.mohsen.movies.R;
import com.mohsen.movies.models.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, publisher, socialScore;
    AppCompatImageView image;
    OnMovieListener onMovieListener;
    RequestManager requestManager;
    ViewPreloadSizeProvider viewPreloadSizeProvider;

    public MovieViewHolder(@NonNull View itemView,
                           OnMovieListener onmovieListener,
                           RequestManager requestManager,
                           ViewPreloadSizeProvider preloadSizeProvider) {
        super(itemView);

        this.onMovieListener = onmovieListener;
        this.requestManager = requestManager;
        this.viewPreloadSizeProvider = preloadSizeProvider;

        title = itemView.findViewById(R.id.movie_title);
        publisher = itemView.findViewById(R.id.movie_publisher);
        socialScore = itemView.findViewById(R.id.movie_social_score);
        image = itemView.findViewById(R.id.Movie_image);

        itemView.setOnClickListener(this);
    }

    public void onBind(Movie movie){

        requestManager
                .load(movie.getPoster())
                .into(image);

        title.setText(movie.getTitle());
        publisher.setText(movie.getYear());
//        socialScore.setText(String.valueOf(Math.round(movie.getSocial_rank())));

        viewPreloadSizeProvider.setView(image);
    }

    @Override
    public void onClick(View v) {
        onMovieListener.onmovieClick(getAdapterPosition());
    }
}






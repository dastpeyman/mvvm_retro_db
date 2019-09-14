package com.mohsen.movies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mohsen.movies.models.Movie;
import com.mohsen.movies.models.MovieDetail;
import com.mohsen.movies.util.Resource;
import com.mohsen.movies.viewmodels.MovieViewModel;

public class MovieActivity extends BaseActivity {

    private static final String TAG = "movieActivity";

    // UI components
    private ImageView mMovieImage;
    private TextView detail_header_title,country,released,language,
            rated,genre,director,writer,actors,plot,awards,rating;
    private NestedScrollView mScrollView;
    private CollapsingToolbarLayout collapsing_toolbar;
    private MovieViewModel mMovieViewModel;
    private Toolbar toolbar;
    private RatingBar detail_header_star;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        mMovieImage = findViewById(R.id.movie_image);
        detail_header_title = findViewById(R.id.detail_header_title);
        detail_header_star = findViewById(R.id.detail_header_star);
        rating = findViewById(R.id.rating);
        country = findViewById(R.id.country);
        released = findViewById(R.id.released);
        language = findViewById(R.id.language);
        rated = findViewById(R.id.rated);
        genre = findViewById(R.id.genre);
        director = findViewById(R.id.director);
        writer = findViewById(R.id.writer);
        actors = findViewById(R.id.actors);
        plot = findViewById(R.id.plot);
        awards = findViewById(R.id.awards);
        collapsing_toolbar = findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.movie_detail_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setSupportActionBar(toolbar);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


        mScrollView = findViewById(R.id.parent);

        mMovieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        getIncomingIntent();

    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("movie")){
            Movie movie = getIntent().getParcelableExtra("movie");
            String movieId = movie.getImdbID();
            Log.d(TAG, "getIncomingIntent: " + movie.getTitle());
            subscribeObservers(movieId);
            setMovieProperties(movie);
        }
    }

    private void subscribeObservers(final String movieId){
        mMovieViewModel.getMovie(movieId).observe(this, new Observer<Resource<MovieDetail>>() {
            @Override
            public void onChanged(@Nullable Resource<MovieDetail> movieDetailResource) {
                if(movieDetailResource != null){
                    if(movieDetailResource.data != null){
                        switch (movieDetailResource.status){

                            case LOADING:{
                                showProgressBar(true);
                                break;
                            }

                            case ERROR:{
                                Log.e(TAG, "onChanged: status: ERROR, movie: " + movieDetailResource.data.getTitle() );
                                Log.e(TAG, "onChanged: ERROR message: " + movieDetailResource.message );
                                showParent();showProgressBar(false);
                                setMovieDetailProperties(movieDetailResource.data);
                                break;
                            }

                            case SUCCESS:{
                                Log.d(TAG, "onChanged: cache has been refreshed.");
                                Log.d(TAG, "onChanged: status: SUCCESS, movie: " + movieDetailResource.data.getTitle());
                                showParent();
                                showProgressBar(false);
                                setMovieDetailProperties(movieDetailResource.data);
                                break;
                            }
                        }
                    }
                }
            }

        });
    }

    private void setMovieProperties(Movie movie){
        if(movie != null){
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.white_background)
                    .error(R.drawable.white_background);

            Glide.with(this)
                    .setDefaultRequestOptions(options)
                    .load(movie.getPoster())
                    .into(mMovieImage);

            detail_header_title.setText(movie.getTitle());
            collapsing_toolbar.setTitle(movie.getTitle());
//            mmovieRank.setText(String.valueOf(Math.round(movie.getSocial_rank())));

//            setIngredients(movie);
        }
    }

    private void setMovieDetailProperties(MovieDetail movieDetail){
        country.setText(movieDetail.getCountry());
        awards.setText(movieDetail.getAwards());
        language.setText(movieDetail.getLanguage());
        released.setText(movieDetail.getReleased());
        genre.setText(movieDetail.getGenre());
        plot.setText(movieDetail.getPlot());
        director.setText(movieDetail.getDirector());
        rated.setText(movieDetail.getRated());
        rating.setText(movieDetail.getImdbRating());
        detail_header_star.setRating(Float.parseFloat(movieDetail.getImdbRating()));
        writer.setText(movieDetail.getWriter());

    }


    private void showParent(){
        mScrollView.setVisibility(View.VISIBLE);

    }
}















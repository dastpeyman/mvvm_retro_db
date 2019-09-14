package com.mohsen.movies.requests.responses;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.mohsen.movies.models.MovieDetail;

public class movieResponse {


    //    @SerializedName("movie")
//    @Expose()
    private MovieDetail movieDetail;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "imdbID")
    private String imdbID;

    @ColumnInfo(name = "Year")
    private String Year;

    @ColumnInfo(name = "Poster")
    private String Poster;

    @ColumnInfo(name = "Type")
    private String Type;

    @ColumnInfo(name = "Title")
    private String Title;

    @ColumnInfo(name = "Rated")
    private String Rated;

    @ColumnInfo(name = "Country")
    private String Country;

    @ColumnInfo(name = "Released")
    private String Released;

    @ColumnInfo(name = "Runtime")
    private String Runtime;

    @ColumnInfo(name = "Genre")
    private String Genre;

    @ColumnInfo(name = "Director")
    private String Director;

    @ColumnInfo(name = "Plot")
    private String Plot;

    @ColumnInfo(name = "Writer")
    private String Writer;

    @ColumnInfo(name = "Language")
    private String Language;


    @ColumnInfo(name = "Awards")
    private String Awards;

    @ColumnInfo(name = "Metascore")
    private String Metascore;

    @ColumnInfo(name = "imdbRating")
    private String imdbRating;

    @ColumnInfo(name = "imdbVotes")
    private String imdbVotes;

    @ColumnInfo(name = "Actors")
    private String Actors;


    public MovieDetail getMovieDetail() {
        MovieDetail movieDetail1= new MovieDetail();
        movieDetail1.setImdbID(imdbID);
        movieDetail1.setTitle(Title);
        movieDetail1.setYear(Year);
        movieDetail1.setPoster(Poster);
        movieDetail1.setType(Type);
        movieDetail1.setRated(Rated);
        movieDetail1.setCountry(Country);
        movieDetail1.setReleased(Released);
        movieDetail1.setRuntime(Runtime);
        movieDetail1.setGenre(Genre);
        movieDetail1.setDirector(Director);
        movieDetail1.setPlot(Plot);
        movieDetail1.setWriter(Writer);
        movieDetail1.setLanguage(Language);
        movieDetail1.setAwards(Awards);
        movieDetail1.setMetascore(Metascore);
        movieDetail1.setImdbRating(imdbRating);
        movieDetail1.setImdbVotes(imdbVotes);
        movieDetail1.setActors(Actors);
        return movieDetail1;
    }

    @Override
    public String toString() {
        return "movieResponse{" +
                "imdbID='" + imdbID + '\'' +
                ", Year='" + Year + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Type='" + Type + '\'' +
                ", Title='" + Title + '\'' +
                ", Rated='" + Rated + '\'' +
                ", Country='" + Country + '\'' +
                ", Released='" + Released + '\'' +
                ", Runtime='" + Runtime + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Director='" + Director + '\'' +
                ", Plot='" + Plot + '\'' +
                ", Writer='" + Writer + '\'' +
                ", Language='" + Language + '\'' +
                ", Awards='" + Awards + '\'' +
                ", Metascore='" + Metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", Actors='" + Actors + '\'' +
                '}';
    }
}

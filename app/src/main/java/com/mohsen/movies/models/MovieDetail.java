package com.mohsen.movies.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "movie_detail")
public class MovieDetail implements Parcelable{

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

    public MovieDetail(@NonNull String imdbID, String title, String year, String poster,
                       String type, String rate,String country,String released,String runtime,String genre,
    String director,String plot,String writer,String language,String awards,String metascore,String imdbVotes,String imdbRating,String actors) {
        this.imdbID = imdbID;
        this.Title = title;
        this.Year = year;
        this.Poster = poster;
        this.Type = type;
        this.Rated = rate;
        this.Country = country;
        this.Released = released;
        this.Runtime = runtime;
        this.Genre = genre;
        this.Director = director;
        this.Plot = plot;
        this.Writer = writer;
        this.Language = language;
        this.Awards = awards;
        this.Metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.Actors = actors;
    }

    public MovieDetail() {
    }

    protected MovieDetail(Parcel in) {
        imdbID = Objects.requireNonNull(in.readString());
        Title = in.readString();
        Year = in.readString();
        Poster = in.readString();
        Type = in.readString();
        Rated = in.readString();
        Country = in.readString();
        Released = in.readString();
        Runtime = in.readString();
        Genre = in.readString();
        Director = in.readString();
        Plot = in.readString();
        Writer = in.readString();
        Language = in.readString();
        Awards = in.readString();
        Metascore = in.readString();
        imdbRating = in.readString();
        imdbVotes = in.readString();
        Actors = in.readString();

    }


    public static final Creator<MovieDetail> CREATOR = new Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel in) {
            return new MovieDetail(in);
        }

        @Override
        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imdbID);
        dest.writeString(Title);
        dest.writeString(Year);
        dest.writeString(Poster);
        dest.writeString(Type);
        dest.writeString(Rated);
        dest.writeString(Country);
        dest.writeString(Released);
        dest.writeString(Runtime);
        dest.writeString(Genre);
        dest.writeString(Director);
        dest.writeString(Country);
        dest.writeString(Plot);
        dest.writeString(Writer);
        dest.writeString(Language);
        dest.writeString(Awards);
        dest.writeString(Metascore);
        dest.writeString(imdbRating);
        dest.writeString(imdbVotes);
        dest.writeString(Actors);
    }

    @Override
    public String toString() {
        return "MovieDetail{" +
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

    @NonNull
    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(@NonNull String imdbID) {
        this.imdbID = imdbID;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }
//
    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
//
    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }
}















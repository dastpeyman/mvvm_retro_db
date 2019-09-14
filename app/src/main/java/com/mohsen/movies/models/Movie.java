package com.mohsen.movies.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.Objects;

@Entity(tableName = "movies")
public class Movie implements Parcelable{

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

//    @ColumnInfo(name = "ingredients")
//    private String[] ingredients;
//
//    @ColumnInfo(name = "timestamp")
//    private int timestamp;


    public Movie(@NonNull String imdbID, String title, String year, String poster,
                 String type) {
        this.imdbID = imdbID;
        this.Title = title;
        this.Year = year;
        this.Poster = poster;
        this.Type = type;
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        imdbID = Objects.requireNonNull(in.readString());
        Title = in.readString();
        Year = in.readString();
        Poster = in.readString();
        Type = in.readString();
//        ingredients = in.createStringArray();
//        timestamp = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
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
    }

    @Override
    public String toString() {
        return "{" +
                "imdbID='" + imdbID + '\'' +
                ", Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", Poster='" + Poster + '\'' +
                ", Type='" + Type + '\'' +
                '}';
    }

    @NonNull
    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(@NonNull String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }
}















package com.example.sonthach.phim.Load;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("films")
    @Expose
    private Filmss films;
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("genre")
    @Expose
    private String genre;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    @SerializedName("posterURL")
    @Expose
    private String posterURL;

    public Movie(String name, String genre, String releaseDate, String posterURL) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterURL = posterURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }
}

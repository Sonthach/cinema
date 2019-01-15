package com.example.sonthach.phim.Load;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filmss {
    @SerializedName("films")
    @Expose
    List<Movie> movie;

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}

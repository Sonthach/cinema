package com.example.sonthach.phim.Load;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Filmss {
    @SerializedName("films")
    @Expose
    List<Movie> movie;

    @SerializedName("creatorId")
    @Expose
    private String creatorId;

    //@SerializedName("films")
    //private Movie movies;

    /*public Movie getMovies() {
        return movies;
    }

    public void setMovies(Movie movies) {
        this.movies = movies;
    }*/

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}

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

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("cinema")
    @Expose
    private Cinema cinema;

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



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

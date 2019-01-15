package com.example.sonthach.phim.model.response;

import com.example.sonthach.phim.model.ResponseMovie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Films{
    @SerializedName("films")
    @Expose
    List<ResponseMovie> movies;

    public Films(List<ResponseMovie> movies) {
        this.movies = movies;
    }

    public List<ResponseMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<ResponseMovie> movies) {
        this.movies = movies;
    }
}

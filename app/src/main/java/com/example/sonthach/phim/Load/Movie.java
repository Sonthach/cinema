package com.example.sonthach.phim.Load;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
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
    private long releaseDate;

    @SerializedName("posterURL")
    @Expose
    private String posterURL;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("creatorId")
    @Expose
    private String creatorId;

    public Movie(String name, String genre, long releaseDate, String posterURL) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterURL = posterURL;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }
}

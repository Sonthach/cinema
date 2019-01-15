package com.example.sonthach.phim;

public class Movie {
    private String name;
    private String genre;
    private String releaseDate;
    private String content;

    public Movie(String name, String genre, String releaseDate, String content) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.example.sonthach.phim.model;

import com.example.sonthach.phim.model.request.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class ResponseMovie {
    @SerializedName("_id")
    @Expose
    String id = null;

    @SerializedName("name")
    @Expose
    String name = null;

    @SerializedName("genre")
    @Expose
    String genre = null;

    @SerializedName("releaseDate")
    @Expose
    Timestamp releaseDate = null;

    @SerializedName("content")
    @Expose
    String content = null;

    @SerializedName("creatorId")
    @Expose
    String creatorId = null;

    @SerializedName("createdDate")
    @Expose
    Timestamp createdDate = null;

    @SerializedName("user")
    @Expose
    User user = null;

    @SerializedName("__v")
    @Expose
    int __v = 0;

    @SerializedName("link")
    @Expose
    String link = null;

    @SerializedName("posterURL")
    @Expose
    String posterURL = null;

    public ResponseMovie(String id,
                         String name,
                         String gerne,
                         Timestamp releaseDate,
                         String content,
                         String creatorId,
                         Timestamp createdDate,
                         User user,
                         int __v, String link, String posterURL) {
        this.id = id;
        this.name = name;
        this.genre = gerne;
        this.releaseDate = releaseDate;
        this.content = content;
        this.creatorId = creatorId;
        this.createdDate = createdDate;
        this.user = user;
        this.__v = __v;
        this.link = link;
        this.posterURL = posterURL;
    }

    public ResponseMovie(String name, String genre, Timestamp releaseDate, String posterURL) {
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.posterURL = posterURL;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

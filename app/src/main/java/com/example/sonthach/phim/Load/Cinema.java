package com.example.sonthach.phim.Load;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cinema {
    @SerializedName("cinema")
    @Expose
    private Detail detail;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public class Detail {
        @SerializedName("_id")
        @Expose
        private String id;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("genre")
        @Expose
        private String genre;
        @SerializedName("releaseDate")
        @Expose
        private long releaseDate;
        @SerializedName("content")
        @Expose
        private String content;

        @SerializedName("posterURL")
        @Expose
        private String posterURL;

        @SerializedName("creatorId")
        @Expose
        private String creatorId;

        @SerializedName("user")
        @Expose
        private User users;

        public String getCreatorId() {
            return creatorId;
        }

        public void setCreatorId(String creatorId) {
            this.creatorId = creatorId;
        }

        public User getUsers() {
            return users;
        }

        public void setUsers(User users) {
            this.users = users;
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

        public long getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(long releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }



        public String getPosterURL() {
            return posterURL;
        }

        public void setPosterURL(String posterURL) {
            this.posterURL = posterURL;
        }
    }

}

package com.example.sonthach.phim.Load;

public class LoginRespone {
    private int status;
    private String token;
    private User user;

    public LoginRespone(int status, User user) {
        this.status = status;
        this.user = user;
    }

    public LoginRespone(String token1) {
        this.token = token1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

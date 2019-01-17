package com.example.sonthach.phim.Load;

public class User {
    private String _id;
    private String email;
    private String name;
    private String password;
    private String userlink;
    private int __v;

    public User(String id, String email, String name, String userlink) {
        this._id = id;
        this.email = email;
        this.name = name;
        this.userlink = userlink;
    }

    public User(String id){
        this._id = id;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserlink() {
        return userlink;
    }

    public void setUserlink(String userlink) {
        this.userlink = userlink;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}

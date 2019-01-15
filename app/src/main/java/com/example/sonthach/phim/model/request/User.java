package com.example.sonthach.phim.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName("_id")
    @Expose
    String _id= null;

    @SerializedName("name")
    @Expose
    String name= null;

    @SerializedName("password")
    @Expose
    String password= null;

    @SerializedName("email")
    @Expose
    String email= null;

    @SerializedName("userLink")
    @Expose
    String conuserLinktent= null;

    @SerializedName("__v")
    @Expose
    int __v= 0;

    public User(String _id, String name, String password, String email, String conuserLinktent, int __v) {
        this._id = _id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.conuserLinktent = conuserLinktent;
        this.__v = __v;
    }
}

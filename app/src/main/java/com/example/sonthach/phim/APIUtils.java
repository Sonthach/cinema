package com.example.sonthach.phim;

import com.example.sonthach.phim.APIService;
import com.example.sonthach.phim.RetrofitClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class APIUtils {
    public APIUtils() {}

    public static final String BASE_URL = "https://cinema-hatin.herokuapp.com/";

    public static APIService getAPIService(){
//        Gson gson = GsonBuilder()
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
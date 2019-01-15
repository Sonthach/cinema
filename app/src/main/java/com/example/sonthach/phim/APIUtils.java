package com.example.sonthach.phim;

import com.example.sonthach.phim.APIService;
import com.example.sonthach.phim.RetrofitClient;


public class APIUtils {
    public APIUtils() {}

    public static final String BASE_URL = "https://cinema-hatin.herokuapp.com/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}

package com.example.sonthach.phim;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;


public class RetrofitClient {
    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseUrl){
        Gson gson;
        gson = new GsonBuilder().setLenient().create();

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}

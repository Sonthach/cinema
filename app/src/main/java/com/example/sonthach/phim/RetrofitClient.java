package com.example.sonthach.phim;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;


public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static  RetrofitClient mInstance;
    public static Retrofit getClient(String baseUrl){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static  synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }
    public APIService getApi(){ //API is APIServices interface
        return retrofit.create(APIService.class);
    }
}

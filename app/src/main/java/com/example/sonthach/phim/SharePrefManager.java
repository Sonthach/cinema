package com.example.sonthach.phim;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sonthach.phim.Load.LoginRespone;

public class SharePrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private static SharePrefManager mInstance;
    private Context mCtx;

    private SharePrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized SharePrefManager getmInstance(Context mCtx){
        if(mInstance == null){
            mInstance = new SharePrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUsers(LoginRespone loginRespone){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("token",loginRespone.getToken());


    }

    public boolean isLogginIn(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return (sharedPreferences.getString("id","-1") != null);
    }

    public LoginRespone getUser(){
        SharedPreferences sharedPreferences =mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new LoginRespone(
                sharedPreferences.getString("token","-1"));
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

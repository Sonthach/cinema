package com.example.sonthach.phim.Load;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.IOException;

import okhttp3.RequestBody;

@GlideModule
public class ImageLoader extends AppGlideModule {

    public static ImageLoader mInstance;
    public ImageLoader(){
    }

    public static ImageLoader getInstance(){
        if(mInstance == null){
            mInstance = new ImageLoader();
        }
        return mInstance;
    }

    public void loadImage(Context context,String url, ImageView imageView,RequestOptions requestOptions){
        try{
            GlideApp.with(context).setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .centerCrop().into(imageView);

        }catch (Exception e){
        }
    }
}

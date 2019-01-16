package com.example.sonthach.phim;

import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.Load.LoginRespone;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.Call;
import retrofit2.http.Path;


public interface APIService {
    @Multipart
    @POST ("/api/cinema/")
    Call<ResponseBody> postFilm(
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part photo
            );

    @GET("api/cinema/")
    Call<Filmss> getAllMovie();

    @FormUrlEncoded
    @POST ("/api/auth/signup")
    Call<ResponseBody> signUp(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/api/auth/signin")
    Call<LoginRespone> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

}

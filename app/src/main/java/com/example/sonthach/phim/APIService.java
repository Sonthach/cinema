package com.example.sonthach.phim;

import com.example.sonthach.phim.Load.Cinema;
import com.example.sonthach.phim.Load.ErrorResponse;
import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.Load.LoginRespone;
import com.example.sonthach.phim.Load.Movie;
import com.example.sonthach.phim.Load.SignupResponse;
import com.example.sonthach.phim.Load.User;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @Multipart
    @POST("/api/cinema/")
    Call<ResponseBody> postfilmnoimage(
            @PartMap() Map<String, RequestBody> partMap
    );

    @GET("/api/cinema/")
    Call<Filmss> getAllMovie();

    @GET("/api/cinema/")
    Call<Movie> getMylistmovie();

    @FormUrlEncoded
    @POST ("/api/auth/signup")
    Call<SignupResponse> signUp(
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


    @GET("/api/cinema/{id}")
    Call<Cinema> productdetails(@Path("id") String id );

    @FormUrlEncoded
    @POST("/api/auth/user")
    Call<User> getUser(@Field("token") String token);

    @FormUrlEncoded
    @POST("/api/user/change-password")
    Call<ErrorResponse> changePassword(@Header("x-access-token") String token,
                                       @Field("oldPassword") String oldPassword,
                                       @Field("newPassword") String newPassword);

    @FormUrlEncoded
    @POST("/api/user/edit")
    Call<ErrorResponse> changeName(@Header("x-access-token") String token,
                                   @Field("name") String name);

    @Multipart
    @POST("/api/user/change-avatar")
    Call<ErrorResponse> changeAvaterUser(@Header("x-access-token") String token,
                                         @Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("/api/auth/reset-password")
    Call<ErrorResponse> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("/api/cinema/delete")
    Call<ErrorResponse> deleteMovie(@Header("x-access-token") String token,
                                    @Field("_id") String id);

    @Multipart
    @POST ("/api/cinema/edit")
    Call<ResponseBody> editMovie(
            @Header("x-access-token") String token,
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part photo
    );

    @Multipart
    @POST ("/api/cinema/edit")
    Call<ResponseBody> editMovienoImg(
            @Header("x-access-token") String token,
            @PartMap() Map<String, RequestBody> partMap
    );
}

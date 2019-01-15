package com.example.sonthach.phim;

import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.model.response.Films;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.Call;


public interface APIService {
    @Multipart
    @POST ("/api/cinema/")
    Call<ResponseBody> postFilm(
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part photo
            );

    @GET("api/cinema/")
    Call<Filmss> getAllMovie();


}

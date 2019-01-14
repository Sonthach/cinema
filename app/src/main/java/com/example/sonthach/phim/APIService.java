package com.example.sonthach.phim;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PartMap;
import retrofit2.Call;

public interface APIService {
    @Multipart
    @POST ("api/cinema/")
    Call<ResponseBody> PostFilm(
            @PartMap() Map<String, RequestBody> partMap
            );
}

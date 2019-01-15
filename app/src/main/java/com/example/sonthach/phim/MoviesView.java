package com.example.sonthach.phim;

import com.example.sonthach.phim.model.response.Films;

import java.util.List;

public interface MoviesView
{
    void showLoading();
    void hideLoading();
    void onGetResult(Films films);
    void onErrorLoading(String messege);
}

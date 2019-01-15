package com.example.sonthach.phim;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.model.request.Movie;
import com.example.sonthach.phim.model.response.Films;
import com.example.sonthach.phim.model.ResponseMovie;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachphimAcitivity extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<com.example.sonthach.phim.Load.Movie> movies = new ArrayList<>();
    private RecyclerAdapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private APIService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachphim);
        per();


        fab = findViewById(R.id.fttaophim);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DanhsachphimAcitivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        apiService = APIUtils.getAPIService();
        Call<Filmss> call;
        apiService.getAllMovie().enqueue(new Callback<Filmss>() {
            @Override
            public void onResponse(Call<Filmss> call, Response<Filmss> response) {
                if (!movies.isEmpty()) {
                    movies.clear();
                }

                movies = response.body().getMovie();
                adapter = new RecyclerAdapter(movies, DanhsachphimAcitivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Filmss> call, Throwable t) {

            }
        });
    }


    private void per() {
        if (checkSelfPermission(Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.INTERNET},
                    1);

        }

        if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    1);

        }
    }
}


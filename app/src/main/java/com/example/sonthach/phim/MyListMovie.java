package com.example.sonthach.phim;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.Load.Movie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyListMovie extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    Toolbar toolbar;
    Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movies = new ArrayList<>();
    private AdapterMyListMovie adapter;
    APIService apiService;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistmovie);

        per();
        toolbar = findViewById(R.id.tbdanhsachphim);
        actionBar();

        swipeRefreshLayout = findViewById(R.id.swip_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdanhsachphim);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyListMovie.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        onLoadingSwipRefresh("");

    }

    private void actionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

    @Override
    protected void onResume() {
        super.onResume();
        onLoadingSwipRefresh("");
    }

    private void loadAgainListFilms(String keyword) {
        SharedPreferences pre = getSharedPreferences("SaveToken", MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        final String getCreatorId = pre.getString("id", "");
        swipeRefreshLayout.setRefreshing(true);

        final APIService apiService;
        apiService = APIUtils.getAPIService();
        apiService.getAllMovie().enqueue(new Callback<Filmss>() {
            @Override
            public void onResponse(Call<Filmss> call, Response<Filmss> response) {
                Filmss filmss = response.body();
                    if (response.isSuccessful()) {
                        movies = response.body().getMovie();
                        adapter = new AdapterMyListMovie(movies, MyListMovie.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        adapter.filterList();
                        swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Filmss> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onRefresh() {
        loadAgainListFilms("");
    }

    private void onLoadingSwipRefresh(final String keyword) {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadAgainListFilms(keyword);
            }
        });
    }
}

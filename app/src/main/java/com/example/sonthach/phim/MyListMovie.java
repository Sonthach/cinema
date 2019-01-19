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

public class MyListMovie extends AppCompatActivity {
    Toolbar toolbar;
    Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movies = new ArrayList<>();
    private RecyclerAdapter adapter;
    APIService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylistmovie);
        per();

        toolbar = findViewById(R.id.tbdanhsachphim);
        actionBar();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewdanhsachphim);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyListMovie.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        loadAgainListFilms();

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
        loadAgainListFilms();
    }

    private void loadAgainListFilms(){
        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();
        final String getCreatorId = pre.getString("id","");

        final ProgressDialog progressDialog = new ProgressDialog(MyListMovie.this);
        progressDialog.setMessage("Đang tải dữ liệu...");
        progressDialog.show();
        APIService apiService;
        apiService = APIUtils.getAPIService();
        apiService.getAllMovie().enqueue(new Callback<Filmss>() {
            @Override
            public void onResponse(Call<Filmss> call, Response<Filmss> response) {
                Filmss filmss = response.body();
                if(response.isSuccessful()) {
                        movies = response.body().getMovie();
                        adapter = new RecyclerAdapter(movies, MyListMovie.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Filmss> call, Throwable t) {

            }
        });
    }

    private void loadDataa() {
        apiService.getAllMovie().enqueue(new Callback<Filmss>() {
            @Override
            public void onResponse(Call<Filmss> call, Response<Filmss> response) {

                if (!movies.isEmpty()) {
                    movies.clear();
                }

                movies = response.body().getMovie();
                adapter = new RecyclerAdapter(movies, MyListMovie.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Filmss> call, Throwable t) {

            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Tìm Phim ...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.setFilter(newText);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false,false);
        return true;
    }*/
}

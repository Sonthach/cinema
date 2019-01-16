package com.example.sonthach.phim;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import android.widget.Toolbar;

import com.bumptech.glide.annotation.GlideModule;
import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.Load.Movie;


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
    private List<Movie> movies = new ArrayList<>();
    private RecyclerAdapter adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private APIService apiService;
    private Toolbar toolbar;

    // 2 arraylist, 1 cai chinh de chua du lieu tra ve, cai con lai chua du lieu sau khi tim xong dung de hien thi
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhsachphimAcitivity.this,TaoPhim.class));
            }
        });
        apiService = APIUtils.getAPIService();
        Call<Filmss> call;

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
        APIService apiService;
        apiService = APIUtils.getAPIService();
        apiService.getAllMovie().enqueue(new Callback<Filmss>() {
            @Override
            public void onResponse(Call<Filmss> call, Response<Filmss> response) {
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

    private void loadDataa() {
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

    @Override
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
                List<Movie> mylist = new ArrayList<>();
                newText = newText.toLowerCase();
                    for(Movie model : movies)
                    {
                        String sName = model.getName().toLowerCase();
                        if(sName.contains(newText)) {
                            movies.add(model);
                        }

                    }
                adapter.setFilter(mylist);
                return false;
            }
        });
        searchMenuItem.getIcon().setVisible(false,false);
        return true;
    }

}


package com.example.sonthach.phim;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.Load.Movie;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhsachphimAcitivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    FloatingActionButton fab,ftuser;
    Toolbar toolbar;
    Context context;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movies = new ArrayList<>();
    private List<Movie> moviesFull = new ArrayList<>();
    private Movie movie;
    private AdapterListMovie adapter;
    private String TAG = MainActivity.class.getSimpleName();
    private APIService apiService;
    private SwipeRefreshLayout swipeRefreshLayout;

    // 2 arraylist, 1 cai chinh de chua du lieu tra ve, cai con lai chua du lieu sau khi tim xong dung de hien thi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachphim);

        per();
        Anhxa();
        onloadingSwipRefresh("");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhsachphimAcitivity.this,TaoPhimActivity.class));
            }
        });

        ftuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhsachphimAcitivity.this,ProfileUserActivity.class));
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.tbdanhsachphim);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tìm Phim");

        swipeRefreshLayout = findViewById(R.id.swip_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        ftuser = findViewById(R.id.ftuser);
        fab = findViewById(R.id.fttaophim);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DanhsachphimAcitivity.this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
        loadAgainListFilms("");
    }

    private void loadAgainListFilms(String keyword){
        swipeRefreshLayout.setRefreshing(true);
        APIService apiService;
        apiService = APIUtils.getAPIService();
        apiService.getAllMovie().enqueue(new Callback<Filmss>() {
            @Override
            public void onResponse(Call<Filmss> call, Response<Filmss> response) {
                if(response.isSuccessful()) {
                    movies = response.body().getMovie();
                    adapter = new AdapterListMovie(movies, DanhsachphimAcitivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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

    private void onloadingSwipRefresh(final String keyword){
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadAgainListFilms(keyword);
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
        searchView.setQueryHint("...");
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
    }

}



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
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class DanhsachphimAcitivity extends AppCompatActivity {
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private List<Movie> MoviesList = new ArrayList<>();
    private RecyclerAdapter adapter;
    private APIService apiService;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachphim);


        Per();


        fab = findViewById(R.id.fttaophim);


        apiService = APIUtils.getAPIService();
        apiService.getAllMovie().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()) {
                    MoviesList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                ThongBao.Toast(DanhsachphimAcitivity.this,"Không thể Load Data!");
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        RecyclerAdapter adapter = new RecyclerAdapter(MoviesList, DanhsachphimAcitivity.this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DanhsachphimAcitivity.this,TaoPhim.class));
            }
        });
    }
    private void Per(){
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

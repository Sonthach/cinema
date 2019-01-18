package com.example.sonthach.phim;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.sonthach.phim.Load.Cinema;
import com.example.sonthach.phim.Load.Movie;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity {
    TextView txtTenphim,txtTheloai,txtNgayphathanh,txtMota,txtNguoitao;
    Toolbar toolbar;
    ImageView poster;
    Context context;
    String mid ="";
    String mten ="";
    String mtheloai ="";
    long mngayphathanh;
    String mmota="";
    String mhinhanh ="";
    String mnguoitao = "";
    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        toolbar = findViewById(R.id.tbchitietphim);
        txtTenphim = findViewById(R.id.chitiet_tenphim);
        txtTheloai = findViewById(R.id.chitiet_theloai);
        txtNgayphathanh = findViewById(R.id.chitiet_ngayphathanh);
        txtMota = findViewById(R.id.chitiet_mota);
        poster = findViewById(R.id.poster_chitiet);
        txtNguoitao = findViewById(R.id.chitiet_nguoitao);
        actionBar();

        final String url ="https://cinema-hatin.herokuapp.com";
        final String movieId = getIntent().getExtras().getString("id");


        /*mten = movieId.getName();
        mtheloai = movie.getGenre();
        mngayphathanh = movie.getReleaseDate();
        mmota = movie.getContent();
        mhinhanh = url+movie.getPosterURL();
        mnguoitao = movie.getCreatorId();*/


        txtTenphim.setText("Tên Phim: "+mten);
        txtTheloai.setText("Thể loại: "+mtheloai);
        /*txtNgayphathanh.setText("Ngày phát hành: "+dateString);*/
        txtNguoitao.setText("Người tạo phim: "+mnguoitao);
        txtMota.setText("Mô tả: "+mmota);

        Picasso.with(getApplicationContext()).load(url+mhinhanh).placeholder(R.drawable.filmnon).
                error(R.drawable.filmnon).into(poster);
        apiService = APIUtils.getAPIService();
        apiService.productdetails(movieId).enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                Cinema cinema = response.body();
                if(response.isSuccessful()) {
                    txtTenphim.setText("Tên phim: "+cinema.getDetail().getName());
                    txtTheloai.setText("Thể loại: "+cinema.getDetail().getGenre());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = formatter.format(new Date(cinema.getDetail().getReleaseDate()));
                    txtNgayphathanh.setText("Ngày phát hành: "+dateString);
                    if(cinema.getDetail().getUsers() != null)
                    {
                        txtNguoitao.setText("Người tạo: "+cinema.getDetail().getUsers().getName());
                    }else {
                        txtNguoitao.setText("Người tạo: ");
                    }

                    txtMota.setText("Mô tả: "+cinema.getDetail().getContent());

                    Picasso.with(getApplicationContext()).load(url+cinema.getDetail().getPosterURL()).placeholder(R.drawable.filmnon).
                            error(R.drawable.filmnon).into(poster);

                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {

            }
        });
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

    private void getProductsMovie() {

    }
}

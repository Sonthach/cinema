package com.example.sonthach.phim;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.sonthach.phim.Load.Cinema;
import com.example.sonthach.phim.Load.ErrorResponse;
import com.example.sonthach.phim.Load.Filmss;
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
    Button btnsua,btnxoa;
    Toolbar toolbar;
    ImageView poster;
    Context context;
    String mid ="";
    String mten ="";
    String mtheloai ="";
    String mmota="";
    long mngayphathanh =0;
    String mhinhanh ="";
    String mnguoitao = "";
    APIService apiService;
    private Filmss filmss;


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
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        actionBar();
        deleteMovie();

        final String url ="https://cinema-hatin.herokuapp.com";
        final String movieId = getIntent().getExtras().getString("id");
        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();

        final String getId = pre.getString("id","");



        txtTenphim.setText("Tên Phim: "+mten);
        txtTheloai.setText("Thể loại: "+mtheloai);
        txtNguoitao.setText("Người tạo phim: "+mnguoitao);
        txtMota.setText("Mô tả: "+mmota);
        btnsua.setVisibility(View.GONE);
        btnxoa.setVisibility(View.GONE);
        Picasso.with(getApplicationContext()).load(url+mhinhanh).placeholder(R.drawable.filmnon).
                error(R.drawable.filmnon).into(poster);
        apiService = APIUtils.getAPIService();
        apiService.productdetails(movieId).enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                Cinema cinema = response.body();
                if(response.isSuccessful()) {
                    if(cinema.getDetail().getCreatorId().equals(getId)){
                        btnsua.setVisibility(View.VISIBLE);
                        btnxoa.setVisibility(View.VISIBLE);
                    }
                    mten = cinema.getDetail().getName();
                    mngayphathanh = cinema.getDetail().getReleaseDate();
                    mmota = cinema.getDetail().getContent();
                    mtheloai = cinema.getDetail().getGenre();

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

                    Picasso.with(getApplicationContext()).load(url+cinema.getDetail()
                            .getPosterURL())
                            .placeholder(R.drawable.filmnon)
                            .fit()
                            .centerInside()
                            .error(R.drawable.filmnon)
                            .into(poster);
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {

            }
        });
    }

    private void deleteMovie(){
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetails.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắn chắn muốn xóa phim ?");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận !");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
                        SharedPreferences.Editor editor = pre.edit();
                        String getToken = pre.getString("token","");
                        final String movieId = getIntent().getExtras().getString("id");
                        apiService = APIUtils.getAPIService();
                        apiService.deleteMovie(getToken,movieId).enqueue(new Callback<ErrorResponse>() {
                            @Override
                            public void onResponse(Call<ErrorResponse> call, Response<ErrorResponse> response) {
                                ErrorResponse errorResponse = response.body();
                                if(response.isSuccessful()){
                                    if(errorResponse.getStatus() == 200 ) {
                                        ThongBao.Toast(ProductDetails.this, "Xóa phim thành công!!");
                                        finish();
                                    }
                                } else {
                                    ThongBao.Toast(ProductDetails.this,"Xóa phim thất bại! Vui lòng thử lại.");
                                }
                            }

                            @Override
                            public void onFailure(Call<ErrorResponse> call, Throwable t) {

                            }
                        });
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String movieId = getIntent().getExtras().getString("id");

                Intent intent = new Intent(ProductDetails.this,EditMovie.class);
                intent.putExtra("id",movieId);
                intent.putExtra("name",mten);
                intent.putExtra("genre",mtheloai);
                intent.putExtra("releaseDate",mngayphathanh);
                intent.putExtra("content",mmota);
                startActivity(intent);
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

    @Override
    protected void onResume() {
        super.onResume();
        final String url ="https://cinema-hatin.herokuapp.com";
        final String movieId = getIntent().getExtras().getString("id");
        SharedPreferences pre = getSharedPreferences("SaveToken",MODE_PRIVATE);
        SharedPreferences.Editor editor = pre.edit();

        final String getId = pre.getString("id","");



        txtTenphim.setText("Tên Phim: "+mten);
        txtTheloai.setText("Thể loại: "+mtheloai);
        txtNguoitao.setText("Người tạo phim: "+mnguoitao);
        txtMota.setText("Mô tả: "+mmota);
        btnsua.setVisibility(View.GONE);
        btnxoa.setVisibility(View.GONE);
        Picasso.with(getApplicationContext()).load(url+mhinhanh).placeholder(R.drawable.filmnon).
                error(R.drawable.filmnon).into(poster);
        apiService = APIUtils.getAPIService();
        apiService.productdetails(movieId).enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                Cinema cinema = response.body();
                if(response.isSuccessful()) {
                    if(cinema.getDetail().getCreatorId().equals(getId)){
                        btnsua.setVisibility(View.VISIBLE);
                        btnxoa.setVisibility(View.VISIBLE);
                    }
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

                    Picasso.with(getApplicationContext()).load(url+cinema.getDetail()
                            .getPosterURL())
                            .placeholder(R.drawable.filmnon)
                            .fit()
                            .centerInside()
                            .error(R.drawable.filmnon)
                            .into(poster);
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {

            }
        });
    }
}

package com.example.sonthach.phim;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.sonthach.phim.Load.Movie;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDetails extends AppCompatActivity {
    TextView txtTenphim,txtTheloai,txtNgayphathanh,txtMota;
    Toolbar toolbar;
    ImageView poster;
    Context context;
    String mten ="";
    String mtheloai ="";
    long mngayphathanh;
    String mmota="";
    String mhinhanh ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        txtTenphim = findViewById(R.id.chitiet_tenphim);
        txtTheloai = findViewById(R.id.chitiet_theloai);
        txtNgayphathanh = findViewById(R.id.chitiet_ngayphathanh);
        txtMota = findViewById(R.id.chitiet_mota);
        poster = findViewById(R.id.poster_chitiet);

        String url ="https://cinema-hatin.herokuapp.com";
        Movie movie = (Movie) getIntent().getSerializableExtra("thongtinphim");
        mten = movie.getName();
        mtheloai = movie.getGenre();
        mngayphathanh = movie.getReleaseDate();
        mmota = movie.getContent();
        mhinhanh = movie.getPosterURL();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(movie.getReleaseDate()));
        txtTenphim.setText(mten);
        txtTheloai.setText(mtheloai);
        txtNgayphathanh.setText(dateString);
        txtMota.setText(mmota);


        Picasso.with(getApplicationContext()).load(url+mhinhanh).placeholder(R.drawable.filmnon).
                error(R.drawable.filmnon).into(poster);

        getProductDetails();
    }

    private void getProductDetails() {


    }

    private void Views() {

    }
}

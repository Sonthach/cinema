package com.example.sonthach.phim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.Load.Movie;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    private List<Movie> movies;
    Context context;

    public RecyclerAdapter(List<Movie> movie,Context context) {
        this.movies = movie;
        this.context = context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.filmnon);

        String url = "https://cinema-hatin.herokuapp.com";
        MyViewHolder holders = holder;
        Movie movie = movies.get(pos);
        holder.txtTenphim.setText("Tên phim: "+movie.getName());
        holder.txtTheloai.setText("Thể loại: "+movie.getGenre());

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(movie.getReleaseDate()));

        holder.txtNgayphathanh.setText("Ngày phát hành: \n"+dateString);
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url+movie.getPosterURL()).into(holder.poster);

    }
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView txtTenphim,txtTheloai,txtNgayphathanh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.ctposter);
            txtTenphim = itemView.findViewById(R.id.txttenphim);
            txtTheloai = itemView.findViewById(R.id.txttheloai);
            txtNgayphathanh = itemView.findViewById(R.id.txtngayphathanh);
        }
    }

    public void setFilter(List<Movie> listitem)
    {
        movies = new ArrayList<>();
        movies.addAll(listitem);
        notifyDataSetChanged();
    }
}

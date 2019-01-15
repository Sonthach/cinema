package com.example.sonthach.phim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.sonthach.phim.model.ResponseMovie;
import com.example.sonthach.phim.Load.Movie;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Movie> movies;
    Context context;

    public RecyclerAdapter(List<Movie> movie,Context context) {
        movies = movie;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos) {

        MyViewHolder holders = holder;
        Movie movie = movies.get(pos);
        holder.txtTenphim.setText("Tên phim: "+movie.getName());
        holder.txtTheloai.setText("Thể loại: "+movie.getGenre());
        holder.txtNgayphathanh.setText((CharSequence) "Ngày phát hành: "+movie.getReleaseDate());
        Glide.with(context).load(movie.getPosterURL()).into(holder.poster);
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
}

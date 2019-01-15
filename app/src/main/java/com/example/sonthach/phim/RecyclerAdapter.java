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
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Movie> Movie;
    private Context context;

    public RecyclerAdapter(List<com.example.sonthach.phim.Movie> movie, Context context) {
        Movie = movie;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_rv,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.txtTenphim.setText(Movie.get(i).getName());
        holder.txtTheloai.setText(Movie.get(i).getGenre());
        holder.txtNgayphathanh.setText(Movie.get(i).getReleaseDate());
        Glide.with(context).load(Movie.get(i).getPosterURL()).into(holder.poster);
    }



    @Override
    public int getItemCount() {
        return Movie.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}

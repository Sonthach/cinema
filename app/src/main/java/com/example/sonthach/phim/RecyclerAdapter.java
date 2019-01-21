package com.example.sonthach.phim;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.example.sonthach.phim.Load.Cinema;
import com.example.sonthach.phim.Load.Filmss;
import com.example.sonthach.phim.Load.ImageLoader;
import com.example.sonthach.phim.Load.Movie;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{
    List<Movie> movies;
    List<Movie> moviesSearch = new ArrayList<>();// List dùng để Search
    final Context context;


    public RecyclerAdapter(List<Movie> movie,Context context) {
        this.movies = movie;
        this.moviesSearch.addAll(movies);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_ui_danhsachphim,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int pos) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.filmnon);

        String url = "https://cinema-hatin.herokuapp.com";
        MyViewHolder holders = holder;
        Movie movie = moviesSearch.get(pos);
        holder.txtTenphim.setText(movie.getName());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis (movie.getReleaseDate());
        int year = calendar.get(Calendar.YEAR);
        /*holder.txtTheloai.setText(movie.getGenre()+"     -");*/

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(movie.getReleaseDate()));

        holder.txtNgayphathanh.setText(String.valueOf(year));
        ImageLoader.getInstance().loadImage(context,url+movie.getPosterURL(),holder.poster,requestOptions);
        //Glide.with(context).setDefaultRequestOptions(requestOptions).load(url+movie.getPosterURL()).into(holder.poster);

    }
    @Override
    public int getItemCount() {
        return moviesSearch.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView txtTenphim,txtTheloai,txtNgayphathanh;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.ctposter);
            txtTenphim = itemView.findViewById(R.id.txttenphim);
            //txtTheloai = itemView.findViewById(R.id.txttheloai);
            txtNgayphathanh = itemView.findViewById(R.id.txtngayphathanh);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ProductDetails.class);
                    intent.putExtra("id", moviesSearch.get(getAdapterPosition()).getId());
                    intent.putExtra("name", moviesSearch.get(getAdapterPosition()).getName());
                    intent.putExtra("genre", moviesSearch.get(getAdapterPosition()).getGenre());
                    intent.putExtra("releaseDate", moviesSearch.get(getAdapterPosition()).getReleaseDate());
                    intent.putExtra("content", moviesSearch.get(getAdapterPosition()).getContent());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    public void setFilter(String newText)
    {
        newText = newText.toLowerCase();
        if(newText.equals(""))
        {
            moviesSearch.clear();
            moviesSearch.addAll(movies);
            notifyDataSetChanged();
            return;
        }else
        {
            moviesSearch.clear();
            for(Movie model : movies)
            {
                String sName;
                if(model.getName() != null)
                {
                    sName = model.getName().toLowerCase();
                }else
                {
                    sName = "";
                }
                if(sName.contains(newText)) {
                    moviesSearch.add(model);
                    notifyDataSetChanged();
                }
            }
        }

    }
}

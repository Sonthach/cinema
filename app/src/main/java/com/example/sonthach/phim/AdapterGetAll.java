package com.example.sonthach.phim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterGetAll extends ArrayAdapter<Movie> {
    private Context context;
    private List<Movie> movies;

    public AdapterGetAll(@NonNull Context context, int resource, @NonNull List<Movie> objects) {
        super(context, resource, objects);
        this.context = context;
        this.movies = objects;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.custom_rv,parent,false);
        ImageView poster = itemView.findViewById(R.id.ctposter);
        TextView txtTenphim = itemView.findViewById(R.id.txttenphim);
        TextView txtTheloai = itemView.findViewById(R.id.txttheloai);
        TextView txtNgayphathanh = itemView.findViewById(R.id.txtngayphathanh);

        txtTenphim.setText(String.format("",movies.get(pos).getName()));
        txtTheloai.setText(String.format("",movies.get(pos).getGenre()));
        txtNgayphathanh.setText(String.format("",movies.get(pos).getReleaseDate()));
        Glide.with(context).load(movies.get(pos).getPosterURL()).into(poster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return itemView;
    }
}

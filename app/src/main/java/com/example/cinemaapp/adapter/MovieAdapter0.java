package com.example.cinemaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.MovieItemClickListener;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Phim;

import java.util.List;

public class MovieAdapter0 extends RecyclerView.Adapter<MovieAdapter0.MyViewHolder> {
    Context context;
    List<Phim> Data;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter0(Context context, List<Phim> data) {
        this.context = context;
        this.Data = data;

    }
    public MovieAdapter0(Context context, List<Phim> data, MovieItemClickListener listener) {
        this.context = context;
        this.Data = data;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie0,parent, false);

        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(Data.get(position).getTenPhim());
        Glide.with(context).load(Data.get(position).getImage()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return Data.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;
        private Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_movie_title0);
            imageView = itemView.findViewById(R.id.item_movie_image0);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(Data.get(getAdapterPosition()),imageView);
                }
            });
        }
    }
}

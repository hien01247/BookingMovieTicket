package com.example.bookingmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<Movie> Data;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, List<Movie> data, MovieItemClickListener listener) {
        this.context = context;
        this.Data = data;
        movieItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent, false);

        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(Data.get(position).getTitle());
        holder.imageView.setImageResource(Data.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return Data.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_movie_title);
            imageView = itemView.findViewById(R.id.item_movie_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(Data.get(getAdapterPosition()),imageView);
                }
            });
        }
    }
}

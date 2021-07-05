package com.example.cinemaapp;

import android.widget.ImageView;

import com.example.cinemaapp.model.Phim;

public interface MovieItemClickListener {
    void onMovieClick(Phim movie, ImageView movieImageView);
}

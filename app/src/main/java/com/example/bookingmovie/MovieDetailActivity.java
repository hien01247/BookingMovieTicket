package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MovieDetailActivity extends AppCompatActivity {
    private ImageView MovieThumnailImg, MovieConverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_tab;
    private Button booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        iniView();
        booking = (Button)findViewById(R.id.btn_booking);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void iniView(){
        play_tab = findViewById(R.id.play_tab);
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceID = getIntent().getExtras().getInt("imgURL");
        int imageCover = getIntent().getExtras().getInt("imgCover");
        MovieThumnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceID).into(MovieThumnailImg);
        MovieThumnailImg.setImageResource(imageResourceID);
        MovieConverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(MovieConverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description= findViewById(R.id.detail_movie_desc);
        MovieConverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_tab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
    }
}
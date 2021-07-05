package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.Database.BookingCinemaDatabase;
import com.example.cinemaapp.model.Phim;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MovieDetailActivity extends AppCompatActivity {
    private ImageView MovieThumnailImg;
    private YouTubePlayerView youTubePlayerView;
    private TextView tv_title,tv_description,tv_thoiluong,tv_ngaykhoichieu,tv_theloai,tv_kiemduyet;
    private Button booking;
    private DatabaseReference moviesRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Phim movie = (Phim) getIntent().getSerializableExtra("title");
        booking = (Button) findViewById(R.id.btn_booking);
        moviesRef = FirebaseDatabase.getInstance().getReference("Phim");
        iniView();
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DetailShowTime.class);
                intent.putExtra("movie",movie);
                startActivity(intent);
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);

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
        Phim movie = (Phim) getIntent().getSerializableExtra("title");
        String title = movie.getTenPhim();
        MovieThumnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(movie.getImage()).into(MovieThumnailImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(title);
        getSupportActionBar().setTitle(title);
        tv_description= findViewById(R.id.detail_movie_desc);
        tv_description.setText(movie.getNoiDung());
        tv_description.setMovementMethod(new ScrollingMovementMethod());
        tv_kiemduyet = findViewById(R.id.txtKiemDuyet);
        tv_kiemduyet.setText(movie.getKiemDuyet());
        tv_thoiluong = findViewById(R.id.txtThoiLuong);
        tv_thoiluong.setText(movie.getThoiLuong());
        tv_theloai = findViewById(R.id.txtTheLoai);
        tv_theloai.setText(movie.getTheLoai());
        tv_ngaykhoichieu = findViewById(R.id.txtKhoiChieu);
        tv_ngaykhoichieu.setText(movie.getNgayKhoiChieu());
        String trangthai = movie.getTinhTrang();
        if (trangthai.equals("0")){
            booking.setVisibility(View.INVISIBLE);
        }
        YouTubePlayerView youTubePlayerView = findViewById(R.id.detail_movie_trailer);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = movie.getIdtrailer();
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
    }
}
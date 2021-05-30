package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SliderDetailActivity extends AppCompatActivity {
    private TextView textView1;
    private ImageView imageView;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_detail);
        iniView();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void iniView() {
        String contentTitle = getIntent().getExtras().getString("content");
        int imageResourceID = getIntent().getExtras().getInt("imgcontent");
        String contentDetail = getIntent().getExtras().getString("detailcontent");

        imageView = findViewById(R.id.detail_slider_img);

        Glide.with(this).load(imageResourceID).into(imageView);

        imageView.setImageResource(imageResourceID);

        textView1 = findViewById(R.id.content_slider);
        textView1.setText(contentTitle);

        getSupportActionBar().setTitle(contentTitle);

        textView2= findViewById(R.id.detail_content);
        textView2.setText(contentDetail);
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
}
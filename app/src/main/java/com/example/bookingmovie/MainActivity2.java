package com.example.bookingmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        ImageView q = (ImageView) findViewById(R.id.imageView);
        TextView text = (TextView) findViewById(R.id.uitcinema);
        TextView text1 = (TextView) findViewById(R.id.copyright);
        q.startAnimation(animation);
        text.startAnimation(animation);
        text1.startAnimation(animation);
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
                finishscreen();
            }
        };
        Timer t = new Timer();
        t.schedule(task, 3000);
    }

    private void finishscreen() {
        this.finish();
    }
}
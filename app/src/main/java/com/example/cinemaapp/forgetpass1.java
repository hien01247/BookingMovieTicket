package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cinemaapp.Database.BookingCinemaDatabase;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

public class forgetpass1 extends AppCompatActivity {
    private Button  buttonContinue;
    private TextInputEditText textViewEmail;
    BookingCinemaDatabase DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass1);
        buttonContinue =findViewById(R.id.btn_Continue);
        textViewEmail = findViewById(R.id.txt_For_Email);
        DB = new BookingCinemaDatabase(getApplicationContext());
        DB.open();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Email xác nhận");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("UIT CINEMA","UIT CINEMA", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textViewEmail.getText().toString();
                Boolean check = DB.checkEmail(s);
                if (check==true){
                    int random = new Random().nextInt((999999 - 100000) + 1) + 100000;
                    String ma = String.valueOf(random);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(forgetpass1.this, "UIT CINEMA");
                    builder.setContentTitle("UIT CINEMA - Thay đổi mật khẩu");
                    builder.setContentText("Mã đổi mật khẩu của quý khách là " + ma + ". Vui lòng không cung cấp mã cho người khác");
                    builder.setSmallIcon(R.drawable.ic_launcher_background);
                    builder.setAutoCancel(true);
                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(forgetpass1.this);
                    managerCompat.notify(1, builder.build());
                        Intent intent =new Intent(forgetpass1.this,forgetpass2.class);
                        intent.putExtra("ma",ma);
                        intent.putExtra("email",s);
                        startActivity(intent);
                }
                else Toast.makeText(forgetpass1.this,"Email không hợp lệ!!!",Toast.LENGTH_SHORT).show();
            }
        });
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
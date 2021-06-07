package com.example.bookingmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.adapter.AdapterXuatChieu;
import com.example.bookingmovie.adapter.NgayChieuAdapter;
import com.example.bookingmovie.modelshowtime.NgayChieu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DetailShowTime extends AppCompatActivity {

    private RecyclerView rcvXuatChieu;
    private TextView tvTenPhim;
    private AdapterXuatChieu adapterXuatChieu;
    private RecyclerView rcvNgayChieu;
    private NgayChieuAdapter ngayChieuAdapter;
    private BookingCinemaDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_show_time);
        setNgayChieuRecycler();
        tvTenPhim = findViewById(R.id.tv_tphim);

        Intent intent = getIntent();
        String tenPhim = intent.getStringExtra("tenPhim");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(tenPhim);
        tvTenPhim.setText(tenPhim);
        database = new BookingCinemaDatabase(this);
        database.open();
        setDsXuatChieu(tenPhim);
        database.close();
    }

    private void setNgayChieuRecycler() {
        rcvNgayChieu = findViewById(R.id.rcv_ngaychieu);
        ngayChieuAdapter = new NgayChieuAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        rcvNgayChieu.setLayoutManager(gridLayoutManager);
        ngayChieuAdapter.setData(getListNgayChieu());
        rcvNgayChieu.setAdapter(ngayChieuAdapter);
    }

    // ArrayList<Date> mDateArray;
    private List<NgayChieu> getListNgayChieu() {
        Calendar calendar = Calendar.getInstance();
        // mDateArray = new ArrayList<>();
        List<NgayChieu> ls = new ArrayList<>();
        //ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();
            int day = date.getDay();
            String tenThu="";
            if(i==0){
                tenThu="hôm nay";
            }
            else{
                tenThu=getThu(day);
            }
            String str = DateFormat.format("dd/MM", date).toString();
            ls.add(new NgayChieu(tenThu, str));
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return ls;

    }


    private String getThu(int day) {
        switch (day) {
            case 1: {
                return "Thứ 2";
            }

            case 2: {
                return "Thứ 3";
            }
            case 3: {
                return "Thứ 4";
            }
            case 4: {
                return "Thứ 5";
            }
            case 5: {
                return "Thứ 6";
            }
            case 6: {
                return "Thứ 7";
            }
            case 0: {
                return "Chủ nhật";
            }
        }
        return "";
    }
    public void setDsXuatChieu(String tPhim){
        rcvXuatChieu=findViewById(R.id.rcv_xuatChieu);
        adapterXuatChieu =new AdapterXuatChieu(this);
        GridLayoutManager gridLayoutManagerxc= new GridLayoutManager(this,5);
        rcvXuatChieu.setLayoutManager(gridLayoutManagerxc);
        adapterXuatChieu.setData(database.getXuatChieu(tPhim));
        rcvXuatChieu.setAdapter(adapterXuatChieu);
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
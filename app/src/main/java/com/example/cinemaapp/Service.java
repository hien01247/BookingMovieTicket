package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.adapter.ChonDoUongAdapter;
import com.example.cinemaapp.adapter.MovieAdapter;
import com.example.cinemaapp.adapter.MovieAdapter0;
import com.example.cinemaapp.adapter.ThucUong;
import com.example.cinemaapp.model.DichVu;
import com.example.cinemaapp.model.Phim;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service extends AppCompatActivity {
    private RecyclerView rcvKhuyenMai;
    private TextView tvPrice;
    private TextView tvPhim;
    ChonDoUongAdapter chonDoUongAdapter;
    private DatabaseReference moviesRef;
    private Button btnDatVe;
    int tong;
    private List<DichVu> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        rcvKhuyenMai = findViewById(R.id.rcv_khuyenMai);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dịch vụ");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        tvPrice = findViewById(R.id.price);
        tvPhim = findViewById(R.id.tv_phim);
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String tenPhim = sharedPref.getString("tenphim", "");
        tvPhim.setText("Các loại dịch vụ đi kèm");

        moviesRef = FirebaseDatabase.getInstance().getReference("DichVu");
        moviesRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot movieDS : snapshot.getChildren()) {
                    String id= movieDS.child("id").getValue(Integer.class).toString();
                    String imgdichvu = movieDS.child("imgDichVu").getValue(String.class);
                    String tenDichVu = movieDS.child("tenDichVu").getValue(String.class);
                    String noidungDV = movieDS.child("noidungDV").getValue(String.class);
                    int soluong= movieDS.child("soluong").getValue(Integer.class);
                    int gia= movieDS.child("gia").getValue(Integer.class);
                    DichVu dv= new DichVu(id,imgdichvu,tenDichVu,noidungDV,soluong,gia);
                    list.add(dv);
                }
                chonDoUongAdapter = new ChonDoUongAdapter(getApplicationContext());
                chonDoUongAdapter.setOnSelectedChangedListener(this::setOnSelectedChanged);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                rcvKhuyenMai.setLayoutManager(linearLayoutManager);
                chonDoUongAdapter.setData(list);
                rcvKhuyenMai.setAdapter(chonDoUongAdapter);
                Intent intent = getIntent();
                tong = intent.getIntExtra("total", 0);
                tvPrice.setText(String.valueOf(tong) + " ₫");
                //datVe();
                btnDatVe = findViewById(R.id.btn_TTDV);
                btnDatVe.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                        //editor.putString("trangthaighe", strRePlace);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("tongtien", String.valueOf(tvPrice.getText()));
                        editor.putString("dichvu", tenDichVu);
                        editor.commit();
                        Intent iVe = new Intent(Service.this, Payment.class);
                        startActivity(iVe);
                    }
                });


            }

            public void setOnSelectedChanged(List<Integer> selects) {
                int total = 0;
                String dichVuTong = "";
                mSelects.clear();
                mSelects.addAll(selects);
                int tongthucuong = 0;
                int kthu = 0;
                for (int select : selects) {
                    tongthucuong = list.get(select).getGia();
                    total = total + tongthucuong;
                    String dichvu= list.get(select).getSoluong() + " X " + list.get(select).getTenDichVu() +";";
                    if (dichVuTong.contains(dichvu)==false)
                        dichVuTong = dichVuTong + dichvu;
                }
                total = total + tong;
                tenDichVu = dichVuTong;
                tvPrice.setText(String.valueOf(total) + " ₫");
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        //chonDoUongAdapter.setData(getListThucUong());


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

    public void datVe() {
        btnDatVe = findViewById(R.id.button);
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVe = new Intent(Service.this, Payment.class);
                startActivity(iVe);
            }
        });
    }

    String tenDichVu = "";
    private List<Integer> mSelects = new ArrayList<>();

    public void setOnSelectedChanged(List<Integer> selects) {
        int total = 0;
        String dichVu = "";
        mSelects.clear();
        mSelects.addAll(selects);
        int tongthucuong = 0;
        int kthu = 0;
        for (int select : selects) {
            tongthucuong = list.get(select).getSoluong() * list.get(select).getGia();
            total = total + tongthucuong;
            dichVu = dichVu + list.get(select).getSoluong() + " " + list.get(select).getTenDichVu();
        }
        total = total + tong;
        tenDichVu = dichVu;
        tvPrice.setText(String.valueOf(total) + " ₫");
    }
}
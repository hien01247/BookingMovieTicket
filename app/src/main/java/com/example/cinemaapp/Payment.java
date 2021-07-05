package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.adapter.PhuongThucThanhToanAdapter;
import com.example.cinemaapp.model.ThanhToan;
import com.example.cinemaapp.model.Ve;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Payment extends AppCompatActivity {
    private Button btnThanhToan;
    private TextView tvPhim;
    private TextView tvRap;
    private TextView tvPhong;
    private TextView tvGhe;
    private TextView tvNgayChieu;
    private TextView tvGio;
    private TextView tvDichVu;
    private TextView tvtongTien;
    private RecyclerView rcvPhuongThuc;
    private String idlichChieu;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;
    private DatabaseReference userRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thanh toán");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        setListPhuongThuc();
        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());


        AnhXa();
        SetThongTinVe();
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma = MaXacNhan();
                SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "username");
                userRef.child("diemTichLuy").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));
                            int diemtichluy =Integer.parseInt(String.valueOf(task.getResult().getValue()));
                            Ve ve= new Ve("1",mAuth.getCurrentUser().getUid(),idlichChieu, tvGhe.getText().toString(), tvDichVu.getText().toString(), ma, tvtongTien.getText().toString());
                            FirebaseDatabase.getInstance().getReference("Ve")
                                    .child(mAuth.getCurrentUser().getUid()).child(ma)
                                    .setValue(ve).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {      // Check if the user information was stored in the DB

                                    if (task.isSuccessful()) {
                                        String[] tien = tvtongTien.getText().toString().split(" ₫");
                                        int bonus = Integer.parseInt(tien[0]);
                                        userRef.child("diemTichLuy").setValue(diemtichluy + bonus / 1000);
                                        updateTrangThaiGhe();

                                        Intent i = new Intent(Payment.this, TicketDetailActivity.class);
                                        i.putExtra("mave", ma);
                                        startActivity(i);
                                    }

                                    else {

                                        Toast.makeText(getApplicationContext(), "Xảy ra lỗi ! Xin thử lại sau", Toast.LENGTH_LONG).show();

                                    }

                                }

                            });

                        }
                    }
                });
            }
        });

    }

    private void AnhXa() {
        tvPhim = findViewById(R.id.phim);
        tvRap = findViewById(R.id.rapPhim);
        tvPhong = findViewById(R.id.phong);
        tvGhe = findViewById(R.id.ghe);
        tvNgayChieu = findViewById(R.id.ngay);
        tvDichVu = findViewById(R.id.thucUong);
        tvGio = findViewById(R.id.gio);
        tvtongTien = findViewById(R.id.tongTien);
        btnThanhToan = findViewById(R.id.button);
    }

    private void SetThongTinVe() {
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String tenPhim = sharedPref.getString("tenphim", "");
        int phong = sharedPref.getInt("phong", 0);
        String rap = sharedPref.getString("rapphim", "");
        String ghe = sharedPref.getString("ghe", "");
        String ngay = sharedPref.getString("ngay", "");
        String gio = sharedPref.getString("gio", "");
        String thucuong = sharedPref.getString("dichvu", "");
        String tongtien = sharedPref.getString("tongtien", "");
        idlichChieu = sharedPref.getString("idLichChieu", "");
        tvPhim.setText(tenPhim);
        tvRap.setText(rap);
        tvPhong.setText(String.valueOf(phong));
        tvGhe.setText(ghe);
        tvGio.setText(gio);
        tvNgayChieu.setText(ngay);
        tvDichVu.setText(thucuong);
        tvtongTien.setText(tongtien);
    }

    private void updateTrangThaiGhe() {
        SharedPreferences sharedPref = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String id = sharedPref.getString("idLichChieu", "");
        String trangthai = sharedPref.getString("trangthaighe", "");
        Log.d("trang thai",trangthai);
        userRef1 = FirebaseDatabase.getInstance().getReference("LichChieu").child(id);
        userRef1.child("trangthai").setValue(trangthai);

    }

    private void setListPhuongThuc() {
        rcvPhuongThuc = findViewById(R.id.rcv_phuongThuc);
        PhuongThucThanhToanAdapter phuongThucThanhToanAdapter = new PhuongThucThanhToanAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvPhuongThuc.setLayoutManager(linearLayoutManager);
        phuongThucThanhToanAdapter.setData(getListThanhToan());
        rcvPhuongThuc.setAdapter(phuongThucThanhToanAdapter);
    }

    private List<ThanhToan> getListThanhToan() {
        List<ThanhToan> list = new ArrayList<>();
        list.add(new ThanhToan(R.drawable.logo_momo, "Ví MoMo"));
        list.add(new ThanhToan(R.drawable.visa, "Thẻ quốc tế (Visa, Master)"));
        list.add(new ThanhToan(R.drawable.atm, "Thể ngân hàng ATM"));
        return list;
    }

    private String chuoi = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    private String MaXacNhan() {
        String ma = "";
        for (int i = 0; i < 6; i++) {
            Random random = new Random();
            int x = random.nextInt(35);
            ma = ma + chuoi.charAt(x);
        }
        return ma;
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

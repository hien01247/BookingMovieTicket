package com.example.cinemaapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TicketsDetailActivity extends AppCompatActivity {
    private TextView tvtenphim;
    private TextView tvRap;
    private TextView tvDiaChi;
    private TextView tvthoigian;
    private TextView txtDes;
    private TextView tvngay;
    private TextView tvphong;
    private TextView tvGhe;
    private TextView tvdichVu;
    private TextView txtCode;
    private Button btnHome;
    private Button btnMap;
    private DatabaseReference userRef;
    private DatabaseReference userRef1;
    private DatabaseReference userRef2;
    private FirebaseAuth mAuth;
    String idLichChieu;
    int idPhim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vé của bạn");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        mAuth = FirebaseAuth.getInstance();


        AnhXa();
        Intent intent = getIntent();
        String mave = intent.getStringExtra("mave");
        userRef = FirebaseDatabase.getInstance().getReference("Ve").child(mAuth.getCurrentUser().getUid()).child(mave);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String viTriGhe =snapshot.child("viTriGhe").getValue(String.class);
                tvGhe.setText(viTriGhe);
                String dichVu =snapshot.child("dichVu").getValue(String.class);
                tvdichVu.setText(dichVu);
                idLichChieu =snapshot.child("idLichChieu").getValue(String.class);
                txtCode.setText(mave);
                userRef1 = FirebaseDatabase.getInstance().getReference("LichChieu").child(idLichChieu);
                userRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String rapphim =snapshot.child("rapphim").getValue(String.class);
                        tvRap.setText(rapphim);
                        String ngaychieu =snapshot.child("ngaychieu").getValue(String.class);
                        tvngay.setText(ngaychieu);
                        int phong =snapshot.child("phong").getValue(Integer.class);
                        tvphong.setText(String.valueOf(phong));
                        String thoigian =snapshot.child("thoigian").getValue(String.class);
                        tvthoigian.setText(thoigian);
                        idPhim =snapshot.child("idphim").getValue(Integer.class);
                        String des =snapshot.child("dinhdang").getValue(String.class);
                        txtDes.setText(des);
                        userRef2 = FirebaseDatabase.getInstance().getReference("Phim").child(String.valueOf(idPhim));
                        userRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String tenphim =snapshot.child("tenphim").getValue(String.class);
                                tvtenphim.setText(tenphim);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("tenrap",tvRap.getText().toString());
                startActivity(intent);
            }
        });
    }
    private  void AnhXa(){
        tvRap=findViewById(R.id.txtCinema);
        tvDiaChi=findViewById(R.id.txtAddress);
        tvtenphim=findViewById(R.id.txtTitle);
        tvthoigian=findViewById(R.id.txtTime);
        tvngay=findViewById(R.id.txtDate);
        tvphong=findViewById(R.id.txtRoom);
        tvGhe=findViewById(R.id.txtSeatPlaces);
        tvdichVu=findViewById(R.id.txtCombos);
        txtCode =findViewById(R.id.txtCode);
        btnHome=findViewById(R.id.btn_goHome);
        btnMap=findViewById(R.id.btn_goMap);
        txtDes=findViewById(R.id.txtDescription);
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
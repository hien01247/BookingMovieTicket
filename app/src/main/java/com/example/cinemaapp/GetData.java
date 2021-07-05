package com.example.cinemaapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemaapp.adapter.MovieAdapter;
import com.example.cinemaapp.adapter.MovieAdapter0;
import com.example.cinemaapp.model.LichChieu;
import com.example.cinemaapp.model.Phim;
import com.example.cinemaapp.model.Ve;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class GetData {
    public List<Phim> moviesList=new ArrayList<>();
    public List<LichChieu> lcList=new ArrayList<>();
    public List<Ve> veList=new ArrayList<>();
    private FirebaseAuth mAuth;

    public List<Phim> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Phim> moviesList) {
        this.moviesList = moviesList;
    }

    public List<LichChieu> getLcList() {
        return lcList;
    }

    public void setLcList(List<LichChieu> lcList) {
        this.lcList = lcList;
    }

    public List<Ve> getVeList() {
        return veList;
    }

    public void setVeList(List<Ve> veList) {
        this.veList = veList;
    }



    public List<Phim> getPhim(){
        DatabaseReference moviesRef;
        moviesRef = FirebaseDatabase.getInstance().getReference("Phim");
        moviesRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    for (DataSnapshot movieDS : task.getResult().getChildren()) {
                        String releaseDate = movieDS.child("ngaykhoichieu").getValue(String.class);
                        String id = movieDS.getKey();
                        String title = movieDS.child("tenphim").getValue(String.class);
                        String nd = movieDS.child("noidung").getValue(String.class);
                        String kiemduyet = movieDS.child("kiemduyet").getValue(String.class);
                        String theloai = movieDS.child("theloai").getValue(String.class);
                        String thoiluong = movieDS.child("thoiluong").getValue(String.class);
                        String idtrailer = movieDS.child("idtrailer").getValue(String.class);
                        String idImage= movieDS.child("imgphim").getValue(String.class);
                        String tinhtrang= movieDS.child("tinhtrang").getValue(String.class);
                        Phim movie = new Phim(id, title, nd, kiemduyet, releaseDate, theloai, thoiluong, idImage, tinhtrang,idtrailer);
                        moviesList.add(movie);
                    }
                }
            }
        });
        return moviesList;
    }

    public List<LichChieu> getLichChieu(){

        DatabaseReference moviesRef;
        moviesRef = FirebaseDatabase.getInstance().getReference("LichChieu");
        moviesRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                }
                else {
                    for (DataSnapshot movieDS : task.getResult().getChildren()) {
                        String id = movieDS.getKey();
                        String idphim= movieDS.child("idphim").getValue(Integer.class).toString();
                        String rapphim = movieDS.child("rapphim").getValue(String.class);
                        int phong= movieDS.child("phong").getValue(Integer.class);
                        String dinhdang = movieDS.child("dinhdang").getValue(String.class);
                        int gia= movieDS.child("gia").getValue(Integer.class);
                        String trangthai = movieDS.child("trangthai").getValue(String.class);
                        String ngaychieu = movieDS.child("ngaychieu").getValue(String.class);
                        String thoigian = movieDS.child("thoigian").getValue(String.class);
                        LichChieu lc = new LichChieu(id,idphim,rapphim,phong,dinhdang,gia,trangthai,ngaychieu,thoigian);
                        lcList.add(lc);
                    }
                }
            }
        });
        return lcList;
    }
    public List<Ve> getVe(){
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference moviesRef;
        moviesRef = FirebaseDatabase.getInstance().getReference("Ve").child(mAuth.getCurrentUser().getUid());


        moviesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot movieDS : snapshot.getChildren()) {
                    String mave = movieDS.getKey();
                    String id= movieDS.child("id").getValue(String.class);
                    String idKhachhang = movieDS.child("idKhachhang").getValue(String.class);
                    String idLichChieu = movieDS.child("idLichChieu").getValue(String.class);
                    String viTriGhe = movieDS.child("viTriGhe").getValue(String.class);
                    String dichVu = movieDS.child("dichVu").getValue(String.class);
                    String tongTien = movieDS.child("tongTien").getValue(String.class);
                    Ve ve = new Ve(id,idKhachhang,idLichChieu,viTriGhe,dichVu,mave,tongTien);
                    veList.add(ve);

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }

        });
        return veList;
    }
}

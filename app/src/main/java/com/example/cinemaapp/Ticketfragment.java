package com.example.cinemaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.Database.BookingCinemaDatabase;
import com.example.cinemaapp.adapter.TicketAdapter;
import com.example.cinemaapp.model.LichChieu;
import com.example.cinemaapp.model.Phim;
import com.example.cinemaapp.model.Ve;
import com.example.cinemaapp.model.VeUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Ticketfragment extends Fragment {
    private DatabaseReference userRef1;
    private DatabaseReference userRef;
    private DatabaseReference userRef2;
    private FirebaseAuth mAuth;
    public List<Phim> moviesList=new ArrayList<>();
    public List<LichChieu> lcList=new ArrayList<>();
    public List<Ve> veList=new ArrayList<>();
    List<VeUser> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ticket, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Login", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","Tên đăng nhập");

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.rcv_ticket);

        mAuth = FirebaseAuth.getInstance();

        userRef = FirebaseDatabase.getInstance().getReference("Ve").child(mAuth.getCurrentUser().getUid());
        userRef.addValueEventListener(new ValueEventListener() {
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
                userRef1 = FirebaseDatabase.getInstance().getReference("LichChieu");
                userRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot movieDS : snapshot.getChildren()) {
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
                        userRef2 = FirebaseDatabase.getInstance().getReference("Phim");
                        userRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot movieDS : snapshot.getChildren()) {
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
                                TicketAdapter ticketAdapter = new TicketAdapter(getContext(),veList,lcList,moviesList);
                                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
                                recyclerView.setAdapter(ticketAdapter);
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


        return v;
    }
}


package com.example.cinemaapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.Database.BookingCinemaDatabase;
import com.example.cinemaapp.adapter.MovieAdapter;
import com.example.cinemaapp.adapter.MovieAdapter0;
import com.example.cinemaapp.adapter.MoviefAdapter;
import com.example.cinemaapp.model.Phim;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Moviefragment extends Fragment implements MovieItemClickListener{
    private DatabaseReference moviesRef;
    private List<Phim> moviesList1=new ArrayList<>();
    private List<Phim> moviesList2=new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        tabLayout = v.findViewById(R.id.tabMovie);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.listmovie);

        moviesRef = FirebaseDatabase.getInstance().getReference("Phim");

        moviesList1 = new ArrayList<>();
        moviesList2 = new ArrayList<>();
        moviesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot movieDS : snapshot.getChildren()) {

                    String releaseDate = movieDS.child("ngaykhoichieu").getValue(String.class);
                    if (LocalDate.parse(releaseDate, formatter).isBefore(LocalDate.now())) {


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

                        moviesList1.add(movie);

                    }
                    else{
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

                        moviesList2.add(movie);
                    }

                }
                MovieAdapter movieAdapter1 = new MovieAdapter(getActivity(), moviesList1,this::onMovieClick);
                MovieAdapter0 movieAdapter2 = new MovieAdapter0(getActivity(), moviesList2,this::onMovieClick);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerView.setAdapter(movieAdapter1);

            }
            private void onMovieClick(Phim movie1, ImageView movieImageView) {
                Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
                intent.putExtra("title", movie1);
                /*intent.putExtra("imgURL",movie.getImage());
                intent.putExtra("imgCover",movie.getImage());
                intent.putExtra("noidung",movie.getNoiDung());
                intent.putExtra("thoiluong",movie.getThoiLuong());
                intent.putExtra("kiemduyet",movie.getKiemDuyet());
                intent.putExtra("ngaykhoichieu",movie.getNgayKhoiChieu());
                intent.putExtra("theloai",movie.getTheLoai());*/
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),movieImageView,"sharedName");
                startActivity(intent,options.toBundle());
                Toast.makeText(getActivity(),"Mở : " + movie1.getTenPhim(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Không load được", Toast.LENGTH_LONG).show();

            }

        });

        MovieAdapter movieAdapter1 = new MovieAdapter(getActivity(), moviesList1,this::onMovieClick);
        MovieAdapter0 movieAdapter2 = new MovieAdapter0(getActivity(), moviesList2,this::onMovieClick);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(movieAdapter1);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recyclerView.setAdapter(movieAdapter1);
                }
                else{
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                    recyclerView.setAdapter(movieAdapter2);
                }

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return v;
    }
    @Override
    public void onMovieClick(Phim movie1, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
        intent.putExtra("title", movie1);
                /*intent.putExtra("imgURL",movie.getImage());
                intent.putExtra("imgCover",movie.getImage());
                intent.putExtra("noidung",movie.getNoiDung());
                intent.putExtra("thoiluong",movie.getThoiLuong());
                intent.putExtra("kiemduyet",movie.getKiemDuyet());
                intent.putExtra("ngaykhoichieu",movie.getNgayKhoiChieu());
                intent.putExtra("theloai",movie.getTheLoai());*/
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),movieImageView,"sharedName");
        startActivity(intent,options.toBundle());
        Toast.makeText(getActivity(),"Mở : " + movie1.getTenPhim(),Toast.LENGTH_LONG).show();
    }
}

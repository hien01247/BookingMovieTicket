package com.example.cinemaapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cinemaapp.Database.BookingCinemaDatabase;
import com.example.cinemaapp.adapter.MovieAdapter;
import com.example.cinemaapp.adapter.MovieAdapter0;
import com.example.cinemaapp.model.KhuyenMai;
import com.example.cinemaapp.model.Phim;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MovieItemClickListener,SliderItemClickListener{
    private ViewPager2 viewPager2;
    private Handler sliderHandlder =new Handler();
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    private TextView suggestion;
    private DatabaseReference moviesRef;
    private DatabaseReference moviesRef1;
    private List<Phim> moviesList1=new ArrayList<>();
    private List<Phim> moviesList2=new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public HomeFragment(){
    }
    public HomeFragment(List<Phim> l1, List<Phim> l2){
        moviesList1.addAll(l1);
        moviesList2.addAll(l2);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager2 = v.findViewById(R.id.viewPagerSlide);
        tabLayout = v.findViewById(R.id.tabLayout);
        recyclerView =  (RecyclerView) v.findViewById(R.id.rv_movie);
        RecyclerView recyclerView1 = (RecyclerView) v.findViewById(R.id.recyclerview_id);

        moviesRef = FirebaseDatabase.getInstance().getReference("Phim");

        moviesList1 = new ArrayList<>();
        moviesList2 = new ArrayList<>();
        moviesRef.addValueEventListener(new ValueEventListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot movieDS : snapshot.getChildren()) {

                    // Get "Release Date"
                    String releaseDate = movieDS.child("ngaykhoichieu").getValue(String.class);

                    // Only load the movies which "Release Date" is after the actual date
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

                        // Create movie object and add it to the list
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

                        // Create movie object and add it to the list
                        Phim movie = new Phim(id, title, nd, kiemduyet, releaseDate, theloai, thoiluong, idImage, tinhtrang,idtrailer);

                        moviesList2.add(movie);
                    }

                }


                MovieAdapter0 movieAdapter2 = new MovieAdapter0(getActivity(), moviesList2,this::onMovieClick);
                MovieAdapter movieAdapter1 = new MovieAdapter(getActivity(), moviesList1,this::onMovieClick);
                recyclerView.setAdapter(movieAdapter1);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));


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
                //.makeText(getActivity(),"Mở : " + movie1.getTenPhim(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Không load được", Toast.LENGTH_LONG).show();
//
//                // Hide the progress bar
//                progressBar_ComingSoon.setVisibility(View.GONE);

            }

        });


        MovieAdapter movieAdapter1 = new MovieAdapter(getActivity(), moviesList1, this::onMovieClick);

        MovieAdapter0 movieAdapter2 = new MovieAdapter0(getActivity(), moviesList2, this::onMovieClick);

        recyclerView.setAdapter(movieAdapter1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition()==0){
                    recyclerView.setAdapter(movieAdapter1);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
                }
                else{
                    recyclerView.setAdapter(movieAdapter2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        List<KhuyenMai> sliderItems = new ArrayList<>();
        moviesRef1 = FirebaseDatabase.getInstance().getReference("KhuyenMai");
        moviesRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot movieDS : snapshot.getChildren()) {
                    String id = movieDS.getKey();
                    String title = movieDS.child("tenkhuyenmai").getValue(String.class);
                    String nd = movieDS.child("noidung").getValue(String.class);
                    String idImage= movieDS.child("imgkhuyenmai").getValue(String.class);

                    KhuyenMai km = new KhuyenMai(id, title, idImage,nd);

                        sliderItems.add(km);
                    }
                SliderAdapter sliderAdapter = new SliderAdapter(getContext(),sliderItems,viewPager2, this::onSliderClick);
                viewPager2.setAdapter(sliderAdapter);
                PromotionAdapter promotionAdapter = new PromotionAdapter(getContext(),sliderItems);
                recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerView1.setAdapter(promotionAdapter);
                recyclerView1.setNestedScrollingEnabled(false);
                }

            private void onSliderClick(KhuyenMai sliderItem, ImageView imageView) {
                Intent intent = new Intent(getActivity(),SliderDetailActivity.class);
                intent.putExtra("content",sliderItem.getTenKhuyenMai());
                intent.putExtra("detailcontent",sliderItem.getNoiDung());
                intent.putExtra("imgcontent",sliderItem.getImg());
                ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"sharedName");
                startActivity(intent,options1.toBundle());
                Toast.makeText(getActivity(),"Mở : " + sliderItem.getTenKhuyenMai(),Toast.LENGTH_LONG).show();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

//                Toast.makeText(getActivity(), R.string.problemLoadingMovies, Toast.LENGTH_LONG).show();
//
//                // Hide the progress bar
//                progressBar_ComingSoon.setVisibility(View.GONE);

            }

        });
        SliderAdapter sliderAdapter = new SliderAdapter(getContext(),sliderItems,viewPager2, this::onSliderClick);
        viewPager2.setAdapter(sliderAdapter);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=  1- Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandlder.removeCallbacks(sliderRunnable);
                sliderHandlder.postDelayed(sliderRunnable,1500);
            }
        });



//        List<KhuyenMai> lstPromotion = new ArrayList<>();
//        lstPromotion = sliderItems;

        PromotionAdapter promotionAdapter = new PromotionAdapter(getContext(),sliderItems);
        recyclerView1.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView1.setAdapter(promotionAdapter);
        recyclerView1.setNestedScrollingEnabled(false);

        return v;
    }
    @Override
    public void onSliderClick(KhuyenMai sliderItem, ImageView imageView) {
        Intent intent = new Intent(getActivity(),SliderDetailActivity.class);
        intent.putExtra("content",sliderItem.getTenKhuyenMai());
        intent.putExtra("detailcontent",sliderItem.getNoiDung());
        intent.putExtra("imgcontent",sliderItem.getImg());
        ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"sharedName");
        startActivity(intent,options1.toBundle());
        Toast.makeText(getActivity(),"Mở : " + sliderItem.getTenKhuyenMai(),Toast.LENGTH_LONG).show();
    }

    private  Runnable sliderRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

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

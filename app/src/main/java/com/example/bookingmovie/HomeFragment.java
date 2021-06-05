package com.example.bookingmovie;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements MovieItemClickListener,SliderItemClickListener{
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private  View view;
    private ViewPager2 viewPager2;
    private ViewPager2 viewPager22;
    private Handler sliderHandlder =new Handler();
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    public HomeFragment(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager2 = v.findViewById(R.id.viewPagerSlide);
        tabLayout = v.findViewById(R.id.tabLayout);
        recyclerView =  (RecyclerView) v.findViewById(R.id.rv_movie);


        List<Movie> movieList1 = new ArrayList<>();

        movieList1.add(new Movie("Lật mặt 24H",R.drawable.movie_latmat,R.drawable.cover_thienthanhomenh));
        movieList1.add(new Movie("Conan",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));
        movieList1.add(new Movie("Bàn tay quỷ",R.drawable.movie_bantayquy,R.drawable.cover_thienthanhomenh));
        movieList1.add(new Movie("Lật mặt 24H",R.drawable.movie_latmat,R.drawable.cover_thienthanhomenh));
        movieList1.add(new Movie("Conan",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));
        movieList1.add(new Movie("Bàn tay quỷ",R.drawable.movie_bantayquy,R.drawable.cover_thienthanhomenh));
        MovieAdapter movieAdapter1 = new MovieAdapter(getActivity(), movieList1, this::onMovieClick);


        List<Movie> movieList2 = new ArrayList<>();

        movieList2.add(new Movie("Lật mặt 24H",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));
        movieList2.add(new Movie("Conan",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));
        movieList2.add(new Movie("Bàn tay quỷ",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));
        movieList2.add(new Movie("Lật mặt 24H",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));
        movieList2.add(new Movie("Conan",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));
        movieList2.add(new Movie("Bàn tay quỷ",R.drawable.movie_conan,R.drawable.cover_thienthanhomenh));

        MovieAdapter movieAdapter2 = new MovieAdapter(getActivity(), movieList2, this::onMovieClick);

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

        List<SliderItem> sliderItems = new ArrayList<>();

        /*sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai,"Thành viên U22",getString(R.string.content1)));
        sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai1,"Thanh toán giảm 10% trên Momo",getString(R.string.content2)));
        sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai2,"Xem phim rinh quà",getString(R.string.content3)));
        sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai3,"Happy Monday",getString(R.string.content4)));*/


        sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai,"Thành viên U22",getString(R.string.content1)));
        sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai1,"Thanh toán giảm 10% trên Momo",getString(R.string.content2)));
        sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai2,"Xem phim rinh quà",getString(R.string.content3)));
        sliderItems.add(new SliderItem(R.drawable.panel_khuyen_mai3,"Happy Monday",getString(R.string.content4)));

        SliderAdapter sliderAdapter = new SliderAdapter(getActivity(),sliderItems,viewPager2, this::onSliderClick);
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



        List<Promotion> lstPromotion = new ArrayList<>();
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai,"Thành viên U22",getString(R.string.content1)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai1,"Thanh toán giảm 10% trên Momo",getString(R.string.content2)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai2,"Xem phim rinh quà",getString(R.string.content3)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai3,"Happy Monday",getString(R.string.content4)));
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        PromotionAdapter promotionAdapter = new PromotionAdapter(getContext(),lstPromotion);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(promotionAdapter);


        return v;
    }
    @Override
    public void onSliderClick(SliderItem sliderItem, ImageView imageView) {
        Intent intent = new Intent(getActivity(),SliderDetailActivity.class);
        intent.putExtra("content",sliderItem.getContent());
        intent.putExtra("detailcontent",sliderItem.getDetailcontent());
        intent.putExtra("imgcontent",sliderItem.getImage());
        ActivityOptions options1 = ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"sharedName");
        startActivity(intent,options1.toBundle());
        Toast.makeText(getActivity(),"Mở : " + sliderItem.getContent(),Toast.LENGTH_LONG).show();
    }

    private  Runnable sliderRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(getActivity(),MovieDetailActivity.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),movieImageView,"sharedName");
        startActivity(intent,options.toBundle());
        Toast.makeText(getActivity(),"Mở : " + movie.getTitle(),Toast.LENGTH_LONG).show();
    }
}

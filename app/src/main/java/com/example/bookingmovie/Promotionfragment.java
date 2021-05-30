package com.example.bookingmovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

public class Promotionfragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_promotion, container, false);
        List<Promotion> lstPromotion = new ArrayList<>();
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai,"Thành viên U22",getString(R.string.content1)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai1,"Thanh toán giảm 10% trên Momo",getString(R.string.content2)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai2,"Xem phim rinh quà",getString(R.string.content3)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai3,"Happy Monday",getString(R.string.content4)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai,"Thành viên U22",getString(R.string.content1)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai1,"Thanh toán giảm 10% trên Momo",getString(R.string.content2)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai2,"Xem phim rinh quà",getString(R.string.content3)));
        lstPromotion.add(new Promotion(R.drawable.panel_khuyen_mai3,"Happy Monday",getString(R.string.content4)));
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstPromotion);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }
}

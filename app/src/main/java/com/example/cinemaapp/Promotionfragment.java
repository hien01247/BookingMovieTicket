package com.example.cinemaapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.Database.BookingCinemaDatabase;
import com.example.cinemaapp.model.KhuyenMai;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Promotionfragment extends Fragment {
    private List<KhuyenMai> lstPromotion = new ArrayList<>();
    private DatabaseReference moviesRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_promotion, container, false);
        moviesRef = FirebaseDatabase.getInstance().getReference("KhuyenMai");
        moviesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot movieDS : snapshot.getChildren()) {
                    String id = movieDS.getKey();
                    String title = movieDS.child("tenkhuyenmai").getValue(String.class);
                    String nd = movieDS.child("noidung").getValue(String.class);
                    String idImage= movieDS.child("imgkhuyenmai").getValue(String.class);

                    KhuyenMai km = new KhuyenMai(id, title, idImage,nd);

                    lstPromotion.add(km);
                }

                RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
                PromotionAdapter promotionAdapter = new PromotionAdapter(getContext(),lstPromotion);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                recyclerView.setAdapter(promotionAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

//                Toast.makeText(getActivity(), R.string.problemLoadingMovies, Toast.LENGTH_LONG).show();
//
//                // Hide the progress bar
//                progressBar_ComingSoon.setVisibility(View.GONE);

            }

        });


        return v;
    }
}

package com.example.bookingmovie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Showtimefragment extends Fragment {
    private RecyclerView rcvLichChieu;
    private LichChieuAdapter lichChieuAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_showtime,container,false);
        rcvLichChieu=v.findViewById(R.id.rcv_lichchieu);
        lichChieuAdapter =new LichChieuAdapter(getContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        rcvLichChieu.setLayoutManager(linearLayoutManager);
        lichChieuAdapter.setData(getListLichChieu());
        rcvLichChieu.setAdapter(lichChieuAdapter);
        return v;
    }
    private List<LichChieu> getListLichChieu() {
        List<LichChieu> list =new ArrayList<>();
        list.add(new LichChieu(R.drawable.tthm,"THIÊN THẦN HỘ MỆNH","9/10",R.drawable.haid,"13:00","17:00","20:00"));
        list.add(new LichChieu(R.drawable.conan,"THÁM TỬ LỪNG DANH CONAN","9/10",R.drawable.haid,"15:00","19:00","20:00"));
        list.add(new LichChieu(R.drawable.bantay,"BÀN TAY CỦA QUỈ","8/10",R.drawable.bad,"15:00","19:00","21:00"));
        list.add(new LichChieu(R.drawable.ff,"FAST AND FURIOUS 9","9/10",R.drawable.bad,"13:00","19:00","20:00"));
        list.add(new LichChieu(R.drawable.tthm,"THIÊN THẦN HỘ MỆNH","9/10",R.drawable.haid,"13:00","17:00","20:00"));
        list.add(new LichChieu(R.drawable.conan,"THÁM TỬ LỪNG DANH CONAN","9/10",R.drawable.haid,"15:00","19:00","20:00"));
        list.add(new LichChieu(R.drawable.bantay,"BÀN TAY CỦA QUỈ","8/10",R.drawable.bad,"15:00","19:00","21:00"));
        list.add(new LichChieu(R.drawable.ff,"FAST AND FURIOUS 9","9/10",R.drawable.bad,"13:00","19:00","20:00"));
        list.add(new LichChieu(R.drawable.tthm,"THIÊN THẦN HỘ MỆNH","9/10",R.drawable.haid,"13:00","17:00","20:00"));
        list.add(new LichChieu(R.drawable.conan,"THÁM TỬ LỪNG DANH CONAN","9/10",R.drawable.haid,"15:00","19:00","20:00"));
        list.add(new LichChieu(R.drawable.bantay,"BÀN TAY CỦA QUỈ","8/10",R.drawable.bad,"15:00","19:00","21:00"));
        list.add(new LichChieu(R.drawable.ff,"FAST AND FURIOUS 9","9/10",R.drawable.bad,"13:00","19:00","20:00"));
        return list;
    }
}

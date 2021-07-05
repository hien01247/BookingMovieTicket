package com.example.cinemaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.cinemaapp.adapter.ThanhVienAdapter;
import com.example.cinemaapp.model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class Introduce extends AppCompatActivity {
    private RecyclerView rcvThanhVien;
    private ThanhVienAdapter adapterTeamSangLap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Giới thiệu");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        rcvThanhVien= findViewById(R.id.rcv_thanhvien);
        adapterTeamSangLap= new ThanhVienAdapter(this);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvThanhVien.setLayoutManager(linearLayoutManager);
        adapterTeamSangLap.setData(getList());
        rcvThanhVien.setAdapter(adapterTeamSangLap);
    }

    private List<ThanhVien> getList() {
        List<ThanhVien> list= new ArrayList<>();
        list.add(new ThanhVien("https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/huy.jpg?alt=media&token=3f1e59bf-3c9b-4b00-a458-9a7d6fc5bddb","Lê Quốc Huy","Năm sinh: 2000","Chuyên ngành: CNTT"));
        list.add(new ThanhVien("https://firebasestorage.googleapis.com/v0/b/cinemaapp-4a3d2.appspot.com/o/DichVu%2F200519460_522054542254493_5483455608024612081_n.jpg?alt=media&token=55f8bd05-5b5f-470f-859e-fc4c11c7d9aa","Nguyễn Thị Thu Hiền","Năm sinh: 2000","Chuyên ngành: CNTT"));
        list.add(new ThanhVien("https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/ha.jpg?alt=media&token=06bdf025-7e99-44f3-9486-492a85f09117","Phạm Ngọc Hà","Năm sinh: 2000","Chuyên ngành: CNTT"));
        list.add(new ThanhVien("https://firebasestorage.googleapis.com/v0/b/bookingcinema-eeafc.appspot.com/o/huong.jpg?alt=media&token=12f628f3-cbfb-4241-8516-e148a6f9114d","Trần Ngọc Hương","Năm sinh: 2000","Chuyên ngành: CNTT"));

        return list;
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

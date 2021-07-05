package com.example.cinemaapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.adapter.LichChieuAdapter;
import com.example.cinemaapp.model.LichChieu;
import com.example.cinemaapp.model.Phim;
import com.example.cinemaapp.modelshowtime.ThongTinLichChieu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Showtimefragment extends Fragment {
    private RecyclerView rcvLichChieu;
    private LichChieuAdapter lichChieuAdapter;
    private Spinner spnCatagory;
    private TextView tv_date;
    private List<ThongTinLichChieu> list= new ArrayList<>();
    private int mDate, mMonth, mYear;
    private GetData connectdata;
    private String rapphim;
    private List<LichChieu> lcList= new ArrayList<>();
    private List<Phim> phimList= new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_showtime,container,false);

        spnCatagory=v.findViewById(R.id.spn_catagory);
        String[] RapPhim={"UIT cinema Thủ Đức","UIT cinema Sinh Viên"};//android.R.layout.simple_spinner_item
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,RapPhim);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCatagory.setAdapter(adapter);
        spnCatagory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lcList.clear();
                phimList.clear();
                rapphim =spnCatagory.getSelectedItem().toString();
                SetThongTinLichChieu(rapphim,tv_date.getText().toString());
                SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("rapphim", rapphim);
                editor.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tv_date= v.findViewById(R.id.tv_date);
        final Calendar calendar=Calendar.getInstance();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
        tv_date.setText(simpleDateFormat.format(calendar.getTime()));
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear= calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog= new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        lcList.clear();
                        phimList.clear();
                        calendar.set(year,month,dayOfMonth);
                        tv_date.setText(simpleDateFormat.format(calendar.getTime()));
                        SetThongTinLichChieu(rapphim,tv_date.getText().toString());
                    }
                },mYear,mMonth,mDate);
                datePickerDialog.show();
            }
        });
        rapphim =spnCatagory.getSelectedItem().toString();
        rcvLichChieu= v.findViewById(R.id.rcv_lichchieu);

        return v;
    }

    private void SetThongTinLichChieu(String rapphim,String ngaychieu){
        lcList.clear();
        phimList.clear();
        lichChieuAdapter =new LichChieuAdapter(getContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvLichChieu.setLayoutManager(linearLayoutManager);
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
                    DatabaseReference moviesRef1;
                    moviesRef1 = FirebaseDatabase.getInstance().getReference("Phim");
                    moviesRef1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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
                                    phimList.add(movie);
                                }
                                lichChieuAdapter.setData(lcList,phimList,ngaychieu,rapphim);
                                rcvLichChieu.setAdapter(lichChieuAdapter);
                            }
                        }
                    });
                }
            }
        });

    }

}

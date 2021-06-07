package com.example.bookingmovie;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.Database.BookingCinemaDatabase;
import com.example.bookingmovie.adapter.AdapterCatagory;
import com.example.bookingmovie.adapter.LichChieuAdapter;
import com.example.bookingmovie.model.LichChieu;
import com.example.bookingmovie.modelshowtime.Catagory;
import com.example.bookingmovie.modelshowtime.NgayChieu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Showtimefragment extends Fragment {
    private RecyclerView rcvLichChieu;
    private LichChieuAdapter lichChieuAdapter;
    private Spinner spnCatagory;
    private AdapterCatagory adapterCatagory;
    private TextView tv_date;
    private int mDate, mMonth, mYear;
    private BookingCinemaDatabase database;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_showtime,container,false);
        spnCatagory=v.findViewById(R.id.spn_catagory);
        adapterCatagory= new AdapterCatagory(getContext(),R.id.tv_selected,getListCatagory());
        spnCatagory.setAdapter((adapterCatagory));
        //set chọn ngày tháng chiểu phim
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
                        calendar.set(year,month,dayOfMonth);
                        tv_date.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },mYear,mMonth,mDate);
                datePickerDialog.show();
            }
        });
        database = new BookingCinemaDatabase(getContext());
        database.open();
        rcvLichChieu= v.findViewById(R.id.rcv_lichchieu);
        lichChieuAdapter =new LichChieuAdapter(getContext());
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcvLichChieu.setLayoutManager(linearLayoutManager);
        lichChieuAdapter.setData(database.getThongTinLichChieu());
        rcvLichChieu.setAdapter(lichChieuAdapter);
        return  v;
    }
    private List<Catagory> getListCatagory() {
        List<Catagory> list=new ArrayList<>();
        list.add(new Catagory("UIT cinema sinh viên"));
        list.add(new Catagory("UIT cinema thủ đức"));
        list.add(new Catagory("UIT cinema quận 1"));
        return list;
    }
    private  List<NgayChieu> getListNgayChieu(){
        List<NgayChieu> ls=new ArrayList<>();
        ls.add(new NgayChieu("Thứ Hai","20"));
        ls.add(new NgayChieu("Thứ Ba","21"));
        ls.add(new NgayChieu("Thứ Tư","22"));
        ls.add(new NgayChieu("Thứ Năm","23"));
        ls.add(new NgayChieu("Thứ Sáu","24"));
        ls.add(new NgayChieu("Thứ Bảy","25"));
        ls.add(new NgayChieu("Chủ Nhật","26"));
        return ls;
    }
}

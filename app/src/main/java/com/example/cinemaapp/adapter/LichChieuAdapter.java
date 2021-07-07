package com.example.cinemaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.DetailShowTime;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.DichVu;
import com.example.cinemaapp.model.LichChieu;
import com.example.cinemaapp.model.Phim;
import com.example.cinemaapp.modelshowtime.ThongTinLichChieu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LichChieuAdapter extends RecyclerView.Adapter<LichChieuAdapter.LichChieuViewHolder> {
    private Context mContext;
    private List<ThongTinLichChieu> mListLichChieu=new ArrayList<>();


    private DatabaseReference moviesRef;
    public LichChieuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<LichChieu> lcLists, List<Phim> phimLists, String ngaychieu, String rapphim){
        for (LichChieu lc: lcLists){
            if (lc.getNgay().equals(ngaychieu) && lc.getRapPhim().equalsIgnoreCase(rapphim)){
                for(Phim movie: phimLists){
                    if (movie.getId().equals(lc.getIdMovie())){
                        ThongTinLichChieu tt = new ThongTinLichChieu(movie.getImage(), movie.getTenPhim(),movie.getKiemDuyet(),movie.getThoiLuong());
                        mListLichChieu.add(tt);
                    }
                }
            }
        }
        for (int i=0; i<mListLichChieu.size()-1; i++){
            for (int j=i+1; j<mListLichChieu.size();j++){
                if(mListLichChieu.get(i).getTenPhim().equals(mListLichChieu.get(j).getTenPhim())){
                    mListLichChieu.remove(j);
                }
            }
        }
        for (int i=0; i<mListLichChieu.size()-1; i++){
            for (int j=i+1; j<mListLichChieu.size();j++){
                if(mListLichChieu.get(i).getTenPhim().equals(mListLichChieu.get(j).getTenPhim())){
                    mListLichChieu.remove(j);
                }
            }
        }
        Log.d("hey",String.valueOf(mListLichChieu));
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LichChieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lichchieu,parent,false);
        return new LichChieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichChieuViewHolder holder, int position) {
        ThongTinLichChieu lichChieu = mListLichChieu.get(position);
        if(lichChieu==null){
            return;
        }
        //holder.imgPhim.setImageResource(lichChieu.getResourceImg());
        Glide.with(mContext).load(lichChieu.getResourceImg()).into(holder.imgPhim);
        holder.tvTenPhim.setText(lichChieu.getTenPhim());
        //holder.tvDanhGia.setText(lichChieu.getDanhGia());
        holder.tvDoiTuong.setText(lichChieu.getDoituong());
        //holder.tvNamSX.setText(lichChieu.getNamSX());
        holder.tvThoiGian.setText(lichChieu.getThoigian());
        //holder.img2d.setImageResource(lichChieu.getResourceDinhdangphim());
        // holder.img3d.setImageResource(lichChieu.getResourceDinhdangphim2());
        //holder.imgImax.setImageResource(lichChieu.getResourceDinhdangphim3());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moviesRef = FirebaseDatabase.getInstance().getReference("Phim");
                moviesRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot movieDS : snapshot.getChildren()) {
                            String tenphim = movieDS.child("tenphim").getValue(String.class);
                            if(tenphim.equals(holder.tvTenPhim.getText())){
                                String id = movieDS.getKey();
                                String title = movieDS.child("tenphim").getValue(String.class);
                                String nd = movieDS.child("noidung").getValue(String.class);
                                String kiemduyet = movieDS.child("kiemduyet").getValue(String.class);
                                String theloai = movieDS.child("theloai").getValue(String.class);
                                String ngaykhoichieu = movieDS.child("ngaykhoichieu").getValue(String.class);
                                String thoiluong = movieDS.child("thoiluong").getValue(String.class);
                                String idtrailer = movieDS.child("idtrailer").getValue(String.class);
                                String idImage= movieDS.child("imgphim").getValue(String.class);
                                String tinhtrang= movieDS.child("tinhtrang").getValue(String.class);
                                Phim movie = new Phim(id, title, nd, kiemduyet, ngaykhoichieu, theloai, thoiluong, idImage, tinhtrang,idtrailer);
                                Intent i = new Intent(mContext, DetailShowTime.class);
                                i.putExtra("movie", movie);
                                mContext.startActivity(i);
                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mListLichChieu != null){
            return mListLichChieu.size();
        }
        return 0;
    }

    public class LichChieuViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPhim;
        private TextView tvTenPhim;
        private TextView tvDoiTuong;
        private TextView tvThoiGian;
        /*private TextView tvDanhGia;
        private TextView tvNamSX;
        private ImageView img2d;
        private ImageView img3d;
        private ImageView imgImax;*/
        public LichChieuViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhim=itemView.findViewById(R.id.img_phim);
            tvTenPhim=itemView.findViewById(R.id.tv_tenphim);
            tvDoiTuong=itemView.findViewById(R.id.tv_doituong);
            tvThoiGian=itemView.findViewById(R.id.tv_thoigian);
            /*img2d=itemView.findViewById(R.id.img_2d);
            img3d=itemView.findViewById(R.id.img_3d);
            imgImax=itemView.findViewById(R.id.img_imax);
            tvDanhGia=itemView.findViewById(R.id.tv_danhgia);
            tvNamSX= itemView.findViewById(R.id.tv_namSx);*/
        }
    }
}

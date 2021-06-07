package com.example.bookingmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.DetailShowTime;
import com.example.bookingmovie.R;
import com.example.bookingmovie.modelshowtime.ThongTinLichChieu;


import java.util.List;

public class LichChieuAdapter extends RecyclerView.Adapter<LichChieuAdapter.LichChieuViewHolder> {
    private Context mContext;
    private List<ThongTinLichChieu> mListLichChieu;

    public LichChieuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<ThongTinLichChieu> list){
        this.mListLichChieu=list;
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
        holder.imgPhim.setImageResource(lichChieu.getResourceImg());
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
                Intent i = new Intent(mContext, DetailShowTime.class);
                i.putExtra("tenPhim", holder.tvTenPhim.getText());
                //holder.tvTenPhim.getText();
                mContext.startActivity(i);
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

package com.example.bookingmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LichChieuAdapter extends RecyclerView.Adapter<LichChieuAdapter.LichChieuViewHolder> {
    private Context mContext;
    private List<LichChieu> mListLichChieu;

    public LichChieuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<LichChieu> list){
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
        LichChieu lichChieu = mListLichChieu.get(position);
        if(lichChieu==null){
            return;
        }
        holder.imgPhim.setImageResource(lichChieu.getResourceImg());
        holder.tvTenPhim.setText(lichChieu.getTenPhim());
        holder.tvDanhGia.setText(lichChieu.getDanhGia());
        holder.img2d.setImageResource(lichChieu.getResourceDinhdangphim());
        holder.tvXc1.setText(lichChieu.getXc1());
        holder.tvXc2.setText(lichChieu.getXc2());
        holder.tvXc3.setText(lichChieu.getXc3());

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
        private TextView tvDanhGia;
        private ImageView img2d;
        private TextView tvXc1;
        private TextView tvXc2;
        private TextView tvXc3;
        public LichChieuViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhim=itemView.findViewById(R.id.img_phim);
            tvTenPhim=itemView.findViewById(R.id.txt_ticket_movie);
            tvDanhGia=itemView.findViewById(R.id.tv_danhgia);
            img2d=itemView.findViewById(R.id.img_2d);
            tvXc1=itemView.findViewById(R.id.tv_xc1);
            tvXc2=itemView.findViewById(R.id.tv_xc2);
            tvXc3=itemView.findViewById(R.id.tv_xc3);
        }
    }
}

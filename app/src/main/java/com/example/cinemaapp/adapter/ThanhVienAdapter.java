package com.example.cinemaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.ThanhVien;

import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.TeamViewHolder> {
    private Context mContext;
    private List<ThanhVien> mListTeam;

    public ThanhVienAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<ThanhVien> list){
        this.mListTeam=list;
        notifyDataSetChanged();
    }
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thanhvien,parent,false);
        return new ThanhVienAdapter.TeamViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.TeamViewHolder holder, int position) {
        ThanhVien user=mListTeam.get(position);
        if(user==null){
            return;
        }
        Glide.with(mContext).load(user.getAvatar()).into(holder.img);
        holder.tvTen.setText(user.getTen());
        holder.tvNamSinh.setText(user.getNamsinh());
        holder.tvCN.setText(user.getChuyennganh());
    }

    @Override
    public int getItemCount() {
        return mListTeam.size();
    }
    public class TeamViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView tvTen;
        private TextView tvNamSinh;
        private TextView tvCN;
        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_user);
            tvTen=itemView.findViewById(R.id.tv_name);
            tvNamSinh=itemView.findViewById(R.id.tv_ngaysinh);
            tvCN=itemView.findViewById(R.id.tv_chuyennganh);
        }
    }
}

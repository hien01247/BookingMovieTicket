package com.example.cinemaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.PayTicket;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.ThanhToan;

import java.util.List;

public class PhuongThucThanhToanAdapter extends RecyclerView.Adapter<PhuongThucThanhToanAdapter.PhuongThucThanhToanViewHodler>{
    private Context mContext;
    private List<ThanhToan> mThanhToan;
    private int row_index;
    public PhuongThucThanhToanAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<ThanhToan> list){
        this.mThanhToan=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhuongThucThanhToanViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phuong_thuc_thanh_toan,parent,false);
        return new PhuongThucThanhToanViewHodler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhuongThucThanhToanViewHodler holder, int position) {
        ThanhToan thanhToan=mThanhToan.get(position);
        holder.imgPhuongThuc.setImageResource(thanhToan.getImgThanhToan());
        holder.tvPhuongThuc.setText(thanhToan.getTenThanhToan());
        holder.tvPhuongThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                notifyDataSetChanged();
            }

        });

        if(row_index==position){
            //holder.itemView.setBackground(R.drawable.custom_vien);
            holder.imgSuccess.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.imgSuccess.setVisibility(View.INVISIBLE);
        }

        /*holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PayTicket.class);
                mContext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if(mThanhToan.size()!=0){
            return mThanhToan.size();
        }
        return 0;
    }

    public class PhuongThucThanhToanViewHodler extends RecyclerView.ViewHolder{
        private   ImageView imgPhuongThuc;
        private TextView tvPhuongThuc;
        private ImageView imgSuccess;
        private RelativeLayout relativeLayout;
        public PhuongThucThanhToanViewHodler(@NonNull View itemView) {
            super(itemView);
            imgPhuongThuc=itemView.findViewById(R.id.img_phuongthuc);
            tvPhuongThuc=itemView.findViewById(R.id.tv_phuongthuc);
            imgSuccess=itemView.findViewById(R.id.img_success);
            relativeLayout=itemView.findViewById(R.id.choosetype);
        }
    }
}

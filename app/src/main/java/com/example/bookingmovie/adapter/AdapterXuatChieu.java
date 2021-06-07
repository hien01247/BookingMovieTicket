package com.example.bookingmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.R;
import com.example.bookingmovie.modelshowtime.XuatChieu;

import java.util.List;

public class AdapterXuatChieu extends  RecyclerView.Adapter<AdapterXuatChieu.XuatChieuViewHoder>{
    private Context mContext;
    private List<XuatChieu> mListXuatChieu;

    public AdapterXuatChieu(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<XuatChieu> list){
        this.mListXuatChieu=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public XuatChieuViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xuachieu,parent,false);
        return new XuatChieuViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XuatChieuViewHoder holder, int position) {
        XuatChieu xuatChieu=mListXuatChieu.get(position);
        if(xuatChieu== null){
            return;
        }
        holder.tvXuatChieu.setText(xuatChieu.getXuatChieu());
    }

    @Override
    public int getItemCount() {
        if(mListXuatChieu!= null){
            return mListXuatChieu.size();
        }
        return 0;
    }

    public class XuatChieuViewHoder extends RecyclerView.ViewHolder{
        private TextView tvXuatChieu;
        public XuatChieuViewHoder(@NonNull View itemView) {
            super(itemView);
            tvXuatChieu = itemView.findViewById(R.id.tvXuatChieu);
        }
    }
}

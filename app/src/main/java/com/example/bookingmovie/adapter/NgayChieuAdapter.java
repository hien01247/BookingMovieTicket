package com.example.bookingmovie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookingmovie.R;
import com.example.bookingmovie.modelshowtime.NgayChieu;

import java.util.List;


public class NgayChieuAdapter extends RecyclerView.Adapter<NgayChieuAdapter.NgayChieUViewHolder> {
    private AdapterXuatChieu adapterXuatChieu;
    private RecyclerView rcvXuatChieu;
    private Context mContext;
    private List<NgayChieu> mListNgayChieu;
    private int row_index;

    public NgayChieuAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<NgayChieu> list){
        this.mListNgayChieu=list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NgayChieUViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngaychieu,parent,false);
        return new NgayChieUViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NgayChieUViewHolder holder, int position) {
        NgayChieu ngayChieu = mListNgayChieu.get(position);
        if(ngayChieu==null){
            return;
        }
        holder.tvThu.setText(ngayChieu.getTenThu());
        holder.tvNgay.setText(ngayChieu.getNgay());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index=position;
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            //holder.itemView.setBackground(R.drawable.custom_vien);
            holder.itemView.setBackgroundColor(Color.parseColor("#F44336"));
            holder.tvThu.setTextColor(Color.parseColor("#ffffff"));
            holder.tvNgay.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#00BCD4"));
            holder.tvThu.setTextColor(Color.parseColor("#000000"));
            holder.tvNgay.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        if(mListNgayChieu!=null){
            return mListNgayChieu.size();
        }
        return 0;
    }

    public class NgayChieUViewHolder extends RecyclerView.ViewHolder{
        private TextView tvThu;
        private TextView tvNgay;

        public NgayChieUViewHolder(@NonNull View itemView) {
            super(itemView);
            tvThu= itemView.findViewById(R.id.thu);
            tvNgay= itemView.findViewById(R.id.tv_ngay);
        }
    }

}

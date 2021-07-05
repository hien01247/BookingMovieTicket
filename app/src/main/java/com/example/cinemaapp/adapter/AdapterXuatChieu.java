package com.example.cinemaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.ChooseSeat;
import com.example.cinemaapp.Login;
import com.example.cinemaapp.R;
import com.example.cinemaapp.modelshowtime.SuatChieu;

import java.util.List;

public class AdapterXuatChieu extends  RecyclerView.Adapter<AdapterXuatChieu.XuatChieuViewHoder>{
    private Context mContext;
    private List<SuatChieu> mListSuatChieu;

    public AdapterXuatChieu(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<SuatChieu> list){
        this.mListSuatChieu =list;
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
        SuatChieu suatChieu = mListSuatChieu.get(position);
        if(suatChieu == null){
            return;
        }
        holder.tvXuatChieu.setText(suatChieu.getXuatChieu());
        holder.tvXuatChieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = mContext.getSharedPreferences("Login",Context.MODE_PRIVATE);
                if (sharedPreferences.getString("Login","false").equals("false")){
                    Intent intent = new Intent(mContext, Login.class);
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, ChooseSeat.class);
                    intent.putExtra("xuatChieu", holder.tvXuatChieu.getText());
                    mContext.startActivity(intent);}
            }
        });
    }
    @Override
    public int getItemCount() {
        if(mListSuatChieu != null){
            return mListSuatChieu.size();
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

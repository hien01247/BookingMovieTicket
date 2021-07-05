package com.example.cinemaapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.MovieDetailActivity;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Phim;

import java.util.List;

public class MoviefAdapter extends RecyclerView.Adapter<MoviefAdapter.MyViewHolder>{
    private Context mContext;
    private List<Phim> mData;
    public MoviefAdapter(Context mContext, List<Phim> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MoviefAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie_fragment,parent, false);

        return  new MoviefAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.txtMovie.setText(mData.get(position).getTenPhim());

            Glide.with(mContext).load(mData.get(position).getImage()).into(holder.imgMovie);
            holder.cardView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("title",mData.get(position).getTenPhim());
                intent.putExtra("imgURL",mData.get(position).getImage());
                intent.putExtra("noidung",mData.get(position).getNoiDung());
                intent.putExtra("thoiluong",mData.get(position).getThoiLuong());
                intent.putExtra("kiemduyet",mData.get(position).getKiemDuyet());
                intent.putExtra("ngaykhoichieu",mData.get(position).getNgayKhoiChieu());
                intent.putExtra("theloai",mData.get(position).getTheLoai());
                mContext.startActivity(intent);
            });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView txtMovie;
        private ImageView imgMovie;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMovie = itemView.findViewById(R.id.item_txt_movie);
            imgMovie = itemView.findViewById(R.id.item_img_movie);
            cardView = itemView.findViewById(R.id.cardview_id_movie);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

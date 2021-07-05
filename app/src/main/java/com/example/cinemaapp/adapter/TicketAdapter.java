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
import com.example.cinemaapp.R;
import com.example.cinemaapp.TicketDetailActivity;
import com.example.cinemaapp.TicketsDetailActivity;
import com.example.cinemaapp.model.LichChieu;
import com.example.cinemaapp.model.Phim;
import com.example.cinemaapp.model.Ve;
import com.example.cinemaapp.model.VeUser;
import com.example.cinemaapp.modelshowtime.ThongTinLichChieu;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
    private Context mContext;
    private List<VeUser> mData=new ArrayList<>();

    public TicketAdapter(Context mContext, List<Ve> veList, List<LichChieu> lcList, List<Phim>  movieList) {
        this.mContext = mContext;
        for (Ve ve: veList){
            for (LichChieu lc: lcList){
                if (lc.getId().equals(ve.getIdLichChieu())){
                    for(Phim movie: movieList){
                        if (movie.getId().equals(lc.getIdMovie())){
                            VeUser vu = new VeUser(movie.getImage(), movie.getTenPhim(),lc.getRapPhim(),lc.getNgay(),lc.getmGio(),String.valueOf(lc.getPhong()),ve.getViTriGhe(),ve.getDichVu(),ve.getTongTien(),ve.getMaVe());
                            this.mData.add(vu);
                        }
                    }
                }
            }

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ticket,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movie.setText(mData.get(position).getTenphim());
        holder.date.setText(mData.get(position).getNgaychieu());
        holder.seat.setText(mData.get(position).getVitrighe());
        holder.room.setText(mData.get(position).getPhong());
        Glide.with(mContext).load(mData.get(position).getImgphim()).into(holder.img);
        holder.time.setText(mData.get(position).getThoigian());
        holder.rap.setText(mData.get(position).getRap());
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, TicketsDetailActivity.class);
            intent.putExtra("mave",mData.get(position).getIdve());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView movie;
        private TextView date;
        private TextView seat;
        private TextView room;
        private ImageView img;
        private TextView time;
        private TextView rap;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            movie =(TextView) itemView.findViewById(R.id.txt_ticket_movie);
            date = (TextView) itemView.findViewById(R.id.txt_ticket_date);
            seat = (TextView) itemView.findViewById(R.id.txt_ticket_seat);
            room = (TextView) itemView.findViewById(R.id.txt_ticket_room);
            time = (TextView) itemView.findViewById(R.id.txt_ticket_time);
            rap = itemView.findViewById(R.id.txt_ticket_rap);
            img = itemView.findViewById(R.id.img_phim);
            cardView = itemView.findViewById(R.id.ticket_id);
        }
    }
}

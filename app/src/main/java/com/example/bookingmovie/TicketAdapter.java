package com.example.bookingmovie;

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

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.MyViewHolder> {
    private Context mContext;
    private List<Ticket> mData;

    public TicketAdapter(Context mContext, List<Ticket> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public TicketAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ticket,parent, false);
        return new TicketAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movie.setText(mData.get(position).getNameMovie());
        holder.date.setText(mData.get(position).getTime());
        holder.seat.setText(mData.get(position).getSeat());
        holder.room.setText(mData.get(position).getRoom());
        holder.img.setImageResource(mData.get(position).getImgMovie());
        holder.img.setOnClickListener(v -> {
            Intent intent = new Intent(mContext,TicketDetailActivity.class);
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
        CardView cardView;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            movie =(TextView) itemView.findViewById(R.id.txt_ticket_movie);
            date = (TextView) itemView.findViewById(R.id.txt_ticket_date);
            seat = (TextView) itemView.findViewById(R.id.txt_ticket_seat);
            room = (TextView) itemView.findViewById(R.id.txt_ticket_room);
            img = itemView.findViewById(R.id.img_phim);
            cardView = itemView.findViewById(R.id.ticket_id);
        }

    }
}

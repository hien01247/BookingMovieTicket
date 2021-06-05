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

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.MyViewHolder>{
    private Context mContext;
    private List<Promotion> mData;
    public PromotionAdapter(Context mContext, List<Promotion> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PromotionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cardview_item_promotion,parent, false);

        return  new PromotionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.tvTitlePromotion.setText(mData.get(position).getContent());
            holder.imgPromotion.setImageResource(mData.get(position).getImage());
            holder.cardView.setOnClickListener(v -> {
                Intent intent = new Intent(mContext,PromotionDetailActivity.class);
                intent.putExtra("TitlePromotion",mData.get(position).getContent());
                intent.putExtra("DetailPromotion",mData.get(position).getDetailcontent());
                intent.putExtra("ImgPromotion",mData.get(position).getImage());
                mContext.startActivity(intent);
            });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{
        private TextView tvTitlePromotion;
        private ImageView imgPromotion;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitlePromotion = itemView.findViewById(R.id.promotion_title);
            imgPromotion = itemView.findViewById(R.id.promotion_img);
            cardView = itemView.findViewById(R.id.cardview_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}

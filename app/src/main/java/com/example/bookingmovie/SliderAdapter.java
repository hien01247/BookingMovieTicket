package com.example.bookingmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {
    private Context context;
    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;
    SliderItemClickListener sliderItemClickListener;
    SliderAdapter(Context context,List<SliderItem> sliderItems, ViewPager2 viewPager2,SliderItemClickListener sliderItemClickListener1){
        this.context = context;
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        sliderItemClickListener = sliderItemClickListener1;

    }
    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item,parent,false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
            holder.setImage(sliderItems.get(position));
            if (position == sliderItems.size()-2){
                viewPager2.post(runnable);
            }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;
        SliderViewHolder(@NonNull View itenView){
            super(itenView);
            imageView = itenView.findViewById(R.id.imageSlide);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sliderItemClickListener.onSliderClick(sliderItems.get(getAdapterPosition()),imageView);
                }
            });
        }
        void setImage(SliderItem sliderItem){
            imageView.setImageResource(sliderItem.getImage());
        }
    }
    private  Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}

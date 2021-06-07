package com.example.bookingmovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bookingmovie.R;
import com.example.bookingmovie.modelshowtime.Catagory;


import java.util.List;


public class  AdapterCatagory  extends ArrayAdapter<Catagory> {
    public AdapterCatagory(@NonNull Context context, int resource, @NonNull List<Catagory> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected,parent,false);
        TextView tvSelected=convertView.findViewById(R.id.tv_selected);
        Catagory catagory=this.getItem(position);
        if(catagory!=null){
            tvSelected.setText(catagory.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catagory,parent,false);
        TextView tvCatagory=convertView.findViewById(R.id.tv_catagory);
        Catagory catagory=this.getItem(position);
        if(catagory!=null){
            tvCatagory.setText(catagory.getName());
        }
        return convertView;
    }
}

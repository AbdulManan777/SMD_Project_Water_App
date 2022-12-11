package com.ass2.water_reminderapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context c;
    List<user_required_water_info> ls;

    public Adapter(Context c, List<user_required_water_info> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(c).inflate(R.layout.row,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.Serial_no.setText(String.valueOf(ls.get(position).getIndex()));

        holder.glass.setText(String.valueOf(ls.get(position).getNo_of_water_glasses()));

        holder.time.setText(ls.get(position).getTime().toString());








    }


    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Serial_no,glass,time,glass2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Serial_no = itemView.findViewById(R.id.serial_no);
            glass=itemView.findViewById(R.id.glass);
            time=itemView.findViewById(R.id.time1);
            glass2=itemView.findViewById(R.id.glass2);
        }
    }


}

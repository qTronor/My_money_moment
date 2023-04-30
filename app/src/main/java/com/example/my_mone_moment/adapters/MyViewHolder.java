package com.example.my_mone_moment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView type;
    private TextView amount;
    private TextView date;
    public MyViewHolder(View itemView) {
        super(itemView);
        type = itemView.findViewById(R.id.tvName);
        amount = itemView.findViewById(R.id.tvValue);
        date = itemView.findViewById(R.id.tvDate);
    }

    public void bind(String name_txt, String amount_txt, String date_txt) {
        type.setText(name_txt);
        amount.setText(amount_txt);
        date.setText(date_txt);
    }

    static MyViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_list, parent, false);
        return new MyViewHolder(view);
    }
}
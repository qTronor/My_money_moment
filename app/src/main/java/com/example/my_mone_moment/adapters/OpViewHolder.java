package com.example.my_mone_moment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.R;

public class OpViewHolder extends RecyclerView.ViewHolder {

    private final TextView opTypeView;
    private final TextView opAmountView;
    private final TextView opDateView;


    private OpViewHolder(View view){
        super(view);
        opTypeView = view.findViewById(R.id.tvName);
        opAmountView = view.findViewById(R.id.tvValue);
        opDateView = view.findViewById(R.id.tvDate);
    }

    public void bind(String type, String amount, String date){
        opTypeView.setText(type);
        opAmountView.setText(amount);
        opDateView.setText(date);
    }

    static OpViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_list, parent, false);
        return new OpViewHolder(view);
    }

}

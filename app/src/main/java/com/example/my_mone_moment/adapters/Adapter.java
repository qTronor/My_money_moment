package com.example.my_mone_moment.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.data.Operation;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ListAdapter<Operation, MyViewHolder> {

    public Adapter(@NonNull DiffUtil.ItemCallback<Operation> diffCallback) {
        super(diffCallback);
    }

    // This method creates a new ViewHolder object for each item in the RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return MyViewHolder.create(parent);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Operation current = getItem(position);
        holder.bind(current.getType(), current.getAmount(), current.getDate());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

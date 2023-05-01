package com.example.my_mone_moment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.data.Operation;
import com.example.my_mone_moment.R;

import java.util.ArrayList;

public class Adapter extends ListAdapter<Operation, OpViewHolder> {
    public Adapter(@NonNull DiffUtil.ItemCallback<Operation> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public OpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return OpViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull OpViewHolder holder, int position) {
        Operation current = getItem(position);
        holder.bind(current.getType(), current.getValue(), current.getDate());
    }

    public static class OpDiff extends DiffUtil.ItemCallback<Operation> {

        @Override
        public boolean areItemsTheSame(@NonNull Operation oldItem, @NonNull Operation newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Operation oldItem, @NonNull Operation newItem) {
            return oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getValue().equals(newItem.getValue()) &&
                    oldItem.getDate().equals(newItem.getDate());
        }
    }

}

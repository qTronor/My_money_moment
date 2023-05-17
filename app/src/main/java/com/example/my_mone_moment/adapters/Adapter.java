package com.example.my_mone_moment.adapters;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.data.Operation;
import com.example.my_mone_moment.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.OpViewHolder> {
    private static int position;

    private final LayoutInflater inflater;
    private List<Operation> operationList;
    public Adapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setOperations(List<Operation> operations){
        operationList = operations;
        notifyDataSetChanged();
    }

    public Operation getOperationAtPosition(int position) {
        return operationList.get(position);
    }


    @NonNull
    @Override
    public OpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.items_list, parent, false);
        return new OpViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OpViewHolder holder, int position) {
        if (operationList != null) {
            Operation current = operationList.get(position);
            holder.opTypeView.setText(current.getType());
            holder.opAmountView.setText(current.getValue());
            holder.opDateView.setText(current.getDate());
        } else {
            // Covers the case of data not being ready yet.
            holder.opTypeView.setText("No Word");
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(holder.getPosition());
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        if (operationList != null)
            return operationList.size();
        else return 0;

    }

    public static int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public static class OpViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        private final TextView opTypeView;
        private final TextView opAmountView;
        private final TextView opDateView;


        private OpViewHolder(View view){
            super(view);
            opTypeView = view.findViewById(R.id.tvName);
            opAmountView = view.findViewById(R.id.tvValue);
            opDateView = view.findViewById(R.id.tvDate);
            view.setOnCreateContextMenuListener(this);
        }

        /*public void bind(String type, String amount, String date){
            opTypeView.setText(type);
            opAmountView.setText(amount);
            opDateView.setText(date);
        }

        static OpViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.items_list, parent, false);
            return new OpViewHolder(view);
        }*/

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(Menu.NONE, R.id.delete_operation,
                    Menu.NONE, R.string.delete);
            contextMenu.add(Menu.NONE, R.id.delete_all_operation,
                    Menu.NONE, R.string.delete_all);
        }
    }
}

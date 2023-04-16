package com.example.my_mone_moment.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.data.Operation;
import com.example.my_mone_moment.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<Operation> oplist;

    public Adapter(ArrayList<Operation> oplist) {
        this.oplist = oplist;
    }

    // This method creates a new ViewHolder object for each item in the RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item and return a new ViewHolder object
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_list, parent, false);
        return new MyViewHolder(itemView);
    }

    // This method returns the total
    // number of items in the data set
    @Override
    public int getItemCount() {
        return oplist.size();
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Operation currentOp = oplist.get(position);
        holder.name.setText(currentOp.getName());
        holder.value.setText(currentOp.getValue() + "$");

        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(currentOp.getDate()));
        String formattedDate = DateFormat.format("dd/MM/yyyy", calendar).toString();*/
        holder.date.setText(currentOp.getDate());

    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView value;

        private TextView date;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            value = itemView.findViewById(R.id.tvValue);
            date = itemView.findViewById(R.id.tvDate);

        }
    }
}

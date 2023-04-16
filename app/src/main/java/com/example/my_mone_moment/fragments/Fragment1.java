package com.example.my_mone_moment.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.adapters.Adapter;
import com.example.my_mone_moment.data.Constants;
import com.example.my_mone_moment.data.Operation;
import com.example.my_mone_moment.R;
import com.example.my_mone_moment.animations.ViewAnimation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    boolean isRotateFloatBtn = false;
    TextView add_text, cancel_text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Dialog dialog = new Dialog(this.getContext());

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(view1 -> {
            isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
            //Snackbar.make(view1, "Fragment1", Snackbar.LENGTH_LONG)
                   //.setAction("Action", null).show();
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setContentView(R.layout.dialog_add);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                add_text = dialog.findViewById(R.id.add_text);
                cancel_text = dialog.findViewById(R.id.cancel_text);

                add_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Expence added", Toast.LENGTH_SHORT).show();
                    }
                });
                cancel_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        // getting the opList
        ArrayList<Operation> opList
                = Constants.getOperationData();
        // Assign opList to ItemAdapter
        Adapter itemAdapter = new Adapter(opList);
        // Set the LayoutManager that
        // this RecyclerView will use.
        RecyclerView recyclerView
                = view.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));
        // adapter instance is set to the
        // recyclerview to inflate the items.
        recyclerView.setAdapter(itemAdapter);
    }
}

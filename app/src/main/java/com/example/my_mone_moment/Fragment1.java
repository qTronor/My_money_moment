package com.example.my_mone_moment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Fragment1 extends Fragment {

    boolean isRotateFloatBtn = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(view1 -> {
            isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
            Snackbar.make(view1, "Fragment1", Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show();
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

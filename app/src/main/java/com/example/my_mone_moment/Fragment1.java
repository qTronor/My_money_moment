package com.example.my_mone_moment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Fragment1 extends Fragment {

    boolean isRotateFloatBtn = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(view1 -> {
            ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
            Snackbar.make(view1, "Fragment1", Snackbar.LENGTH_LONG)
                   .setAction("Action", null).show();

        });
        return view;
    }
}

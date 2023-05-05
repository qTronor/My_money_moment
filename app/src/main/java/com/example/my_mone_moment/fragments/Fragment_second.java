package com.example.my_mone_moment.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.my_mone_moment.R;
import com.example.my_mone_moment.animations.ViewAnimation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class Fragment_second extends Fragment {
    boolean isRotateFloatBtn = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);


        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(view1 -> {
            isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
            Snackbar.make(view1, "Fragment2", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        });
        return view;
    }
}
package com.example.my_mone_moment.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
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

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    boolean isRotateFloatBtn = false;
    TextView date_text;
    Button add_text, cancel_text;
    ImageButton calendarBtn;
    CalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Dialog expense_dialog = new Dialog(this.getContext());

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
                fab.setOnClickListener(view1 -> {
                    isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);

                    expense_dialog.setContentView(R.layout.dialog_add);
                    expense_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    expense_dialog.setCancelable(false);
                    expense_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                    add_text = expense_dialog.findViewById(R.id.addBtn);
                    cancel_text = expense_dialog.findViewById(R.id.cancelBtn);
                    date_text = expense_dialog.findViewById(R.id.dateInputEditText);
                    calendarBtn = expense_dialog.findViewById(R.id.calendarBtn);

                    add_text.setOnClickListener(view112 -> {
                        expense_dialog.dismiss();
                        Toast.makeText(getContext(), "Expense added", Toast.LENGTH_SHORT).show();
                        isRotateFloatBtn = ViewAnimation.rotateFab(view112, !isRotateFloatBtn);
                    });
                    cancel_text.setOnClickListener(v -> {
                        expense_dialog.dismiss();
                        Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                        isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
                    });
                    expense_dialog.show();

                    calendarBtn.setOnClickListener(view113 -> {
                       Dialog calendar_dialog = new Dialog(getContext());
                       CalendarView calendarView;

                        calendar_dialog.setContentView(R.layout.calendar_view);
                        calendar_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        calendar_dialog.setCancelable(false);
                        calendar_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                        calendarView = calendar_dialog.findViewById(R.id.calendar);

                        calendarView.setOnDateChangeListener((view11, year, month, dayOfMonth) -> {
                            String Date;
                            if(month < 9)
                                Date = dayOfMonth + "/0" + (month + 1) + "/" + year;
                            else
                                Date = dayOfMonth + "/" + (month + 1) + "/" + year;
                            date_text.setText(Date);
                            Toast.makeText(getContext(), "Date picked", Toast.LENGTH_SHORT).show();
                            calendar_dialog.dismiss();
                        });
                        calendar_dialog.show();
                    });
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

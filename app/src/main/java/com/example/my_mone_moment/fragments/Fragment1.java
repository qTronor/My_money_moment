package com.example.my_mone_moment.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import java.util.Calendar;

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
        Dialog dialog = new Dialog(this.getContext());

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
                fab.setOnClickListener(view1 -> {
                    isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);

                    dialog.setContentView(R.layout.dialog_add);
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.setCancelable(false);
                    dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                    add_text = dialog.findViewById(R.id.addBtn);
                    cancel_text = dialog.findViewById(R.id.cancelBtn);
                    date_text = dialog.findViewById(R.id.date_text);
                    calendarBtn = dialog.findViewById(R.id.calendarBtn);

                    add_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view1) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Expense added", Toast.LENGTH_SHORT).show();
                            isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
                        }
                    });
                    cancel_text.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                            isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
                        }
                    });
                    dialog.show();

                    calendarBtn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view1) {
                           Dialog dialog1 = new Dialog(getContext());
                           CalendarView calendarView;

                            dialog1.setContentView(R.layout.calendar_view);
                            dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            dialog1.setCancelable(false);
                            dialog1.getWindow().getAttributes().windowAnimations = R.style.animation;

                            calendarView = dialog1.findViewById(R.id.calendar);

                            calendarView
                                    .setOnDateChangeListener(
                                            new CalendarView
                                                    .OnDateChangeListener() {
                                                @Override
                                                public void onSelectedDayChange(
                                                        @NonNull CalendarView view1,
                                                        int year,
                                                        int month,
                                                        int dayOfMonth)
                                                {
                                                    String Date;
                                                    if(month < 9)
                                                        Date = dayOfMonth + "/0" + (month + 1) + "/" + year;
                                                    else
                                                        Date = dayOfMonth + "/" + (month + 1) + "/" + year;
                                                    date_text.setText(Date);
                                                    Toast.makeText(getContext(), "Date picked", Toast.LENGTH_SHORT).show();
                                                    dialog1.dismiss();
                                                }
                                            });
                            dialog1.show();
                        }
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

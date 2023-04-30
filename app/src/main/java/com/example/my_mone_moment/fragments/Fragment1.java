package com.example.my_mone_moment.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.adapters.Adapter;
import com.example.my_mone_moment.data.OpViewModel;
import com.example.my_mone_moment.data.Operation;
import com.example.my_mone_moment.R;
import com.example.my_mone_moment.animations.ViewAnimation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Fragment1 extends Fragment {

    boolean isRotateFloatBtn = false;

    private OpViewModel opViewModel;

    final Adapter adapter = new Adapter(new DiffUtil.ItemCallback<Operation>() {
        @Override
        public boolean areItemsTheSame(@NonNull Operation oldItem, @NonNull Operation newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Operation oldItem, @NonNull Operation newItem) {
            return (oldItem.getType().equals(newItem.getType()))
                    && (oldItem.getAmount().equals(newItem.getAmount()))
                    && (oldItem.getDate().equals(newItem.getDate()));
        }
    });



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Dialog expense_dialog = new Dialog(this.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get a ViewModel from the ViewModelProvider
        opViewModel = new ViewModelProvider(this.getActivity()).get(OpViewModel.class);

        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
                fab.setOnClickListener(view1 -> {
                    isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);

                    expense_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    expense_dialog.setContentView(R.layout.dialog_add);
                    expense_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    expense_dialog.setCancelable(false);
                    expense_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                    Button add_btn = expense_dialog.findViewById(R.id.addBtn);
                    Button cancel_btn = expense_dialog.findViewById(R.id.cancelBtn);
                    ImageView calendarBtn = expense_dialog.findViewById(R.id.calendarBtn);

                    TextView type_text = expense_dialog.findViewById(R.id.nameInputEditText);
                    TextView amount_text = expense_dialog.findViewById(R.id.amountInputEditText);
                    TextView date_text = expense_dialog.findViewById(R.id.dateInputEditText);


                    add_btn.setOnClickListener(view112 -> {
                        opViewModel.getAllOperations().observe(getViewLifecycleOwner(), operations -> {adapter.submitList(operations);});
                        if (TextUtils.isEmpty(type_text.getText()) ||
                                TextUtils.isEmpty(amount_text.getText()) ||
                                TextUtils.isEmpty(date_text.getText()))
                            Toast.makeText(getContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                        else{
                            Operation operation = new Operation(
                                    type_text.getText().toString(),
                                    amount_text.getText().toString(),
                                    date_text.getText().toString(),
                                    true);

                            opViewModel.insert(operation);

                            expense_dialog.dismiss();
                            isRotateFloatBtn = ViewAnimation.rotateFab(view112, !isRotateFloatBtn);
                            Toast.makeText(getContext(), "Expense added", Toast.LENGTH_SHORT).show();
                        }
                    });
                    cancel_btn.setOnClickListener(v -> {
                        expense_dialog.dismiss();
                        Toast.makeText(getContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                        isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);
                    });
                    expense_dialog.show();

                    calendarBtn.setOnClickListener(view113 -> {
                       Dialog calendar_dialog = new Dialog(getContext());
                       CalendarView calendarView;

                        calendar_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
}

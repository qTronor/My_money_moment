package com.example.my_mone_moment.fragments;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_mone_moment.adapters.Adapter;
import com.example.my_mone_moment.data.Operation;
import com.example.my_mone_moment.R;
import com.example.my_mone_moment.animations.ViewAnimation;
import com.example.my_mone_moment.data.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class Fragment_first extends Fragment {

    boolean isRotateFloatBtn = false;

    private ViewModel viewModel;

    //Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        //Adding RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        final Adapter adapter = new Adapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //Register the View to which the context menu should be associated
        registerForContextMenu(recyclerView);

        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getAllOperations().observe(getViewLifecycleOwner(), new Observer<List<Operation>>() {
            @Override
            public void onChanged(List<Operation> operationList) {
                adapter.setOperations(operationList);
            }
        });

        Dialog expense_dialog = new Dialog(this.getContext());


        FloatingActionButton fab = view.findViewById(R.id.floatingActionButton1);
        fab.setOnClickListener(view1 -> {
            //Change the state of floating btn
            isRotateFloatBtn = ViewAnimation.rotateFab(view1, !isRotateFloatBtn);

            //Create dialog
            expense_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            expense_dialog.setContentView(R.layout.dialog_add);
            expense_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            expense_dialog.setCancelable(false);
            expense_dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


            //Find all btn and editText on dialog
            Button add_btn = expense_dialog.findViewById(R.id.addBtn);
            Button cancel_btn = expense_dialog.findViewById(R.id.cancelBtn);
            ImageButton calendarBtn = expense_dialog.findViewById(R.id.calendarBtn);

            EditText type_text = expense_dialog.findViewById(R.id.nameInputEditText);
            EditText amount_text = expense_dialog.findViewById(R.id.amountInputEditText);
            EditText date_text = expense_dialog.findViewById(R.id.dateInputEditText);

            add_btn.setOnClickListener(view112 -> {

                String type = type_text.getText().toString().trim();
                String amount = amount_text.getText().toString().trim();
                String date = date_text.getText().toString().trim();

                if (type.length() == 0 || amount.length() == 0 || date.length() == 0)
                    Toast.makeText(getContext(), "Field are empty", Toast.LENGTH_SHORT).show();
                else {
                    //MyThread thread = new MyThread(new Operation(type, amount, date, true), viewModel);
                    //thread.runInsert();
                    viewModel.insert(new Operation(type, amount, date, true));

                    expense_dialog.dismiss();
                    Toast.makeText(getContext(), "Expense added", Toast.LENGTH_SHORT).show();
                    //Change the state of floating btn
                    isRotateFloatBtn = ViewAnimation.rotateFab(view112, !isRotateFloatBtn);
                }
            });
            cancel_btn.setOnClickListener(v -> {
                expense_dialog.dismiss();
                //Change the state of floating btn
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
                    if (month < 9)
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
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;

        try {
            position = Adapter.getPosition();
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }

        switch (item.getItemId()){
            case R.id.update_operation:
                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.delete_operation:
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.delete_all_operation:
                viewModel.deleteAll();

                Toast.makeText(getContext(), "All Operations are deleted", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}

package com.example.my_mone_moment.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "operation_table")
public class Operation implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int value;
    private String date;

    private boolean expense;



    public Operation(int id, String name, int value, String date, boolean expense) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.date = date;
        this.expense = expense;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean isExpense() {
        return expense;
    }

    public void setExpense(boolean expense) {
        this.expense = expense;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.example.my_mone_moment.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "operation_table")
public class Operation implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Operation type")
    private String type;
    private String value;
    private String date;
    private boolean expense;



    public Operation(@NonNull String type, String value, String date, boolean expense) {
        this.type = type;
        this.value = value;
        this.date = date;
        this.expense = expense;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }
    public boolean isExpense() {
        return expense;
    }

    public void setExpense(boolean expense) {
        this.expense = expense;
    }

}

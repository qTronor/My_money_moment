package com.example.my_mone_moment;

import java.io.Serializable;

public class Operation implements Serializable {
    private String name;
    private String value;
    private String date;

    private int iconResource;

    public Operation(String name, String value, String date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
    public int getIconResource() {
        return iconResource;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}

package com.example.my_mone_moment;

public class Operation {
    private String name;
    private int value;
    private int date;

    private int iconResource;

    public Operation(String name, int value, int date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getDate() {
        return date;
    }
    public int getIconResource() {
        return iconResource;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}

package com.test.data;

public class Item {
    private String name;
    private String date;
    private String size;
    private int isDirectory;

    public int getIsDirectory() {
        return isDirectory;
    }

    public void setIsDirectory(int isDirectory) {
        this.isDirectory = isDirectory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

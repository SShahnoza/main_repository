package com.test.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class ListOfItems {
    private String path;

    public ListOfItems(String path) {
        this.path = path;
    }

    public ArrayList<Item> data() {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        ArrayList<Item> items = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            Item item = new Item();
            BasicFileAttributes attr;
            Path path = Paths.get(listOfFiles[i].getPath());
            if (listOfFiles[i].isFile()) {
                item.setName(listOfFiles[i].getName());
                item.setSize(listOfFiles[i].length() / 1024 + " Kb");
                item.setIsDirectory(0);

                try {
                    attr = Files.readAttributes(path, BasicFileAttributes.class);
                    item.setDate(attr.creationTime().toString().substring(0, 10));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                items.add(item);
            } else {
                item.setName(listOfFiles[i].getName());
                item.setSize("");
                item.setIsDirectory(1);

                try {
                    attr = Files.readAttributes(path, BasicFileAttributes.class);
                    item.setDate(attr.creationTime().toString().substring(0, 10));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                items.add(item);
            }
        }
        return items;
    }
}
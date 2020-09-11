package com.geektech.mytaskapp.models;

public class Box {

    private String title;
    private String category;
    private String description;
    private int Thumbnail;

    public Box(String title, String category, String description, int thumbnail) {
        this.title = title;
        this.category = category;
        this.description = description;
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }
}
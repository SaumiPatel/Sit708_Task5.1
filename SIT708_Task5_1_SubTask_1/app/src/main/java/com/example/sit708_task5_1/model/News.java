package com.example.sit708_task5_1.model;

public class News {
    private String title;
    private String description;
    private int imageResource;
    private NewsType type;

    public enum NewsType {
        TOP_STORY,
        REGULAR_NEWS,
        RELATED_NEWS
    }

    public News(String title, String description, int imageResource, NewsType type) {
        this.title = title;
        this.description = description;
        this.imageResource = imageResource;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public NewsType getType() {
        return type;
    }
} 
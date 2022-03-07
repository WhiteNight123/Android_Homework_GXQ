package com.example.lv6plus;

public class Hero {
    private String name;

    private int imageId;

    public Hero(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}


package com.example.task6;

public class Lv4Body {
    private int imageId;
    private String bodyTitle;
    private String bodyContent;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBodyTitle() {
        return bodyTitle;
    }

    public void setBodyTitle(String bodyTitle) {
        this.bodyTitle = bodyTitle;
    }

    public String getBodyContent() {
        return bodyContent;
    }

    public void setBodyContent(String bodyContent) {
        this.bodyContent = bodyContent;
    }

    public Lv4Body(int imageId, String bodyTitle, String bodyContent) {
        this.imageId = imageId;
        this.bodyTitle = bodyTitle;
        this.bodyContent = bodyContent;
    }
}

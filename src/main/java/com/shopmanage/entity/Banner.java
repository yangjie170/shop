package com.shopmanage.entity;

public class Banner {
    private Picture photo;
    private String description;
    private String url;

    public Banner() {
    }

    public Banner(Picture photo, String description, String url) {
        this.photo = photo;
        this.description = description;
        this.url = url;
    }

    public Picture getPhoto() {
        return photo;
    }

    public void setPhoto(Picture photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

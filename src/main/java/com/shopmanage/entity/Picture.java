package com.shopmanage.entity;

public class Picture {
    private String small;
    private String thumb;
    private String url;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = "http://10.0.2.2:9001/"+small;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = "http://10.0.2.2:9001/"+thumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = "http://10.0.2.2:9001/"+url;
    }
}

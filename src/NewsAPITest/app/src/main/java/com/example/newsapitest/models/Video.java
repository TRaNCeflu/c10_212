package com.example.newsapitest.models;
/**
 * ... 视频类...
 */
public class Video {
    private String img;
    private String title;
    private String url;

    public Video( String img, String title, String url) {
        this.img = img;
        this.title = title;
        this.url = url;
    }

    public Video() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

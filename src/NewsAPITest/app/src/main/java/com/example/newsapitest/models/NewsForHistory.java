package com.example.newsapitest.models;
/**
 * ... 我的历史所存新闻id...
 */
public class NewsForHistory {
    public String id;
    public String title;

    public NewsForHistory(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public NewsForHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

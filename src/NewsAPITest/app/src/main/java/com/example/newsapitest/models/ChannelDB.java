package com.example.newsapitest.models;

/**
 * Created by Administrator on 2018/10/18.
 */

public class ChannelDB {
    public String id;
    public String name;

    public ChannelDB() {
    }

    public ChannelDB(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

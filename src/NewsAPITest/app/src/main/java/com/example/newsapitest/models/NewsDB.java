package com.example.newsapitest.models;

/**
 * C新闻类
 */

public class NewsDB {
    public String id;
    public String title;
    public String channelId;
    public String channelName;
    public String source;
    public Boolean havePic;
    //public String content;

    public NewsDB() {
    }


    public NewsDB(String channelId, String channelName, Boolean havePic, String id, String source, String title) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.havePic = havePic;
        this.id = id;
        this.source = source;
        this.title = title;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Boolean getHavePic() {
        return havePic;
    }

    public void setHavePic(Boolean havePic) {
        this.havePic = havePic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.example.newsapitest.models;

import java.util.List;
/**
 * ... 新闻类...
 */
public class News {
    public String id;
    public String title;
    public String channelId;
    public String channelName;
    public String source;
    public Boolean havePic;
    public List<NewsAPI.Imageurls> imageurl;

    public News(String id, String title, String channelId, String channelName, String source, Boolean havePic, List<NewsAPI.Imageurls> imageurl) {
        this.id = id;
        this.title = title;
        this.channelId = channelId;
        this.channelName = channelName;
        this.source = source;
        this.havePic = havePic;
        this.imageurl = imageurl;
    }

    public News() {

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getHavePic() {
        return havePic;
    }

    public void setHavePic(Boolean havePic) {
        this.havePic = havePic;
    }

    public List<NewsAPI.Imageurls> getImageurl() {
        return imageurl;
    }

    public void setImageurl(List<NewsAPI.Imageurls> imageurl) {
        this.imageurl = imageurl;
    }
}

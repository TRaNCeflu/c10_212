package com.example.newsapitest.models;

/**
 * Created by Administrator on 2018/10/19.
 */

public class NewsDetail {
    public String id;
    public String title;
    public String channelId;
    public String channelName;
    public String source;
    public Boolean havePic;
    public String html;
    public String content;
    public String pubdate;


    public NewsDetail() {
    }

    public NewsDetail(String channelId, String channelName, String content, Boolean havePic, String html, String id, String source, String title,String pubdate) {

        this.channelId = channelId;
        this.channelName = channelName;
        this.content = content;
        this.havePic = havePic;
        this.html = html;
        this.id = id;
        this.source = source;
        this.title = title;
        this.pubdate = pubdate;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getHavePic() {
        return havePic;
    }

    public void setHavePic(Boolean havePic) {
        this.havePic = havePic;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

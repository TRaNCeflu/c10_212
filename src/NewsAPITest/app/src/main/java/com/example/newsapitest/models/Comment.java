package com.example.newsapitest.models;

import java.util.Date;
import java.util.UUID;
/**
 * ... 评论类...
 */
public class Comment {
    String mName;
    String mTime;
    UUID mId;



    String mSite;
    String mBody;

    public Comment(String name,  String site, String body,String time) {
        mName = name;
        mTime = time;
        mId = UUID.randomUUID();
        mSite = site;
        mBody = body;
    }
    public Comment() {

    }
    public String getSite() {
        return mSite;
    }

    public void setSite(String site) {
        mSite = site;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }
}

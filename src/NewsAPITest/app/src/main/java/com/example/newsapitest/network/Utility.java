package com.example.newsapitest.network;

import android.content.Intent;

import com.example.newsapitest.models.ChannelAPI;
import com.example.newsapitest.models.ChannelDB;
import com.example.newsapitest.models.News;
import com.example.newsapitest.models.NewsAPI;
import com.example.newsapitest.models.NewsDB;
import com.example.newsapitest.models.NewsDetail;
import com.example.newsapitest.models.Video;
import com.example.newsapitest.models.VideoAPI;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析API数据
 * 用Gson类
 * 解析出来的数据都保存在类中
 */

public class Utility {
    public static List<ChannelDB> handleChannelForDB(String res){
        Gson gson  = new Gson();
        ChannelAPI channelAPI = gson.fromJson(res,ChannelAPI.class);
        List<ChannelDB> channelDBList  = new ArrayList<>();
        List<ChannelAPI.ChannelList> channelList  = channelAPI.getShowapi_res_body().getChannelList();
        for(int i = 0 ; i < channelList.size();i++){
            ChannelAPI.ChannelList tmp = channelList.get(i);
            channelDBList.add(new ChannelDB(tmp.getChannelId(),tmp.getName()));
            System.out.println(tmp.getChannelId()+"--"+tmp.getName());
        }
        return channelDBList;
    }

    public static List<NewsDB> handleNewsForDB(String res){
        Gson gson = new Gson();
        NewsAPI newsAPI = gson.fromJson(res,NewsAPI.class);
        List<NewsDB> newsDBList = new ArrayList<>();
        List<NewsAPI.Contentlist> contentlistList = newsAPI.getShowapi_res_body().getPagebean().getContentlist();
        for(int i = 0;i<contentlistList.size();i++){
            NewsAPI.Contentlist contentlist = contentlistList.get(i);
            if(contentlist.getContent() == ""){ continue;}  //如果新闻内容为空，则抛弃这条新闻
            newsDBList.add(new NewsDB(contentlist.getChannelId(),contentlist.getChannelName(),contentlist.getHavePic(),
                                                                contentlist.getId(),contentlist.getSource(),contentlist.getTitle()));
            //System.out.println(contentlist.getId()+"--"+contentlist.getTitle());
        }
        return newsDBList;
        //String channelId, String channelName, Boolean havePic, String id, String source, String title
    }

    public static NewsDetail handleNewsForDetail(String res){
        Gson gson = new Gson();
        NewsAPI newsAPI = gson.fromJson(res,NewsAPI.class);
        List<NewsAPI.Contentlist> contentlistList = newsAPI.getShowapi_res_body().getPagebean().getContentlist();
        NewsAPI.Contentlist contentlist = contentlistList.get(0);
        NewsDetail newsDetail = new NewsDetail(contentlist.getChannelId(),contentlist.getChannelName(),contentlist.getContent(),contentlist.getHavePic()
                                                                                ,contentlist.getHtml(),contentlist.getId(),contentlist.getSource(),contentlist.getTitle(),contentlist.getPubDate());
        return newsDetail;
    }

    public static List<News> handleNewsForList(String res){
        Gson gson = new Gson();
        NewsAPI newsAPI = gson.fromJson(res,NewsAPI.class);
        List<News> newsList = new ArrayList<>();
        List<NewsAPI.Contentlist> contentlistList = newsAPI.getShowapi_res_body().getPagebean().getContentlist();
        for(int i = 0;i<contentlistList.size();i++){
            NewsAPI.Contentlist contentlist = contentlistList.get(i);
            if(contentlist.getContent() == ""){ continue;}  //如果新闻内容为空，则抛弃这条新闻
            newsList.add(new News(contentlist.getId(),contentlist.getTitle(),contentlist.getChannelId(),contentlist.getChannelName(),
                                                    contentlist.getSource(),contentlist.getHavePic(),contentlist.getImageurls()));
            //System.out.println(contentlist.getId()+"--"+contentlist.getTitle());
        }
        return newsList;
    }

    public static int handleNewsAllPageForList(String res){
        Gson gson = new Gson();
        NewsAPI newsAPI = gson.fromJson(res,NewsAPI.class);
       // List<NewsAPI.Contentlist> contentlistList = newsAPI.getShowapi_res_body().getPagebean().getContentlist();
        return newsAPI.getShowapi_res_body().getPagebean().getAllPages();
    }
    public static List<Video> handleVideoForList(String res){
        Gson gson = new Gson();
        VideoAPI videoAPI = gson.fromJson(res,VideoAPI.class);
        List<Video> videoList = new ArrayList<>();
        if(videoAPI.getShowapi_res_body().getNumber0()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber0().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber0().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber0().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber1()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber1().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber1().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber1().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber2()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber2().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber2().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber2().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber3()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber3().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber3().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber3().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber4()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber4().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber4().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber4().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber5()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber5().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber5().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber5().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber6()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber6().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber6().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber6().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber7()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber7().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber7().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber7().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber8()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber8().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber8().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber8().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber9()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber9().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber9().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber9().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber10()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber10().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber10().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber10().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber11()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber11().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber11().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber11().getUrl()));
        }
        if(videoAPI.getShowapi_res_body().getNumber12()!=null){
            videoList.add(new Video(videoAPI.getShowapi_res_body().getNumber12().getThumbUrl(),videoAPI.getShowapi_res_body().getNumber12().getTitle(),
                    videoAPI.getShowapi_res_body().getNumber12().getUrl()));
        }
        return videoList;
    }
}

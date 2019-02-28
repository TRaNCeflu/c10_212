package com.example.newsapitest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.newsapitest.R;
import com.example.newsapitest.models.News;
import com.example.newsapitest.models.NewsDetail;
import com.example.newsapitest.network.Utility;
import com.example.newsapitest.views.NewsFragmentTitleLayout;
import com.show.api.ShowApiRequest;

import java.util.UUID;
/**
 * ... 设置新闻fragment...
 */
public class NewsFragment extends Fragment {
    //private static final String ARG_NEWS_ID = "news_id";
    private static final String app_id  = "77817";
    private static final String app_secret = "60d76fa900d34bf4af4197db7c16614d";

    private NewsFragmentTitleLayout newsFragmentTitleLayout;
    WebView webView;
    NewsDetail newsDetail;
    private String id;
    private int num_comment;
    /**
     * ... 拿到传递过来的新闻id，知道点击的是哪个新闻...
     */
    public static NewsFragment newInstance(String newsId,int num) {

        //Bundle args = new Bundle();
        //args.putSerializable(ARG_NEWS_ID,newsId);
        NewsFragment fragment = new NewsFragment();
        fragment.getNewsId(newsId);
        fragment.getNum(num);
        Log.d("调试", String.valueOf(num));
        //fragment.setArguments(args);
        return fragment;
    }

    public void getNewsId(String newsId){
        id = newsId;
    }
    public void getNum(int num){
        num_comment = num;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news,container,false);
        newsFragmentTitleLayout = (NewsFragmentTitleLayout)v.findViewById(R.id.fragment_news_title_comments);
        webView = (WebView)v.findViewById(R.id.news_webview);
        putinWebview();
        newsFragmentTitleLayout.setCommentsNum(num_comment);

        return v;
    }

    private void putinWebview(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String res = new ShowApiRequest("http://route.showapi.com/109-35",app_id,app_secret)
                        .addTextPara("channelId", "")
                        .addTextPara("channelName", "")
                        .addTextPara("title", "")
                        .addTextPara("page", "1")
                        .addTextPara("needContent", "1")
                        .addTextPara("needHtml", "1")
                        .addTextPara("needAllList", "0")
                        .addTextPara("maxResult", "20")
                        .addTextPara("id", id)
                        .post();
                newsDetail = Utility.handleNewsForDetail(res);
                System.out.println(newsDetail.getHtml());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadDataWithBaseURL(null,convertHTML(newsDetail.getHtml(),newsDetail.getTitle(),newsDetail.getPubdate(),newsDetail.getSource()),"text/html","utf-8",null);
                    }
                });
            }
        }).start();
    }

    /**
     * 将新闻正文用html在webview中显示出来，格式自己添加
     * @param html
     * @param title
     * @param pubDate
     * @param source
     * @return
     */
    private String convertHTML(String html,String title,String pubDate,String source){
        String titileHTML = "<h1 style=\"font-family: verdana\">"+title+"</h1> ";
        String pubdate = "<p style=\"size: 4 ;color: #C0C0C0FF\">"+pubDate+" "+source+"</p><br>";
        String newHTML  = "<style type=\"text/css\">\n" +
                "\t.a img{\n" +
                "\t\tmax-width: 100%;height: auto;width: auto\\9;\n" +
                "\t}\n" +
                "\t.b p{\n" +
                "\t\tfont-size: 17px\n" +
                "\t}\n" +
                "</style>" +
                "<div class=\"a b\">" +
                html+
                "</div>";
        return titileHTML+pubdate+newHTML;
    }
}

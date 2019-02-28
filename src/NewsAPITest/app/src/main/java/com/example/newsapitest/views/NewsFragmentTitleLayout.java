package com.example.newsapitest.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newsapitest.R;
/**
*   为新闻详情页面做的自定义标题栏
 *
 */
public class NewsFragmentTitleLayout extends LinearLayout {

    private ImageView btnBack;
    private TextView comments;
    public NewsFragmentTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.newsfrag_title,this);

        btnBack = (ImageView) findViewById(R.id.newsfrag_title_back);
        comments = (TextView)findViewById(R.id.newsfrag_title_num);

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
    }

    public void setCommentsNum(int num){
        String tmp = num+" 跟帖";
        comments.setText(tmp);
    }
}

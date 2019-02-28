package com.example.newsapitest.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.newsapitest.R;
/**
 *   为新闻评论页面做的自定义标题栏
 *
 */
public class CommentTitleLayout extends LinearLayout {
    private ImageView btnBack;
    public CommentTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.comment_title,this);
        btnBack = (ImageView)findViewById(R.id.comment_title_back);
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
    }
}

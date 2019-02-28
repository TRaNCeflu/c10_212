package com.example.newsapitest.views;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.newsapitest.R;
/**
 *   为历史纪录页面做的自定义标题栏
 *
 */
public class HistoryTitleLayout extends LinearLayout {
    private ImageView btnback;

    public HistoryTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.history_title,this);
        btnback = (ImageView) findViewById(R.id.history_title_back);
        btnback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
    }
}

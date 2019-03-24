package com.example.newsapitest.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.newsapitest.R;
import com.example.newsapitest.SearchActivity;

/**
 * 总的标题栏，其中包括搜索按钮转到SearchActivity
 */

public class PagerTitleLayout extends LinearLayout {
    TextView search;
    public PagerTitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.pager_titile,this);
        search = (TextView) findViewById(R.id.pager_title_search);
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((Activity)getContext(),SearchActivity.class);

                ((Activity)getContext()).startActivity(intent);
            }
        });
    }
}

package com.example.newsapitest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.newsapitest.fragments.CommentFragment;
import com.example.newsapitest.fragments.NewsFragment;
import com.example.newsapitest.views.MySwipeBackLayout;
import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;

/**
 * ... 设置新闻页面，由ViewPager组成两个可滑动页面，一个新闻具体内容，一个评论...
 */
public class NewsActivity extends AppCompatActivity {
    private static final String EXTRA_NEWS_ID = "com.example.android.mews.news_id";

    private ViewPager mViewPager;
    private MySwipeBackLayout swipeBackLayout;
    private int comment_login= (int) (Math.random()*20);
    //    public static Intent newIntent(Context packageContext , UUID newsID){
//        Intent intent = new Intent(packageContext,NewsActivity.class);
//        intent.putExtra(EXTRA_NEWS_ID,newsID);
//        return intent;
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Intent intent  = getIntent();
        final String newsId = intent.getStringExtra("id");

        mViewPager = (ViewPager)findViewById(R.id.activity_news_view_pager);
        swipeBackLayout = (MySwipeBackLayout) findViewById(R.id.swipe);
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {

                if(i == 1)
                    return CommentFragment.newInstance(comment_login);
                else return NewsFragment.newInstance(newsId,comment_login);
            }

            @Override
            public int getCount() {
                return 2;
            }

        });
        //swipeBackLayout.setDragEdge(SwipeBackLayout.DragEdge.LEFT);
    }




    /*@Override
    protected Fragment createFragment() {
        Intent intent = getIntent();
        String newsId = intent.getStringExtra("id");
        return NewsFragment.newInstance(newsId);
    }*/
}
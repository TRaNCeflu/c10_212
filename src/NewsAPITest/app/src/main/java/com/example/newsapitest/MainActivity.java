package com.example.newsapitest;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.newsapitest.fragments.MyFragment;
import com.example.newsapitest.fragments.NewsListFragment;
import com.example.newsapitest.fragments.PagerFragment;


import com.example.newsapitest.fragments.VideoFragment;
import com.show.api.ShowApiRequest;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;
/**
 * ... 主页面...
 */
public class MainActivity extends AppCompatActivity {
    BottomNavigationBar mBottomNavigationBar;
    FragmentManager fm;
    Fragment fragment;
    private long firstBacktime = 0;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.contentContainer);

        if (fragment == null) {
            fragment = new PagerFragment();
            fm.beginTransaction()
                    .add(R.id.contentContainer, fragment)
                    .commit();
        }
        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setBarBackgroundColor(R.color.black_p50);


        /**
         * ... 设置频道 ...
         */
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_document,"首页")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.mipmap.ic_action_document_dark))
                        .setActiveColor(Color.parseColor("#7DDCF2"))
                        .setInActiveColor(Color.LTGRAY)
                )

                .addItem(new BottomNavigationItem(R.mipmap.ic_action_youtube,"视频")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.mipmap.ic_action_youtube_dark))
                        .setActiveColor(Color.parseColor("#bd2158")))
                .addItem(new BottomNavigationItem(R.mipmap.ic_action_user,"我的")
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this,R.mipmap.ic_action_user_dark))
                        .setActiveColor(Color.parseColor("#fbb417")))


                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();
        /**
         * ... 设置lab点击事件，选择不同fragment...
         */
        mBottomNavigationBar
                .setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
                    @SuppressLint("ResourceType")
                    @Override
                    public void onTabSelected(int position) {
                        switch (position) {
                            case 0:


                                fragment = new PagerFragment();
                                fm.beginTransaction()
                                        .replace(R.id.contentContainer, fragment)
                                        .commit();
                                break;
                            case 1:
                                fragment = new VideoFragment();
                                fm.beginTransaction()
                                        .replace(R.id.contentContainer, fragment)
                                        .commit();
                                break;
                            case 2:

                                fragment = new MyFragment();
                                fm.beginTransaction()
                                        .replace(R.id.contentContainer, fragment)
                                        .commit();
                                break;

                        }

                    }

                    @Override
                    public void onTabUnselected(int position) {

                    }

                    @Override
                    public void onTabReselected(int position) {

                    }

                });

    }

    /**
     * 按两次退出功能
     */
    @Override
    public void onBackPressed() {
        if(JzvdStd.backPress()){
            return;
        }
        if (System.currentTimeMillis() - firstBacktime > 1500) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstBacktime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }
}

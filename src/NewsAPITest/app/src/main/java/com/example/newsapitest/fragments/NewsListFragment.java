package com.example.newsapitest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapitest.NewsActivity;
import com.example.newsapitest.R;
import com.example.newsapitest.models.News;
import com.example.newsapitest.network.Utility;
import com.example.newsapitest.utils.Daompl;
import com.example.newsapitest.utils.MyDataBaseHelper;
import com.show.api.ShowApiRequest;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * ... 设置新闻列表...
 */
public class NewsListFragment extends Fragment {
    private static final String TAG = "NewsListFragment";
    private static final String app_id  = "77817";
    private static final String app_secret = "60d76fa900d34bf4af4197db7c16614d";

    private PullLoadMoreRecyclerView mNewsRecyclerView;
    private NewsAdapter mAdapter;
    private String channelName;
    private List<News> newsList = new ArrayList<>();
    private int page;       ///新闻页数

    private MyDataBaseHelper dbHelper;

    public static NewsListFragment newInstance(String channelName) {

        //Bundle args = new Bundle();
       // args.putSerializable(ARG_NEWS_ID,newsId);
        NewsListFragment fragment = new NewsListFragment();
        fragment.getChannelName(channelName);
        //fragment.setArguments(args);
        return fragment;
    }

    private void getChannelName(String name){
        this.channelName = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list,container,false);

        mNewsRecyclerView = (PullLoadMoreRecyclerView) view
                .findViewById(R.id.pullloadmorerecyclerview);
        mNewsRecyclerView.setLinearLayout();

        dbHelper = new MyDataBaseHelper(getActivity(),"MyNewsStore.db",null,4);
        //updateUI();
        page =1;
        getNewsListFromAPI();
        mAdapter = new NewsAdapter(newsList);
        mNewsRecyclerView.setAdapter(mAdapter);
        mNewsRecyclerView.setFooterViewText("加载更多");
        mNewsRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshNewsFromAPI();
                        mNewsRecyclerView.setPullLoadMoreCompleted();
                    }
                },1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNewsListFromAPI();
                        mNewsRecyclerView.setPullLoadMoreCompleted();
                    }
                },1000);
            }
        });
        return view;
    }

    private class NewsHolderWithImage extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleView;
        private TextView mSourceView;
        private ImageView imageView;
        private News mNews;
        public NewsHolderWithImage(View view){
            super(view);
            itemView.setOnClickListener(this);
            mTitleView = (TextView)itemView.findViewById(R.id.news_list_title);
            mSourceView = (TextView)itemView.findViewById(R.id.news_list_source);
            imageView = (ImageView) itemView.findViewById(R.id.news_list_imageview);
        }
        public void bind(News news){
            mNews = news;
            if(mNews.getTitle().length()>=23){
                String tmp = mNews.getTitle().substring(0,22);
                mTitleView.setText(tmp+"……");
            }else{
                mTitleView.setText(mNews.getTitle());
            }
            mSourceView.setText(mNews.getSource());
            if(mNews.havePic == true){
                Glide.with(getContext())
                        .load(mNews.getImageurl().get(0).getUrl())
                        .centerCrop()
                        .placeholder(R.drawable.timg)
                        .into(imageView);
            }
        }
        /**
         * ... 设置新闻点击事件，传递id...
         */
        @Override
        public void onClick(View v) {
            Daompl.saveIdinHistory(mNews.getId(),mNews.getTitle(),dbHelper);    ///每点击一次新闻将新闻title和id放入history表中

            Intent intent = new Intent(getActivity(),NewsActivity.class);
            intent.putExtra("id",mNews.getId());
            startActivity(intent);
        }
    }

    private class NewsHolderWithoutImage extends RecyclerView.ViewHolder implements View.OnClickListener{
        private  TextView mTitleView;
        private  TextView mSourceView;
        private News mNews;
        public NewsHolderWithoutImage(View view){
            super(view);
            itemView.setOnClickListener(this);
            mTitleView = (TextView) itemView.findViewById(R.id.news_list_title_1);
            mSourceView = (TextView) itemView.findViewById(R.id.news_list_source_1);
        }

        public void bind(News news){
            mNews = news;
            mTitleView.setText(mNews.getTitle());
            mSourceView.setText(news.getSource());
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(),NewsActivity.class);
            intent.putExtra("id",mNews.getId());
            startActivity(intent);
        }
    }

    private class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<News> mNewsList;
        public NewsAdapter(List<News> newsList)
        {
            mNewsList = newsList;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView;
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            if(viewType == 1){
                itemView = layoutInflater.inflate(R.layout.list_item_news_s1,parent,false);
                return  new NewsHolderWithImage(itemView);
            }
            else{
                itemView = layoutInflater.inflate(R.layout.list_item_news_s2,parent,false);
                return  new NewsHolderWithoutImage(itemView);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder newsHolder, int position) {
            News news = mNewsList.get(position);
            if(news.getHavePic() == true){
                NewsHolderWithImage newsHolderWithImage = (NewsHolderWithImage)newsHolder;
                ((NewsHolderWithImage) newsHolder).bind(news);
            }
            else{
                NewsHolderWithoutImage newsHolderWithoutImage = (NewsHolderWithoutImage) newsHolder;
                ((NewsHolderWithoutImage) newsHolder).bind(news);
            }
        }

        @Override
        public int getItemViewType(int position) {
            News news = mNewsList.get(position);
            if(news.getHavePic() == true)
                return 1;
            else
                return 0;
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
    /**
     * ... 从API获取新闻...
     * 将获取的新闻放入newsList列表中
     */
    private void getNewsListFromAPI(){
        /*NewsLab newsLab = NewsLab.get(getActivity());
        List<News> newsList = newsLab.getNewss();*/
        //List<News> newsList;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String res = new ShowApiRequest("http://route.showapi.com/109-35",app_id,app_secret)
                        .addTextPara("channelId", "")
                        .addTextPara("channelName", channelName)
                        .addTextPara("title", "")
                        .addTextPara("page", page+"")
                        .addTextPara("needContent", "1")
                        .addTextPara("needHtml", "0")
                        .addTextPara("needAllList", "0")
                        .addTextPara("maxResult", "20")
                        .addTextPara("id", "")
                        .post();
                int allPage = Utility.handleNewsAllPageForList(res);
                if(page<=allPage){
                    List<News> tempList = Utility.handleNewsForList(res);
                    for(int i  = 0;i<tempList.size();i++){
                        newsList.add(tempList.get(i));
                        Log.d(TAG,newsList.get(i).getId()+"--"+newsList.get(i).getTitle());
                    }
                    page++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }else{
                    Log.d(TAG,"没有更多新闻");
                }

            }
        }).start();
    }
    /**
     * ... 刷新新闻...
     */
    private void refreshNewsFromAPI(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                newsList.clear();
                Random ran = new Random();
                page = ran.nextInt(10)+1;
                String res = new ShowApiRequest("http://route.showapi.com/109-35",app_id,app_secret)
                        .addTextPara("channelId", "")
                        .addTextPara("channelName", channelName)
                        .addTextPara("title", "")
                        .addTextPara("page", page+"")
                        .addTextPara("needContent", "1")
                        .addTextPara("needHtml", "0")
                        .addTextPara("needAllList", "0")
                        .addTextPara("maxResult", "20")
                        .addTextPara("id", "")
                        .post();
                page =1;
                List<News> tempList = Utility.handleNewsForList(res);
                for(int i  = 0;i<tempList.size();i++){
                    newsList.add(tempList.get(i));
                    Log.d(TAG,newsList.get(i).getId()+"--"+newsList.get(i).getTitle());
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}

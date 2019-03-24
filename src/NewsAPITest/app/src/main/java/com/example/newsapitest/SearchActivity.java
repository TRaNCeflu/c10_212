package com.example.newsapitest;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newsapitest.fragments.NewsListFragment;
import com.example.newsapitest.models.News;
import com.example.newsapitest.network.Utility;
import com.show.api.ShowApiRequest;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
/**
 * ... 搜索栏...
 */
public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private static final String app_id  = "77817";
    private static final String app_secret = "60d76fa900d34bf4af4197db7c16614d";

    private SearchView searchView;
    private ImageView btnBack;
    private PullLoadMoreRecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<News> newsList = new ArrayList<>();
    private int page;
    private String searchContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = (SearchView)findViewById(R.id.activity_search_searchview);
        btnBack = (ImageView) findViewById(R.id.activity_search_back);

        recyclerView =(PullLoadMoreRecyclerView) findViewById(R.id.activity_search_recyclerview);
        recyclerView.setLinearLayout();
        adapter = new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setPullRefreshEnable(false);
        recyclerView.setFooterViewText("加载更多");
        ///重载下拉刷新上拉加载函数
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }
            ///上拉加载更多News
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNewsListFromAPI(searchContent);
                        recyclerView.setPullLoadMoreCompleted();
                    }
                },1000);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                page = 1;
                searchContent =s ;
                getNewsListFromAPI(searchContent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


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
                Glide.with(SearchActivity.this)
                        .load(mNews.getImageurl().get(0).getUrl())
                        .centerCrop()
                        .placeholder(R.drawable.timg)
                        .into(imageView);
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SearchActivity.this,NewsActivity.class);
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
            Intent intent = new Intent(SearchActivity.this,NewsActivity.class);
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
            LayoutInflater layoutInflater = LayoutInflater.from(SearchActivity.this);
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
    private void getNewsListFromAPI(final String content){
        /*NewsLab newsLab = NewsLab.get(getActivity());
        List<News> newsList = newsLab.getNewss();*/
        //List<News> newsList;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(page==1){
                    newsList.clear();
                }
                String res = new ShowApiRequest("http://route.showapi.com/109-35",app_id,app_secret)
                        .addTextPara("title", content)
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
                        //Log.d(TAG,newsList.get(i).getId()+"--"+newsList.get(i).getTitle());
                    }
                    page++;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }else{
                    Log.d(TAG,"没有更多新闻");
                }

            }
        }).start();
    }
}

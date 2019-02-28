package com.example.newsapitest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapitest.models.NewsForHistory;
import com.example.newsapitest.utils.Daompl;
import com.example.newsapitest.utils.MyDataBaseHelper;

import java.util.List;
/**
 * ... 我的历史页面...
 * 从History表中将数据拿出
 */
public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDataBaseHelper dbHelper;
    private List<NewsForHistory> newsList;
    private HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        dbHelper = new MyDataBaseHelper(HistoryActivity.this,"MyNewsStore.db",null,4);
        newsList = Daompl.getNewsListFromHisttory(dbHelper);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_history_activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(HistoryActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter = new HistoryAdapter(newsList);
        recyclerView.setAdapter(adapter);
    }

    private class HistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleText;
        private NewsForHistory mNews;

        public HistoryHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            titleText = (TextView)itemView.findViewById(R.id.title_history);
        }

        public void bind(NewsForHistory news){
            mNews = news;
            titleText.setText(mNews.getTitle());
        }
        /**
         * ... 设置新闻点击事件...
         */
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(HistoryActivity.this,NewsActivity.class);
            intent.putExtra("id",mNews.getId());
            startActivity(intent);
        }
    }
    private  class HistoryAdapter extends RecyclerView.Adapter<HistoryHolder>{
        private List<NewsForHistory> newsList ;

        public  HistoryAdapter(List<NewsForHistory> newsList){
            this.newsList = newsList;
        }

        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view;
            view = LayoutInflater.from(HistoryActivity.this).inflate(R.layout.list_item_history,viewGroup,false);
            return new HistoryHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder historyHolder, int i) {
            NewsForHistory news = newsList.get(i);
            historyHolder.bind(news);
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }
}

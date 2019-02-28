package com.example.newsapitest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.newsapitest.NewsActivity;
import com.example.newsapitest.R;
import com.example.newsapitest.models.CommentLab;
import com.example.newsapitest.models.News;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
/**
 * ... 设置评论列表...
 */
public class CommentFragment extends Fragment {
    private static  final String ARG_COMMENT_NUM = "comment_num";
        private RecyclerView mCommentRecyclerView;
    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;
    private  int comment_num ;
        private CommentAdapter mAdapter;
        public View onCreateView( LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
            View view = inflater.inflate(R.layout.fragment_comment,container,false);

            mCommentRecyclerView = (RecyclerView) view.findViewById(R.id.commment_recycler);
            mCommentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mCommentRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
            updateUI();
            return view;
        }
        private void updateUI(){
          // Log.d("调试", String.valueOf(comment_num));
            CommentLab commentLab = new CommentLab(getActivity(),comment_num);
            List<com.example.newsapitest.models.Comment> comments = commentLab.getComments();
            mAdapter = new CommentAdapter(comments);
            mCommentRecyclerView.setAdapter(mAdapter);
        }

    public static CommentFragment newInstance(int num) {

           //Bundle args = new Bundle();
          //  args.putSerializable(ARG_COMMENT_NUM ,comment_num);

            CommentFragment fragment = new CommentFragment();
            fragment.getNum(num);
       // Log.d("调试", String.valueOf(num));
           // fragment.setArguments(args);
            return fragment;
    }
    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long time = date.getTime() / 1000;
        long now = new Date().getTime() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR)
            return ago / ONE_MINUTE +"分钟前";
        else if (ago <= ONE_DAY)
            return ago / ONE_HOUR +"小时"+ (ago % ONE_HOUR / ONE_MINUTE)
                    +"分钟前";
        else if (ago <= ONE_DAY * 2)
            return"昨天"+ calendar.get(Calendar.HOUR_OF_DAY) +"点"
                    + calendar.get(Calendar.MINUTE) +"分";
        else if (ago <= ONE_DAY * 3)
            return"前天"+ calendar.get(Calendar.HOUR_OF_DAY) +"点"
                    + calendar.get(Calendar.MINUTE) +"分";
        else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day +"天前"+ calendar.get(Calendar.HOUR_OF_DAY) +"点"
                    + calendar.get(Calendar.MINUTE) +"分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month +"个月"+ day +"天前"
                    + calendar.get(Calendar.HOUR_OF_DAY) +"点"
                    + calendar.get(Calendar.MINUTE) +"分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0 so month+1
            return year +"年前"+ month +"月"+ calendar.get(Calendar.DATE)
                    +"日";
        }
    }
    public static String fromDeadline(Date date) {
        //long deadline = date.getTime() / 1000;
        //long now = (new Date().getTime()) / 1000;
        //long remain = deadline - now;
        long remain = date.getTime()/1000;
        return remain+"小时前";
      /*  if (remain <= ONE_HOUR)
            return remain / ONE_MINUTE +"分钟前";
        else if (remain <= ONE_DAY)
            return  remain / ONE_HOUR +"小时"
                    + (remain % ONE_HOUR / ONE_MINUTE) +"分钟前";
        else {
            long day = remain / ONE_DAY;
            long hour = remain % ONE_DAY / ONE_HOUR;
            long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
            return  day +"天"+ hour +"小时"+ minute +"分钟前";
        }*/
    }
    public void getNum(int num){
            comment_num = num;
    }
    private  class  CommentHolder extends RecyclerView.ViewHolder{
            private TextView mNameTextView;
            private TextView mSiteTextView;
            private TextView mBodyTextView;
            private TextView mTimeTextView;
            private com.example.newsapitest.models.Comment mComment;
            public CommentHolder(LayoutInflater inflater ,ViewGroup parent){
                super(inflater.inflate(R.layout.list_item_comment,parent,false));
                mNameTextView = (TextView)itemView.findViewById(R.id.comment_name);
                mSiteTextView = (TextView)itemView.findViewById(R.id.comment_site);
                mBodyTextView = (TextView)itemView.findViewById(R.id.comment_body);
                mTimeTextView = (TextView)itemView.findViewById(R.id.comment_time);
            }
            public void bind(com.example.newsapitest.models.Comment comment){
                mComment = comment;
                mNameTextView.setText(comment.getName());
                mSiteTextView.setText(comment.getSite());
                mBodyTextView.setText(comment.getBody());
                mTimeTextView.setText(comment.getTime());
            }
        }
        private  class CommentAdapter extends RecyclerView.Adapter<CommentHolder>{
            private List<com.example.newsapitest.models.Comment> mComments;
            public CommentAdapter(List<com.example.newsapitest.models.Comment> comments){
                mComments = comments;
            }

            @NonNull
            @Override
            public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new CommentHolder(layoutInflater,parent);
            }

            @Override
            public void onBindViewHolder(@NonNull CommentHolder commentHolder, int position) {
                com.example.newsapitest.models.Comment comment = mComments.get(position);
                commentHolder.bind(comment);
            }

            @Override
            public int getItemCount() {
                return mComments.size();
            }
        }
  }
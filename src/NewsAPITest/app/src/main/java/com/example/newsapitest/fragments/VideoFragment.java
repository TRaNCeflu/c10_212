package com.example.newsapitest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapitest.PlayVideoActivity;
import com.example.newsapitest.R;
import com.example.newsapitest.models.Video;
import com.example.newsapitest.models.VideoAPI;
import com.example.newsapitest.network.Utility;
import com.google.gson.Gson;
import com.show.api.ShowApiRequest;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ... 视频Fragemnt...
 */
public class VideoFragment extends Fragment {
    private static final String app_id  = "77817";
    private static final String app_secret = "60d76fa900d34bf4af4197db7c16614d";

    private PullLoadMoreRecyclerView recyclerView;
    List<Video> videoList = new ArrayList<>();
    VideoAdapter adapter;
    int page = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video,container,false);
        recyclerView = (PullLoadMoreRecyclerView) v.findViewById(R.id.recycler_view_video_fragment);
        recyclerView.setLinearLayout();
        page = 1;
        getVideoListfromAPI();
        adapter = new VideoAdapter(videoList);
        recyclerView.setAdapter(adapter);
        recyclerView.setFooterViewText("加载更多···");
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        videoList.clear();
                        getVideoListfromAPI();
                        recyclerView.setPullLoadMoreCompleted();
                    }
                },1500);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        getVideoListfromAPI();
                        recyclerView.setPullLoadMoreCompleted();
                    }
                },1500);
            }
        });


        return v;
    }

    private class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView image;
        private TextView title;
        private Video mVideo;
        public VideoHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            image = (ImageView)view.findViewById(R.id.video_image);
            title = (TextView) view.findViewById(R.id.video_title);
        }

        public void bind(Video video){
            mVideo = video;
            title.setText(video.getTitle());
            String imgurl = "http:"+video.getImg();
            Glide.with(getContext())
                    .load(imgurl)
                    .centerCrop()
                    //.placeholder(R.drawable.timg)
                    .into(image);
        }

        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(getActivity(),PlayVideoActivity.class);
            intent.putExtra("url",mVideo.getUrl());
            intent.putExtra("title",mVideo.getTitle());
            startActivity(intent);*/
            JzvdStd.startFullscreen(getContext(),JzvdStd.class,mVideo.getUrl(),mVideo.getTitle());

        }
    }

    private class VideoAdapter extends RecyclerView.Adapter<VideoHolder>{
        private List<Video> videoList;
        public VideoAdapter(List<Video> videoList) {
            this.videoList = videoList;
        }

        @NonNull
        @Override
        public VideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.list_item_video,viewGroup,false);
            return new VideoHolder(v);

        }

        public void onBindViewHolder(@NonNull VideoHolder videoHolder, int i) {
            Video mVideo = videoList.get(i);
            videoHolder.bind(mVideo);
        }

        @Override
        public int getItemCount() {
            return videoList.size();
        }
    }

    /**
     * 从API中获取Video信息
     */
    private void getVideoListfromAPI() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    /*OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://115.196.150.238:8080/forandroid/get_videoList.jsp").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();*/
                    //System.out.print(responseData);
                    String responseData=new ShowApiRequest( "http://route.showapi.com/1564-1", app_id, app_secret)
                            .addTextPara("type", "1033")
                            .addTextPara("cate", "list")
                            .addTextPara("page", page+"")
                            .post();
                    //System.out.print(responseData);
                    List<Video> tempList = Utility.handleVideoForList(responseData);
                    for(int i = 0;i<tempList.size();i++){
                        videoList.add(tempList.get(i));
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

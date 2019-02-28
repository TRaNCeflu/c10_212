package com.example.newsapitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.show.api.ShowApiRequest;

import cn.jzvd.JzvdStd;
/**
 * 视频播放活动
 */
public class PlayVideoActivity extends AppCompatActivity {
    private static final String app_id  = "77817";
    private static final String app_secret = "60d76fa900d34bf4af4197db7c16614d";
    private String videoUrl = "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4";

    private JzvdStd player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        Intent intent = getIntent();
        videoUrl = intent.getStringExtra("url");

        player = (JzvdStd) findViewById(R.id.videoplayer);
        player.setUp(videoUrl,intent.getStringExtra("title"),player.SCREEN_WINDOW_NORMAL);
        //player.startFullscreen(this,JzvdStd.class,videoUrl,"");
        player.startButton.performClick(); ///模拟按动播放按钮使视频自动播放

    }

    /*private void getVIdeourl(){
        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String res=new ShowApiRequest("http://route.showapi.com/1564-1", app_id, app_secret)
                        .addTextPara("cate","detail")
                        .addTextPara("id",id)
                        .post();

            }
        }).start();
    }*/

    @Override
    public void onBackPressed() {
        if(JzvdStd.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }
}

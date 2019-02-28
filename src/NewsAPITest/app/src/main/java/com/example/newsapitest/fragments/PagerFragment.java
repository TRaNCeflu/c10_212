package com.example.newsapitest.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapitest.R;

import java.util.ArrayList;
import java.util.List;
/**
 * ... 新闻频道切换...
 * 用ViewPager
 */
public class PagerFragment extends Fragment {
    private static final String app_id  = "77817";
    private static final String app_secret = "60d76fa900d34bf4af4197db7c16614d";

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<String> _data = new ArrayList<String>();
    //private List<ChannelDB> channelDBList = new ArrayList<>();
    //private MyDataBaseHelper dbHelper;
    int isEnd = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_pager,container,false);
        //dbHelper = new MyDataBaseHelper(getActivity(),"MyNewsStore.db",null,2);
        mViewPager = (ViewPager)v.findViewById(R.id.activity_news_pager_view_pager);
        mTabLayout = (TabLayout)v.findViewById(R.id.tab_layout) ;
        FragmentManager fragmentManager = getChildFragmentManager();
        //新建新闻频道
        InitChannelDB();
        //while(isEnd == 0);
        ///用ViewPager做出滑动切换效果，用_data数组中的数据作为标题
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                return _data.get(position);
            }

            @Override
            public Fragment getItem(int position) {

                return NewsListFragment.newInstance(_data.get(position));
            }

            @Override
            public int getCount() {
                return _data.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        return v;
    }

    private void InitChannelDB(){
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                // List<ChannelDB> channelDBList = new ArrayList<>();
                String res = new ShowApiRequest("http://route.showapi.com/109-34", app_id, app_secret).post();
                //Log.d(TAG,res);
                Daompl.saveChannelinDB(Utility.handleChannelForDB(res),dbHelper);
            }
        }).start();*/
        String[] channels = new String[]{"国际焦点" ,
                "国内最新" ,
                "台湾最新" ,
                "港澳最新" ,
                "国际最新" ,
                "军事最新" ,
                "财经最新" ,
                "理财最新" ,
                "宏观经济最新" ,
                "互联网最新" ,
                "房产最新" ,
                "汽车最新" ,
                "体育最新" ,
                "国际足球最新" ,
                "国内足球最新" ,
                "CBA最新" ,
                "综合体育最新" ,
                "娱乐最新" ,
                "电影最新" ,
                "电视最新" ,
                "游戏最新" ,
                "教育最新" ,
                "女人最新" ,
                "健康养生最新" ,
                "科技最新" ,
                "社会最新" ,
                "电商最新"};
        for(int i = 0;i < channels.length;i++){
            _data.add(channels[i]);
        }
        /*new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client =new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://115.196.154.45:8080/forandroid/get_channel.jsp").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    System.out.print(responseData);
                    Gson gson = new Gson();
                    ChannelFromTom channelFromTom = gson.fromJson(responseData,ChannelFromTom.class);
                    List<ChannelFromTom.ChannelList> channelLists = channelFromTom.getChannelList();
                    for(int i = 0 ;i<channelLists.size();i++){
                        ChannelFromTom.ChannelList tmp= channelLists.get(i);
                        _data.add(tmp.getChannelName());
                    }
                    isEnd = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

    }
}

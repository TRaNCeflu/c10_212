package com.example.newsapitest.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapitest.HistoryActivity;
import com.example.newsapitest.LoginActivity;
import com.example.newsapitest.MainActivity;
import com.example.newsapitest.R;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * 用recycler view写我的页面
 */
public class MyFragment extends Fragment{
    private RecyclerView mMyRecyclerView;
    private  MyAdapter mAdapter;
    private int REQUEST_CODE_SCAN = 111;
    public static  final int VIEW_TYPE_ONE = 1;
    public static  final int VIEW_TYPE_TWO = 2;
    private Button btn_exit;
    private EditText edit1;
    boolean have;
    private List<String> mStrings= new ArrayList<>(Arrays.asList("  我的历史","  金币商城","  京东特供","  阅读公益","  我的钱包","  免费看新闻"," 扫一扫"));
    private static final String TAG = "MyFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_my_list,container,false);
        edit1 = (EditText) view.findViewById(R.id.edit11);
        mMyRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mMyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MyAdapter(mStrings);
        mMyRecyclerView.setAdapter(mAdapter);
        mMyRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        btn_exit = (Button) view.findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(have) {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("data", getActivity().MODE_MULTI_PROCESS).edit();

                    editor.clear();
                    editor.commit();
                    getActivity().finish();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);//
                }

            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
    private void updateUI(){
        if(mAdapter ==null) {
            mAdapter = new MyAdapter(mStrings);
            mMyRecyclerView.setAdapter(mAdapter);
        }
        else{
                mAdapter.notifyDataSetChanged();
            }

    }
    public class MyHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImageView;
        private TextView   mTextView;
        private String name;
       // private boolean have;
        public MyHolder1(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_my_login, parent, false));
            mImageView = (ImageView) itemView.findViewById(R.id.my_image_login);
            mTextView = (TextView)itemView.findViewById(R.id.my_textview_login);
            SharedPreferences pref = getActivity().getSharedPreferences("data",MODE_PRIVATE);
            have = pref.getBoolean("have",false);
            if(have)
                name = pref.getString("nicheng","");
            itemView.setOnClickListener(this);
        }
    public void bind(){

             if(have)
            mTextView.setText(name);
             else
            mTextView.setText("点击登录");

    }

        @Override
        public void onClick(View v) {
            if(!have){
                getActivity().finish();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);}
            else
                ;
        }
    }
    public class MyHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextView ;
        int position;
        public MyHolder2(LayoutInflater inflater,ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_my_normal, parent, false));
            itemView.setOnClickListener(this);
            mTextView = (TextView)itemView.findViewById(R.id.my_textview);
        }
        public void bind(String string, int i){
            mTextView.setText(string);
            position = i;

        }

        @Override
        public void onClick(View v) {Log.d(TAG,position+"");
            if(position == 1){

                Intent intent = new Intent(getActivity(),HistoryActivity.class);
                startActivity(intent);
            }
            else if(position == 7){
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},        1);
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                //startActivityForResult(intent, 3);
                ZxingConfig config = new ZxingConfig();
                config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
                config.setPlayBeep(true);//是否播放提示音
                config.setShake(true);//是否震动
                config.setShowAlbum(true);//是否显示相册
                config.setShowFlashLight(true);//是否显示闪光灯
                 intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);


                startActivityForResult(intent, REQUEST_CODE_SCAN);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);
                //result.setText("扫描结果为：" + content);
                //Toast.makeText(getActivity(),content,Toast.LENGTH_LONG);
                edit1.setText("扫描结果为：" + content);
            }
        }
    }
    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        private List<String> mStrings;
        public MyAdapter(List<String> string){
            mStrings = string;

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
           LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            switch(viewType){
                case VIEW_TYPE_ONE:
                    viewHolder =  new MyHolder1(layoutInflater,parent);
                    break;
                case VIEW_TYPE_TWO:
                    viewHolder =  new MyHolder2(layoutInflater,parent);
                    break;
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
                switch (viewHolder.getItemViewType()){
                    case VIEW_TYPE_ONE:
                        MyHolder1 holder1 = (MyHolder1) viewHolder;
                        holder1.bind();
                        break;
                    case VIEW_TYPE_TWO:
                        String string = mStrings.get(position-1);
                        MyHolder2 holder2 = (MyHolder2) viewHolder;
                        ((MyHolder2)viewHolder).bind(string,position);
                        break;
                }
        }

        @Override
        public int getItemCount() {
            return mStrings.size()+1;

        }
        public int getItemViewType(int position){
            int i= 0;
            if(position== 0)
                i= VIEW_TYPE_ONE;
            else if(position> 0)
                i= VIEW_TYPE_TWO;
            return i;
        }
    }
}

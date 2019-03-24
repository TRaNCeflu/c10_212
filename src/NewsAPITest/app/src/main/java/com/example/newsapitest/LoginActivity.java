package com.example.newsapitest;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * ... 登录页面...
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int OK = 200;
    private EditText usernameEditText;
    private EditText passwrodEditText;
    private Button btn_login;
    private Button btn_register;
    private String nicheng;
    String name;
    String psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.username);
        passwrodEditText = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendRequestWithOkHttp();
            }
        });

        btn_register = (Button) findViewById(R.id.btn_regist);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void goRegister(){
        finish();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {

            sendRequestWithOkHttp();
        }
    }
    /**
     * ... 发送登录信息至服务器，获取返回信息作出反应 ...
     */
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                name = usernameEditText.getText().toString().trim();
                psw = passwrodEditText.getText().toString().trim();
                String path = "http://10.0.2.2:8080/AndroidTest/mustLogin?logname=" + name + "&password=" + psw;
                try {
                    try {
                        URL url = new URL(path); //新建url并实例化
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");//获取服务器数据
                        connection.setReadTimeout(8000);//设置读取超时的毫秒数
                        connection.setConnectTimeout(8000);//设置连接超时的毫秒数
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String result = reader.readLine();//读取服务器进行逻辑处理后页面显示的数据
                        Log.d("MainActivity", "run: " + result);
                         if (result.equals("can not login!")) {

                            Log.d("MainActivity", "run2: " + result);
                            Looper.prepare();
                            Log.d("MainActivity", "run3: " + result);
                            Toast.makeText(LoginActivity.this, " can not login!!", Toast.LENGTH_SHORT).show();
                            Log.d("MainActivity", "run4: " + result);
                            Looper.loop();

                        }
                        else {
                             nicheng = result;
                            storedata();
                            Log.d("MainActivity", "run2: " + result);
                            Looper.prepare();
                            Log.d("MainActivity", "run3: " + result);///
                            Toast.makeText(LoginActivity.this, "You logined successfully!", Toast.LENGTH_SHORT).show();
                            Log.d("MainActivity", "run4: " + result);
                            Looper.loop();

                        }

                    } catch (MalformedURLException e) {
                    }//can not login!
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * ... 将账号密码保存本地，下次自动登录 ...
     */
    void storedata(){
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_MULTI_PROCESS).edit();
        editor.putString("name",name);
        editor.putString("password",psw);
        editor.putString("nicheng",nicheng);
        editor.putBoolean("have",true);
        editor.apply();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
       // MainActivity.class.recreate();
    }
    public void onBackPressed() {

        super.onBackPressed();

        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("logname", String.valueOf(usernameEditText.getText()))
                            .add("password", String.valueOf(passwrodEditText.getText()))
                            .build();

                    Request request = new Request.Builder()
                            .url("http://10.0.2.2/AndroidTest")
                            .post(requestBody)
                            .build();
                    Response response =client.newCall(request).execute();
                    String responseData = response.body().string();
                    Toast.makeText(LoginActivity.this,responseData,Toast.LENGTH_LONG).show();
                }catch (Exception e){

                    e.printStackTrace();

                }
            }
        }).start();
    }


}*/

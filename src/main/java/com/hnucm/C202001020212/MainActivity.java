package com.hnucm.C202001020212;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public TextView reg;
    private TextView login;
    private EditText count;
    private EditText pwd;
    public TextView state;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();
    List<String> picList=new ArrayList<>();
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.yiny);
        TextView textView1=findViewById(R.id.textView9);


        banner=findViewById(R.id.banner);

        picList.add("https://pic2.zhimg.com/v2-17e4319a22559b273938e342d039baaa_r.jpg?source=1940ef5c");
        picList.add("https://pic1.zhimg.com/v2-8042a2b9e5080781906de65d81c5aa98_r.jpg?source=1940ef5c");
        picList.add("https://ts1.cn.mm.bing.net/th/id/R-C.8db8c9a43e5b5eddc70bca5471e33acf?rik=lpBR8OrwJMfzWQ&riu=http%3a%2f%2fimg.zcool.cn%2fcommunity%2f022fcd594b367fa8012193a345e15f.jpg%40800w_1l_2o_100sh.jpg&ehk=wnCt9KLTNCWnfqdTXXIpRIcVeQxJMjHAZ2h00jtZWOA%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1");

        banner.setAdapter(new BannerImageAdapter<String>(picList) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                //网络图片加载
                Glide.with(holder.imageView)
                        .load(data)//获取图片URL
                        .into(holder.imageView);
            }
        });

        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://v1.hitokoto.cn/?c=k")
                .get()
                .build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("onResponse",e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String result=response.body().string();
                Log.i("onResponse",result);

                Gson gson =new Gson();
                Api api=gson.fromJson(result,Api.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        textView.setText(api.hitokoto);
                        textView1.setText(api.from_who);
                    }
                });
            }
        });

        reg= (TextView) findViewById(R.id.nlog);
        login= (TextView) findViewById(R.id.reg);
        count= (EditText) findViewById(R.id.usernameEt);
        pwd= (EditText) findViewById(R.id.passEt);
        state= (TextView) findViewById(R.id.state);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();

                User user=new User();
                user.setUsername(name);
                user.setUserpwd(pass);

                int result=SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                if (result==1){
                    state.setText("注册成功！");
                }else  if (result==-1)
                {
                    state.setText("用户名已经存在！");
                }
                else
                {
                    state.setText("！");
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                int result=SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                    state.setText("登录成功！");
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);//跳转到主界面
                    finish();
                }
                else if (result==0){
                    state.setText("用户名不存在！");

                }
                else if(result==-1)
                {
                    state.setText("密码错误！");
                }

            }
        });
    }
}
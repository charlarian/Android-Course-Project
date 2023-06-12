package com.hnucm.C202001020212;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment_Daka extends Fragment {

    RecyclerView recyclerView;
    MyAdatper myAdatper;//
    List<String> stringList = new ArrayList<>();
    int[] img = new int[]{
            R.drawable.text1,
            R.drawable.text2,
            R.drawable.text3,
            R.drawable.text4,
            R.drawable.text5,
            R.drawable.text6,
            R.drawable.text7,};
//    List<ImageView> photoList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__daka, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        myAdatper = new MyAdatper();
        recyclerView.setAdapter(myAdatper);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//
        TextView textView=view.findViewById(R.id.yiny);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//        //获取当前时间
//        Date date = new Date(System.currentTimeMillis());

        stringList.add("喝水");
        stringList.add("练字");
        stringList.add("每天水彩画");
        stringList.add("英语打卡");
        stringList.add("每日记账");
        stringList.add("四六级练习");
        stringList.add("听力");

        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://v1.hitokoto.cn/?c=i")
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(api.hitokoto);
                    }
                });
            }
        });



        return view;
    }

    public class MyAdatper extends RecyclerView.Adapter<MyViewHolder> {//将布局文件复制n次
        @NonNull
        @Override//加载布局文件返回
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.recycle_da/*加载的布局文件*/, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(view);//封装
            return myViewHolder;
        }

        @Override//
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
            holder.textView.setText(stringList.get(position));
            holder.imageView.setImageResource(img[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.textView1.setText("打卡时间"+simpleDateFormat.format(date));
                }
            });

        }

        @Override//将布局复制的次数返回（显示条数
        public int getItemCount() {

            return 7;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {//控制布局文件的控制
        TextView textView;
        TextView textView1;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView4);//布局文本“写作业”
            textView1= itemView.findViewById(R.id.tv_time1);
            imageView = itemView.findViewById(R.id.imageView2);//布局文件图片
        }

    }
}
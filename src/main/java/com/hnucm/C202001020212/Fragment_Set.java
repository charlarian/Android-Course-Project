package com.hnucm.C202001020212;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.awt.font.TextAttribute;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Fragment_Set extends Fragment {

    private TextView timeET;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        TextView textView;


        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment__set, container, false);

        textView=view.findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WebActivity.class);
                startActivity(intent);//跳转到主界面
            }
        });

        TextView textView6=view.findViewById(R.id.yiny);
        TextView textView1=view.findViewById(R.id.sitext);
        TextView textView2=view.findViewById(R.id.fortune);
        TextView textView3=view.findViewById(R.id.stars);
        TextView textView4=view.findViewById(R.id.timeET);
        TextView textView5=view.findViewById(R.id.textView3);

        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url("https://api.fanlisky.cn/api/qr-fortune/get/123456")
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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM/yyyy");
                Date date = new Date(System.currentTimeMillis());

                Gson gson =new Gson();
                Star star=gson.fromJson(result,Star.class);
//                Api api=gson.fromJson(result,Api.class);
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        textView6.setText(star.data.unSignText);
                        textView1.setText(star.data.signText);
                        textView2.setText(star.data.fortuneSummary);
                        textView3.setText(star.data.luckyStar);
                        textView4.setText(simpleDateFormat.format(date));
                        textView5.setText(simpleDateFormat1.format(date));

                    }

                });
            }
        });

        return view;

    }




}
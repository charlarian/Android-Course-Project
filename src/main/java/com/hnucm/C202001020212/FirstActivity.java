package com.hnucm.C202001020212;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Thread thread=new Thread(){
            @Override
            public void run(){
                super.run();
                try {
                    Thread.sleep(4150);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                    Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                    startActivity(intent);
            }
        };
        thread.start();

//        Timer timer = new Timer();
//        timer.schedule(timetast, 5000);
    }
//    TimerTask timetast = new TimerTask() {
//        @Override
//        public void run() {
//
//            startActivity(new Intent(FirstActivity.this,MainActivity.class));//跳转登录界面
//        }
//    };
}
package com.hnucm.C202001020212;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class HomeActivity extends AppCompatActivity {
    Fragment_Set fragment_set = new Fragment_Set();
    Fragment_User fragment_user=new Fragment_User();
    Fragment_Daka fragment_daka=new Fragment_Daka();

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment,fragment_set).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment,fragment_user).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment,fragment_daka).commit();

        getSupportFragmentManager().beginTransaction()
                .show(fragment_daka)
                .hide(fragment_set)
                .hide(fragment_user)
                .commit();

        ImageView b1=findViewById(R.id.daka);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .show(fragment_daka)
                        .hide(fragment_set)
                        .hide(fragment_user)
                        .commit();

            }
        });

        ImageView b2=findViewById(R.id.set);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .show(fragment_set)
                        .hide(fragment_daka)
                        .hide(fragment_user)
                        .commit();

            }
        });

        ImageView b3=findViewById(R.id.user);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .show(fragment_user)
                        .hide(fragment_set)
                        .hide(fragment_daka)
                        .commit();

            }
        });

    }
}
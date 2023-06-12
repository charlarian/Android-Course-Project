package com.hnucm.C202001020212;

import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2);

        WebView webView = (WebView) findViewById(R.id.webView);


        webView.loadUrl("https://www.playstation.com/zh-hans-cn/support/account/");
        webView.setWebViewClient(new WebViewClient() {

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
                Log.d("TAG", "onReceivedSslError: "); //如果是证书问题，会打印出此条log到console
            }
        });
        webView.requestFocus();

    }
}
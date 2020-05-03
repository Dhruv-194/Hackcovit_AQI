package com.test.aqi_hackcovit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web3 extends AppCompatActivity {
    private WebView Webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web3);

        Webview= findViewById(R.id.webvi2);
        Webview.setWebViewClient(new WebViewClient());
        Webview.loadUrl("https://www.ndtv.com/topic/air-quality-index");

        WebSettings webSettings = Webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {

        if(Webview.canGoBack()){
            Webview.goBack();
        }
        else{
            super.onBackPressed();}
    }
}

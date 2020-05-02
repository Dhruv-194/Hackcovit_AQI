package com.test.aqi_hackcovit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web2 extends AppCompatActivity {
    private WebView Webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web2);
        Webview= findViewById(R.id.webvi);
        Webview.setWebViewClient(new WebViewClient());
        Webview.loadUrl("https://www.airnow.gov/aqi/aqi-basics/");

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

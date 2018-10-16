package com.example.gaurav.gauravmaanmarsplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActivityShowImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        final String imageUrl = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(imageUrl);

    }
}

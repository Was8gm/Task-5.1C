package com.example.task51c_subtask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class youtubeActivity extends AppCompatActivity {

    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);


        webView = findViewById(R.id.webView);



        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String VIDEO_URL = getIntent().getStringExtra("VIDEO_URL");
        String video = String.format("<iframe width=\"100%%\" height=\"100%%\" src=\" %s\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>", VIDEO_URL);
        webView.setWebChromeClient(new WebChromeClient());
        // Load a YouTube web page or embed a video
        webView.loadData(video,"text/html","utf-8");
    }
}
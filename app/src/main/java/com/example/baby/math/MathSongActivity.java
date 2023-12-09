package com.example.baby.math;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baby.R;

public class MathSongActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_song);

        webView = findViewById(R.id.webView);
        openYoutubeLink("https://www.youtube.com/watch?v=NG2aBtqazkY");
    }

    private void openYoutubeLink(String youtubeLink) {
        // 유튜브 링크를 웹뷰에서 열기
        webView.loadUrl(youtubeLink);
    }
}

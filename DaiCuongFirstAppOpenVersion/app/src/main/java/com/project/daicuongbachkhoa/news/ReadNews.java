package com.project.daicuongbachkhoa.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.daicuongbachkhoa.R;

public class ReadNews extends AppCompatActivity {

    private String
            urlNews;
    private WebView
            wvNews;
    private FloatingActionButton
            faGotoWeb;
    private LinearLayout
            btnGoListNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);
        wvNews = (WebView) findViewById(R.id.wvNews);
        faGotoWeb = findViewById(R.id.faGotoWeb);
        urlNews = getIntent().getStringExtra("LINK_NEWS");
        btnGoListNews = findViewById(R.id.btnGoListNews);

        readNews();
        faGotoWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoWeb(urlNews);
            }
        });
        btnGoListNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goListNews();
            }
        });
    }

    private void goListNews() {
        finish();
    }

    private void gotoWeb(String urlNews) {
        Uri uri = Uri.parse(urlNews);
        Intent url = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(url);
    }

    private void readNews() {
        wvNews.setWebViewClient(new WebViewClient());
        wvNews.loadUrl(urlNews);
        WebSettings webSettings = wvNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (wvNews.canGoBack()) {
            wvNews.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
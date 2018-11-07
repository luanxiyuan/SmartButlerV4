package com.lam.smartbutlerv4.ui;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.ui
 *  file name:          WebViewActivity
 *  create date:        2018/10/8 23:03
 *  creator:            Luan Xiyuan
 *  description:        TODO
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lam.smartbutlerv4.R;
import com.lam.smartbutlerv4.utils.StatusBarUtils;

public class WebViewActivity extends AppCompatActivity {
    private ProgressBar pb_loading;
    private WebView wv_displaypage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.bg_toolbar);
        setContentView(R.layout.activity_webview);

        initView();
    }

    private void initView() {
        pb_loading = findViewById(R.id.pb_loading);
        wv_displaypage = findViewById(R.id.wv_displaypage);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        //加载url
        //支持js
        wv_displaypage.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        wv_displaypage.getSettings().setSupportZoom(true);
        wv_displaypage.getSettings().setBuiltInZoomControls(true);
        //接口回调
        wv_displaypage.setWebChromeClient(new LocalWebChromeClient());
        //加载网页
        wv_displaypage.loadUrl(url);
        //在本地加载
        wv_displaypage.setWebViewClient(new LocalWebViewClient(url));
    }

    public class LocalWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress == 100) {
                pb_loading.setVisibility(View.GONE);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    public class LocalWebViewClient extends WebViewClient {
        String url = "";
        LocalWebViewClient(String url) {
            this.url = url;
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(this.url);
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}

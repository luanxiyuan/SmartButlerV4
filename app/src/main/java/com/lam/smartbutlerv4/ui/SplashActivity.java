package com.lam.smartbutlerv4.ui;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.ui
 *  file name:          SplashActivity
 *  create date:        2018/10/3 20:30
 *  creator:            Luan Xiyuan
 *  description:        TODO
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lam.smartbutlerv4.R;
import com.lam.smartbutlerv4.utils.Base64Util;
import com.lam.smartbutlerv4.utils.SPUtil;
import com.lam.smartbutlerv4.utils.StaticClass;
import com.lam.smartbutlerv4.utils.UtilTools;

public class SplashActivity extends AppCompatActivity {

    /**
     * 1.延时2000毫秒
     * 2.判断程序是否是第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */
    private TextView tv_splash;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if(isFirst()) {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        //test open app through browser
                        Intent intent = getIntent();
                        String schema = intent.getScheme();
                        Uri uri = intent.getData();
                        String webviewurl = "https://www.baidu.com";
                        if(uri != null) {
                            String host = uri.getHost();
                            String dataString = intent.getDataString();
                            String path = uri.getPath();
                            String queryString = uri.getQuery();
                            String encodedPath = uri.getEncodedPath();
                            webviewurl = uri.getQueryParameter("webviewurl");
                            webviewurl = Base64Util.decodeToString(webviewurl);
                        }

                        //开启下一个页面
                        Intent intent2 = new Intent(SplashActivity.this, WebViewActivity.class);
                        intent2.putExtra("url", webviewurl);
                        startActivity(intent2);
                        finish();

//                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                        startActivity(new Intent(SplashActivity.this, LocationActivity.class));

                    }
                    break;
            }
        }
    };

    private boolean isFirst() {
        boolean isFirst = SPUtil.getBoolean(this, StaticClass.IS_FIRST_RUN, true);
        if(isFirst) {
            SPUtil.putBoolean(this, StaticClass.IS_FIRST_RUN, false);
        }
        return isFirst;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {
        //延时2000毫秒
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);

//        tv_splash = findViewById(R.id.tv_splash);

        //设置字体
//        UtilTools.setFont(this, tv_splash);
    }

    //禁止返回键
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}

package com.lam.smartbutlerv4.ui;
/*
 *  project name:       SmartButlerV4
 *  pakcage name:       com.lam.smartbutlerv4.ui
 *  file name:          LocationActivity
 *  create date:        2018/10/15 0:00
 *  creator:            Luan Xiyuan
 *  description:        TODO
 */

import android.os.Bundle;

import com.baidu.mapapi.map.MapView;
import com.lam.smartbutlerv4.R;

public class LocationActivity extends BaseActivity {

    private MapView mMapView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        initView();
    }

    private void initView() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}

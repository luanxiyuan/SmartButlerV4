package com.lam.smartbutlerv4.application;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.ui
 *  file name:          BaseActivity
 *  create date:        2018/10/1 12:26
 *  creator:            Luan Xiyuan
 *  description:        TODO
 */

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.lam.smartbutlerv4.utils.StaticClass;
import com.tencent.bugly.Bugly;

import cn.bmob.v3.Bmob;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Bugly init
        Bugly.init(getApplicationContext(), StaticClass.BUGLY_APPID, true);
        //Bmob init
        Bmob.initialize(this, StaticClass.BMOB_APP_ID);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}

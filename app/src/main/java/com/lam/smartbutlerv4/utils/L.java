package com.lam.smartbutlerv4.utils;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.utils
 *  file name:          L
 *  create date:        2018/10/3 19:39
 *  creator:            Luan Xiyuan
 *  description:        Log封装类
 */

import android.util.Log;

public class L {
    //开关
    public static final boolean DEBUG = true;
    //TAG
    public static final String TAG = "Smart Butler";
    //5个等级：DIWEF
    public static void d(String text) {
        if(DEBUG) {
            Log.d(TAG, text);
        }
    }
    public static void i(String text) {
        if(DEBUG) {
            Log.i(TAG, text);
        }
    }
    public static void w(String text) {
        if(DEBUG) {
            Log.w(TAG, text);
        }
    }
    public static void e(String text) {
        if(DEBUG) {
            Log.e(TAG, text);
        }
    }
    public static void f(String text) {
        if(DEBUG) {
            Log.wtf(TAG, text);
        }
    }
}

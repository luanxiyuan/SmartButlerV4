package com.lam.smartbutlerv4.utils;
/*
 *  project name:       SmartButler
 *  pakcage name:       com.lam.smartbutlerv4.utils
 *  file name:          UtilTools
 *  create date:        2018/10/1 12:36
 *  creator:            Luan Xiyuan
 *  description:        工具类
 */

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class UtilTools {

    //设置字体
    public static void setFont(Context mContext, TextView textView) {
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(), "fonts/font_shuweixingshufanti.ttf");
        textView.setTypeface(fontType);
    }
}

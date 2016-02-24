package com.chinawidth.dongtest.utils;

import android.util.Log;

import com.chinawidth.dongtest.app.MyConstant;


public class MyLog {
    public static void i(String msg) {
        if (MyConstant.isShowLog) {
            Log.i("ChinaWidth", msg);
        }
    }
}

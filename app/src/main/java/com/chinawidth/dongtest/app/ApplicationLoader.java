package com.chinawidth.dongtest.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/10/29.
 */
public class ApplicationLoader extends Application {

    public static volatile Context applicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }


}

package com.chinawidth.dongtest.app;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.chinawidth.dongtest.utils.LruBitmapCache;
import com.chinawidth.dongtest.utils.VolleyManager;


/**
 * Created by Administrator on 2015/10/29.
 */
public class ApplicationLoader extends Application {

    public static final String TAG=ApplicationLoader.class.getSimpleName();

    public static volatile Context applicationContext;
    private static final Object sync = new Object();

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        VolleyManager.getInstance();
    }



}

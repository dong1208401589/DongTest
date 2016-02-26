package com.chinawidth.dongtest.utils;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.chinawidth.dongtest.app.ApplicationLoader;

/**
 * Created by Administrator on 2016/2/26.
 */
public class VolleyManager {

    public static final String TAG=ApplicationLoader.class.getSimpleName();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static volatile VolleyManager instance;

    private VolleyManager(){};

    public static VolleyManager getInstance(){
        VolleyManager volleyManager=instance;
        if(volleyManager==null){
            synchronized (VolleyManager.class){
                volleyManager=instance;
                if(volleyManager==null){
                    volleyManager=new VolleyManager();
                }
            }
        }
        return volleyManager;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(ApplicationLoader.applicationContext);
        }
        return mRequestQueue;
    }

    public ImageLoader getImageLoader(){
        getRequestQueue();
        if(mImageLoader==null){
            mImageLoader=new ImageLoader(this.mRequestQueue,new LruBitmapCache());
        }
        return mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req,String tag){
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addTorequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue!=null){
            mRequestQueue.cancelAll(tag);
        }
    }
}

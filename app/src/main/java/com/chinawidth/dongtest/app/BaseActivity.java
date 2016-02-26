package com.chinawidth.dongtest.app;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/2/25.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initData();
    }


    public abstract void initViews();

    public abstract void initData();


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

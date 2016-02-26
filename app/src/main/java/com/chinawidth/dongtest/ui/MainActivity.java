package com.chinawidth.dongtest.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chinawidth.dongtest.R;
import com.chinawidth.dongtest.app.BaseActivity;

/**
 * Created by Administrator on 2016/2/25.
 */
public class MainActivity extends BaseActivity{

    TextView tv_downmanager;

    @Override
    public void initViews() {
        setContentView(R.layout.activity_main);
        tv_downmanager= (TextView) findViewById(R.id.tv_downmanager);


        tv_downmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DownloadActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

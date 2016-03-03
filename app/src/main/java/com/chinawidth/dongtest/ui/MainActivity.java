package com.chinawidth.dongtest.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.chinawidth.dongtest.R;
import com.chinawidth.dongtest.Service.MessengerService;
import com.chinawidth.dongtest.app.BaseActivity;
import com.chinawidth.dongtest.utils.ToastUtil;

/**
 * Created by Administrator on 2016/2/25.
 */
public class MainActivity extends BaseActivity{

    private static final String TAG="MainActivity";

    Button btn_downmanager;
    Button btn_bind_messgerservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(R.style.AppTheme_ChinaWidthHelp);
        super.onCreate(savedInstanceState);
        initViews();
        initData();
    }

    @Override
    public void initViews() {

        setContentView(R.layout.activity_main);

        btn_downmanager= (Button) findViewById(R.id.btn_downmanager);
        btn_bind_messgerservice= (Button) findViewById(R.id.btn_bind_messgerservice );

        btn_downmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DownloadActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btn_bind_messgerservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindMessgerService();
            }
        });
    }

    private static Messenger mGetReplyMessenger=new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 101:
                   Log.i(TAG,"receive msg from Service:"+msg.getData().getString("reply"));
                    break;
                default:
                    break;
            }
        }
    }

    private Messenger mService;
    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             mService=new Messenger(service);
            Message msg=Message.obtain(null,100);
            Bundle data=new Bundle();
            data.putString("msg", "hello,this is client.");

            msg.setData(data);
            msg.replyTo=mGetReplyMessenger;
            try {
                mService.send(msg);
                Log.i(TAG,"bindService");
            } catch (RemoteException e) {
                e.printStackTrace();
            }



            //ToastUtil.showToastShort(MainActivity.this,"bindMessengerService");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private void bindMessgerService(){
        Intent intent=new Intent(this, MessengerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void initData() {
    }



    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}

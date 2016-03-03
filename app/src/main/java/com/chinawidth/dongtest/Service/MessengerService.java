package com.chinawidth.dongtest.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by Administrator on 2016/3/3.
 */
public class MessengerService extends Service {

    private static final String TAG="MessageerService";
    //messenger 对aidl做了封装，它一次只处理一个请求
    private static class MesssagerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 100:
                    Log.i(TAG,"receive msg form Client"+msg.getData().getString("msg"));
                    Messenger client=msg.replyTo;
                    Message replyToMsg=Message.obtain(null,101);
                    Bundle data=new Bundle();
                    data.putString("reply","嗯，我收你的信息了，等一下再回复你哈。");
                    replyToMsg.setData(data);
                    try {
                        client.send(replyToMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger=new Messenger(new MesssagerHandler());

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "create service");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG, "onRebind");

    }
}

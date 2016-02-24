package com.chinawidth.dongtest.ui;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import com.chinawidth.dongtest.R;
import com.chinawidth.dongtest.utils.ToastUtil;

import java.io.File;

/**
 * Created by Administrator on 2016/1/6.
 */
public class DownloadActivity extends Activity {
    // private ProgressBar progressBar;
    DownloadManager downManager;
    private DownLoadCompleteReceiver receiver;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //开始下载更新包
        downManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        download("http://download.z-enter.com:62001/chinawidthSysupload/zzm5.0.3.apk");
        IntentFilter filter = new IntentFilter();
        filter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        filter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiver = new DownLoadCompleteReceiver();
        registerReceiver(receiver, filter);
    }

    long id;
    private void download(String url) {
        //删除之前下载的更新包
        File file=new File(this.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"zzm.apk");
        if(file.exists()){
            file.delete();
        }
        DownloadManager.Request request =new DownloadManager.Request(Uri.parse(url));
        //设置在什么网络情况下进行下载
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI| DownloadManager.Request.NETWORK_MOBILE);
        //设置通知栏标题
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("下载");
        request.setDescription("真知码正在下载");
        request.setAllowedOverRoaming(false);
       //request.setMimeType(".apk application/vnd.android.package-archive");
        //设置文件存放目录
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "zzm.apk");
        id = downManager.enqueue(request);
        handler.postDelayed(querTaskRunnable,1500);
    }

    Runnable querTaskRunnable=new Runnable() {
        @Override
        public void run() {
            queryTaskByIdandUpdateView(id);
        }
    };

    private void queryTaskByIdandUpdateView(long id) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(id);
        Cursor cursor = downManager.query(query);
        String size = "0";
        String sizeTotal = "0";
        if (cursor.moveToNext()) {
            size = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
            sizeTotal = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
        }
        cursor.close();
        //progressBar.setMax(Integer.valueOf(sizeTotal));
        // progressBar.setProgress(Integer.valueOf(size));
    }

    /**
     * 监听下载完成接收者
     */
    private class DownLoadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                ToastUtil.showToastShort(context, "下载任务已经完成");
                handler.removeCallbacks(querTaskRunnable);
                //开始安装app
                File file= new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),"zzm.apk");
                if(file.exists()){
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
                    context.startActivity(intent);
                }
                System.exit(0);
            } else if (intent.getAction().equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) {
            }
        }
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}

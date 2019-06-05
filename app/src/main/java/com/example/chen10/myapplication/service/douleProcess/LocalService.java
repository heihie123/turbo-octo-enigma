package com.example.chen10.myapplication.service.douleProcess;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocalService extends Service {

    private MyBinder mMyBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        mMyBinder = new MyBinder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("deamon", "deamon",
                    NotificationManager.IMPORTANCE_LOW);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager == null) {
                return;
            }
            manager.createNotificationChannel(channel);

            Notification notifacation = new Notification.Builder(this, "deamon")
                    .setAutoCancel(true)    // 点击通知栏 ，移除通知
                    .setCategory(Notification.CATEGORY_SERVICE) // 设置通知类型运行后台服务的指示
                    .setOngoing(true)       // 设置它为一个正在进行的通知，通常表示一个后台任务
                    .setPriority(Notification.PRIORITY_LOW) // 设置通知优先级
                    .build();
            startForeground(10, notifacation);   // 启动一个前台服务
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //如果 18 以上的设备 启动一个Service startForeground给相同的id
            //然后结束那个Service
            startForeground(10, new Notification());
            startService(new Intent(this, InnerService.class));
        } else {
            startForeground(10, new Notification());
        }
        Log.e("keep", "双进程local保活");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, RemoteService.class),
                new MyserviceConnection(),
                BIND_AUTO_CREATE);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, LocalService.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMyBinder;
    }

    class MyserviceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(LocalService.this, RemoteService.class));
            bindService(new Intent(LocalService.this, RemoteService.class),
                    new MyserviceConnection(),
                    BIND_AUTO_CREATE);
        }

        @Override
        public void onBindingDied(ComponentName name) {

        }

        @Override
        public void onNullBinding(ComponentName name) {

        }
    }

    public static class InnerService extends Service {

        @Override
        public void onCreate() {
            super.onCreate();
            // 可能通过在启动一个service，然后startForeground一个相同的id, 然后立刻stopForeground(true)，可以将其消失（注意：这其实是google的bug）
            startForeground(10, new Notification());
            stopSelf();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
    }
}

package com.example.chen10.myapplication.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * service保活
 */
public class ForegroundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /**
             * IMPORTANCE_NONE 关闭通知
             * IMPORTANCE_MIN 开启通知，不会弹出，但没有提示音，状态栏中无显示
             * IMPORTANCE_LOW 开启通知，不会弹出，不发出提示音，状态栏中显示
             * IMPORTANCE_DEFAULT 开启通知，不会弹出，发出提示音，状态栏中显示
             * IMPORTANCE_HIGH 开启通知，会弹出，发出提示音，状态栏中显示
             */
            NotificationChannel channel = new NotificationChannel("deamon", "deamon",
                    NotificationManager.IMPORTANCE_LOW);

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager == null) {
                return;
            }
            manager.createNotificationChannel(channel);

            Notification notifacation = new Notification.Builder(this, "deamon")
                    .setAutoCancel(true)    // 点击通知栏 ，移除通知
                    /**
                     * Notification.CATEGORY_CALL //呼入（语音或视频）或类似的同步通信请求。
                     * Notification.CATEGORY_MESSAGE //直接消息（短信、即时消息等）。
                     * Notification.CATEGORY_EMAIL //异步批量消息（电子邮件）。
                     * Notification.CATEGORY_EVENT //日历事件。
                     * Notification.CATEGORY_PROMO //促销或广告。
                     * Notification.CATEGORY_ALARM //闹钟或定时器。
                     * Notification.CATEGORY_PROGRESS //长时间后台操作的进展。
                     * Notification.CATEGORY_SOCIAL //社交网络或共享更新。
                     * Notification.CATEGORY_ERROR //后台操作或者身份验证状态出错。
                     * Notification.CATEGORY_TRANSPORT //回放媒体传输控制。
                     * Notification.CATEGORY_SYSTEM //系统或者设备状态更新，预留给系统使用。
                     * Notification.CATEGORY_SERVICE //运行后台服务的指示。
                     * Notification.CATEGORY_RECOMMENDATION //针对某一事物的具体及时的建议。
                     * Notification.CATEGORY_STATUS //关于设备或者上下文状态的正在进行的信息。
                     * Notification.CATEGORY_REMINDER //用户预定提醒。
                     */
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
        Log.e("keep", "service保活");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, ForegroundService.class));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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

package com.example.chen10.myapplication.service;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.example.chen10.myapplication.service.douleProcess.LocalService;
import com.example.chen10.myapplication.service.douleProcess.RemoteService;

import org.w3c.dom.Text;

import java.util.List;

/**
 * jobScheduler保活
 */
public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startJob(this);
        }

        // 轮训判断是否服务活着
        boolean isLocalServiceRun = isServiceRunning(LocalService.class.getName());
        boolean isRemoteServiceRun = isServiceRunning(RemoteService.class.getName());
        if (!isLocalServiceRun || isRemoteServiceRun) {
            startService(new Intent(this, LocalService.class));
            startService(new Intent(this, RemoteService.class));
        }
        return false;
    }

    public static void startJob(Context context) {
        Log.e("keep", "开始执行jobservice");
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(10,
                new ComponentName(context.getPackageName(), MyJobService.class.getName()))
                .setPersisted(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMinimumLatency(1000);
        } else {
            builder.setPeriodic(1000);
        }
        jobScheduler.schedule(builder.build());
    }

    private boolean isServiceRunning(String serviceName) {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServiceInfos = am.getRunningServices(10);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServiceInfos) {
            if (TextUtils.equals(runningServiceInfo.service.getClassName(), serviceName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

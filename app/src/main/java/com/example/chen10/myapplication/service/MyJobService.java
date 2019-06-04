package com.example.chen10.myapplication.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

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

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}

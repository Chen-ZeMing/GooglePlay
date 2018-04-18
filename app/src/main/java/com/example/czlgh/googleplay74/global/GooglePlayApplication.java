package com.example.czlgh.googleplay74.global;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by CZLGH on 2018/2/10.
 */

public class GooglePlayApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler(){
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();

        //主线程id
        mainThreadId = android.os.Process.myTid();

    }

}

package com.example.czlgh.googleplay74.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.czlgh.googleplay74.global.GooglePlayApplication;

import static com.example.czlgh.googleplay74.global.GooglePlayApplication.getHandler;

/**
 * Created by CZLGH on 2018/2/10.
 */

public class UIUtils {

    public static Context getContext(){
        return GooglePlayApplication.getContext();
    }

    public static int getMainThreadId(){
        return GooglePlayApplication.getMainThreadId();
    }

    public static String getString(int id){
        return getContext().getResources().getString(id);
    }


    public static Drawable getDrawable(int id){
        return getContext().getResources().getDrawable(id);
    }

    public static int getColor(int id){
        return getContext().getResources().getColor(id);
    }

    public static int getDimen(int id){
        return getContext().getResources().getDimensionPixelSize(id);//返回具体的像素值

    }

//    public static int dip2px(float dip){
//        float density =getContext().getResources().getDisplayMetrics().density;
//        return (int) (dip*density+0.5f);
//    }

//    public static float px2dip(int px){
//        float density=getContext().getResources().getDisplayMetrics().density;
//        return px/density;
//    }

    public static View inflate(int id){
        return View.inflate(getContext(),id,null);
    }


    /*判断是否在主线程*/

    public static boolean isRunOnUIThread(){
        int myTid=android.os.Process.myTid();

        if(myTid==getMainThreadId()){
            return true;
        }

        return false;

    }
    public static void runOnUITHread(Runnable r){
        if(isRunOnUIThread()){
            r.run();
        }
        else { getHandler().post(r);

        }
    }
    //根据id获取状态选择器
    public static ColorStateList getColorStateList(int id){
        return getContext().getResources().getColorStateList(id);

    }


}

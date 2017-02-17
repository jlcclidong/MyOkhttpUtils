package com.eason.jlccl.httputils.utils;

/**
 * Created by jlccl on 2017/2/17.
 */

public class Log {
    private static boolean debug =false;
    public static String TAG= "okhttp";
    public static void e(String msg){
        if (!debug)
        android.util.Log.e(TAG, msg);
    }
}

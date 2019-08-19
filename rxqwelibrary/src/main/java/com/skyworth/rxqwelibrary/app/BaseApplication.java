package com.skyworth.rxqwelibrary.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;

import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by skyworth on 16/3/15.
 */
public class BaseApplication extends Application implements CrashHandler.ICrashHandler{

    private static String PREF_NAME = "creativelocker.pref";

    static Context _context;
    static Resources _resource;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        LeakCanary.install(this);

        Utils.init(this);

        _context = getApplicationContext();
        _resource = _context.getResources();

    }


    public static synchronized BaseApplication context() {
        return (BaseApplication) _context;
    }

    public static Resources resources() {
        return _resource;
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void apply(SharedPreferences.Editor editor) {
        /**
         * apply 异步提交
         * commit 同步提交
         * commit 会返回布尔值
         */
        editor.apply();
    }

    public static void set(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        apply(editor);
    }

    public static void set(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        apply(editor);
    }

    public static void set(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        apply(editor);
    }

    public static boolean get(String key, boolean defValue) {
        return getPreferences().getBoolean(key, defValue);
    }

    public static String get(String key, String defValue) {
        return getPreferences().getString(key, defValue);
    }

    public static int get(String key, int defValue) {
        return getPreferences().getInt(key, defValue);
    }

    public static long get(String key, long defValue) {
        return getPreferences().getLong(key, defValue);
    }

    public static float get(String key, float defValue) {
        return getPreferences().getFloat(key, defValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences() {
        SharedPreferences pre = context().getSharedPreferences(PREF_NAME,
                Context.MODE_MULTI_PROCESS);
        return pre;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static SharedPreferences getPreferences(String prefName) {
        return context().getSharedPreferences(prefName,
                Context.MODE_MULTI_PROCESS);
    }


    /**
     *
     * 全局未处理异常的处理
     * <p>
     * Description: 全局未处理异常的处理，界面层可以根据需要定制自己的业务，例如上传异常日志等。
     * <p>
     * @date 2014年3月5日
     * @param thread 线程信息
     * @param ex 异常等信息
     * @return 返回true代表事件被消耗掉了，底层不再处理。
     */
    public boolean onGlobalUncaughtExceptionOccured(Thread thread, Throwable ex)
    {
        return false;
    }

    @Override
    public boolean onUncaughtExceptionOccured(Thread thread, Throwable ex)
    {
        return onGlobalUncaughtExceptionOccured(thread, ex);
    }
}
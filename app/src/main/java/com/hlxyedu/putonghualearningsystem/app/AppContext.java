package com.hlxyedu.putonghualearningsystem.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.support.multidex.MultiDex;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.fifedu.record.recinbox.bl.record.RecorderManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hlxyedu.putonghualearningsystem.api.Constants;
import com.hlxyedu.putonghualearningsystem.di.component.AppComponent;
import com.hlxyedu.putonghualearningsystem.di.component.DaggerAppComponent;
import com.hlxyedu.putonghualearningsystem.di.module.AppModule;
import com.hlxyedu.putonghualearningsystem.di.module.HttpModule;
import com.lzx.starrysky.manager.MusicManager;
import com.skyworth.rxqwelibrary.app.AppConfig;
import com.skyworth.rxqwelibrary.app.BaseApplication;
import com.skyworth.rxqwelibrary.app.CrashHandler;
import com.skyworth.rxqwelibrary.service.InitializeService;


/**
 * 作者：skyworth on 2017/7/10 10:00
 * 邮箱：dqwei@iflytek.com
 */

public class AppContext extends BaseApplication {

    private static final String TAG = AppContext.class.getSimpleName();

    private static AppContext instance;

    public static AppComponent appComponent;

    private String uid;

    /*//用户uservo
    private UserVO userVO;

    public UserVO getUserVO() {

        if(userVO == null){
            SharedPreferences mSPrefs = getSharedPreferences(SyncStateContract.Constants.BOSS, Context.MODE_PRIVATE);
            String userinfo = mSPrefs.getString(Constants.USER_INFO, "");
            try {
                Gson gson = new Gson();
                userVO = gson.fromJson(userinfo,UserVO.class);
            }catch (JsonSyntaxException ex){

            }
        }

        return userVO;
    }*/

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override//初始化其他信息
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 音频播放
        MusicManager.initMusicManager(this);
        init();

        //在子线程中完成其他初始化
        InitializeService.start(this);

        SharedPreferences mSPrefs = getSharedPreferences(Constants.BOSS, Context.MODE_PRIVATE);
//        uid = mSPrefs.getString(Constants.UID, "");

        //初始化崩溃信息
//        initCrash();
    }

    private void initCrash() {
        // 获取异常信息捕获类实例
        //        开发期间不要监听 稍后放开
        CrashHandler crashHandler = CrashHandler.getInstance(getApplicationContext());

        crashHandler.setICrashHandlerListener(this);
        // 初始化
        crashHandler.init(getApplicationContext());
    }

    /*public String getUid() {
        if (ObjectUtils.isEmpty(uid)) {
            SharedPreferences mSPrefs = getSharedPreferences(Constants.BOSS, Context.MODE_PRIVATE);
            uid = mSPrefs.getString(Constants.UID, "");
        }
        return uid;
    }*/

    //退出时清空
    /*public void clearUp() {
        uid = "";
        userVO = null;
    }*/

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

    /**
     * 获得当前app运行的AppContext
     */
    public static AppContext getInstance() {
        return instance;
    }

    private String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    private void init() {


        // 初始化音频录制工具
        RecorderManager.createInstance(this);

        AppConfig.getAppConfig(this);

    }

}

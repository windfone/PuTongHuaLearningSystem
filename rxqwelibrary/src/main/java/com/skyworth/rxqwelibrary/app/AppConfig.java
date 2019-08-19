package com.skyworth.rxqwelibrary.app;

import android.content.Context;

import com.blankj.utilcode.util.FileUtils;

/**
 * 应用需要初始化的方法管理
 * @author skyworth
 *
 * 2015年7月28日
 */
public class AppConfig {
    public static final String TAG = "AppConfig";

    private Context mContext;
    private static AppConfig appConfig;

    private String[] mAppFolders = {

            AppConstants.CACHE_PATH,
            AppConstants.PARENT_FOLD_PATH,

            AppConstants.DOWNLOAD_PATH,
            AppConstants.LOGS_PATH,

            AppConstants.RECORD_DOWNLOAD_PATH,
            AppConstants.FILE_DOWNLOAD_PATH
    };

    private AppConfig(){
        initAppFolder();
    }

    public static AppConfig getAppConfig(Context context) {
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }


    /**
     * 初始化应用文件目录
     */
    public void initAppFolder(){
        for(String str:mAppFolders){
            FileUtils.createOrExistsDir(str);
        }
    }



}

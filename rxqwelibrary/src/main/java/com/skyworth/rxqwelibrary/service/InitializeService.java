package com.skyworth.rxqwelibrary.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.blankj.utilcode.util.DeviceUtils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.skyworth.rxqwelibrary.BuildConfig;
import com.skyworth.rxqwelibrary.R;
import com.skyworth.rxqwelibrary.app.AppConfig;
import com.skyworth.rxqwelibrary.app.AppConstants;
import com.skyworth.rxqwelibrary.utils.log.TxtFormatStrategy;

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication(){
        //初始化需要的文件夹
        initFinder();

        //初始化log
        initLogger();

        //初始化其他信息
        init();

    }

    private void initFinder(){
        //设置缓存路径
        String cachePath;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            // /storage/emulated/0/Android/data/com.fifedu.mhk/cache

            try {
                cachePath = getExternalCacheDir().getPath();
            }catch (Exception ex)
            {
                cachePath = getCacheDir().getPath();
            }

        } else {
            // /data/data/com.fifedu.mhk/cache
            cachePath = getCacheDir().getPath();
        }
        AppConstants.initPath(cachePath);
    }

    private void initLogger(){
        // 初始化日志写文件的工具
        //DEBUG版本才打控制台log
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().
                    tag(getString(R.string.app_name)).build()));
        }
        //把log存到本地
        Logger.addLogAdapter(new DiskLogAdapter(TxtFormatStrategy.newBuilder().
                tag(getString(R.string.app_name)).build(getPackageName(), getString(R.string.app_name))));

        Logger.d( "AppContext Created");
        Logger.d( "Product Model: " + DeviceUtils.getModel() + "\nApi Level: "
                + DeviceUtils.getSDKVersionName() + "\nVersion: " + DeviceUtils.getSDKVersionCode());
    }

    private void init() {

//        LogUtil.debug(TAG,"配置多态布局");
//        PageStateLayout.Builder.setErrorView(R.layout.page_state_error)
//                .setEmptyView(R.layout.page_state_empty);
        //初始化volley


        AppConfig.getAppConfig(this);


    }

}

package com.skyworth.rxqwelibrary.app;

import android.os.Environment;

import com.blankj.utilcode.util.TimeUtils;

import java.io.File;

/**
 * 常用的变量值
 *
 * @author weidingqiang
 *
 *         2015年6月2日
 */
public class AppConstants {

    // 缓存
    public static String PARENT_FOLD_PATH = "";

    // 外挂 SD 卡 根目录
    public static String PLUG_IN_SDCARD = "";

    // SD卡 试题路径
    public static String SD_PAPER_PATH = "";

    // 外挂SD卡的 版本更新的apk存放路径
    public static String SD_APK_PATH = "";

    // 每日答题包保存路径
//    public static String ANSWER_PATH = "";
    public static String RECORD_PRACTICE_PATH = "";
    public static String RECORD_EXAM_PATH = "";

    // 复制到sd卡下的 答案路径
    public static String SD_ANSWER_PATH = "";

    // sd卡下考试人员的照片路径
    public static String SD_PHOTO_PATH = "";

    // sd 卡下 头像图片.mpeg压缩包的路径
    public static String SD_PHOTO_ZIP_PATH = "";

    // sd卡下考试人员的姓名丶考号丶身份证号路径
    public static String SD_EXAMINFO_PATH = "";

    // sd卡中的考试时间
    public static String SD_EXAMDATE_PATH = "";



    //缓存相关
    /**
     * 缓存地址
     */
    public static String CACHE_PATH = "";



    /**
     * logs 文件
     * @param string
     */
    public static String LOGS_PATH = "";


    public static void initPath(String string){
        PARENT_FOLD_PATH = string+ File.separator;
        CACHE_PATH = PARENT_FOLD_PATH;

        DOWNLOAD_PATH = PARENT_FOLD_PATH + "download" + File.separator;
        LOGS_PATH = PARENT_FOLD_PATH + "logs" + File.separator;

        RECORD_DOWNLOAD_PATH = DOWNLOAD_PATH + "audio" + File.separator;
        FILE_DOWNLOAD_PATH = DOWNLOAD_PATH + "files" + File.separator;
        APK_DOWNLOAD_PATH = DOWNLOAD_PATH + "apk" + File.separator;
        RECORD_PRACTICE_PATH = PARENT_FOLD_PATH + "record" + File.separator + "practice" + File.separator;
        RECORD_EXAM_PATH = PARENT_FOLD_PATH + "record" + File.separator + "exam" + File.separator;


//        RECORD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()  + File.separator + "PTHLearning" + File.separator + "file" +  File.separator +  "record" +  File.separator;
    }



    //--------------------------------------------------------------------------//
    //下载相关
    /**
     * 下载文件的存储地址
     */
    public static String DOWNLOAD_PATH = "";
    /**
     * 录音文件下载存储地址
     */
    public static String RECORD_DOWNLOAD_PATH = "";
    /**
     * 视频文件下载地址
     */
    /**
     * 普通文件下载存储地址
     */
    public static String FILE_DOWNLOAD_PATH = "";

    //-----------------------------------------------//
    /**
     * APK文件下载地址
     */
    public static String APK_DOWNLOAD_PATH = "";

    // -----------------文件后缀名-------------
    /**
     * 音频文件后缀名
     */
    public static final String AUDIO_FILE_SUFFIX = ".mp3";
    /**
     * 音频文件后缀名
     */
    public static final String SPEECHS_FILE_SUFFIX = ".spx";

    //------------------------------------------------------//


}

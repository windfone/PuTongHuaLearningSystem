package com.iflytek.base.audio;


import android.util.Log;

/**
 * 对音频进行MP4音频编码的native代码封装
 * 
 * @author zhangyun
 * @date 2014-12-9
 */
public class AacEncoder {
    /**
     * bitSpeed 压缩速率,默认32000
     */
    public static final int BIT_RATE_32000 = 32000;
    public static final int BIT_RATE_48000 = 48000;
    public static final int BIT_RATE_64000 = 64000;
    /**
     * 声道数
     */
    public static final int CHANNLE_ONE = 1;

    private static boolean mIsloaded = false;
    static {
        /**
         * 增加MP3,MP4接口,修改为V3 2014-12-9
         */
        try {
            System.loadLibrary("aac_encoder");
            mIsloaded = true;
        } catch (UnsatisfiedLinkError e) {
            Log.d("Record", e.toString());
        }

    }

    public static boolean isJniLoaded() {
        return mIsloaded;
    }

    /**
     * 初始化参数
     */
    public static int init(int channle,int sampleRate,int bitRate) {
        if (mIsloaded) {
            return nativeInit(channle, sampleRate, bitRate);
        }
        return -1;
    }

    /**
     * 对数据块MP3编码
     */
    public static int encodeData(byte[] wavData, int length, byte[] outBuffer,
            int outLength) {
        if (mIsloaded) {
            return nativeEncodeData(wavData, length, outBuffer, outLength);
        }
        return 0;
    }

    /**
     * 结束调用
     * 
     * @return
     */
    public static int finish() {
        if (mIsloaded) {
            return nativeFinish();
        }
        return -1;
    }


    /**
     * 流式编码
     */
    private static native int nativeInit(int channel, int sampleRate,
            int bitSpeed);

    private static native int nativeEncodeData(byte[] wavData, int length,
            byte[] outBuffer, int outLenth);

    private static native int nativeFinish();
}

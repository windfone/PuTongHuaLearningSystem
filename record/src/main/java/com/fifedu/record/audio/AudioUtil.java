package com.fifedu.record.audio;


import android.util.Log;

import com.iflytek.audio.AudioProcess;

public class AudioUtil {
    
    /**
     * AAC转SPEEX方法 需要先把ACC解成PCM 再把PCM编码成SPEEX
     * @param aacPath aac文件地址 例："/sdcard/test.aac"
     * @param pcmPath pcmPath文件地址 例："/sdcard/test.pcm"
     * @param spxPath speex文件地址 例："/sdcard/test.spx"
     * @return 返回值 -1初始化失败 0成功 其他？？？
     */
    public int aac2speex(String aacPath, String pcmPath, String spxPath) {
        return aac2pcm(aacPath, pcmPath) != 0 ? aac2pcm(aacPath, pcmPath) : pcm2spx(pcmPath, spxPath) != 0 ? pcm2spx(pcmPath, spxPath) : 0 ;
    }
    
    /****************************** 内部方法 ********************************/
    
    /**
     * AAC转PCM 
     * @param aacPath 你知道这是啥
     * @param pcmPath 不多说了
     * @return 返回0是对的 -1初始化错误 其他是异常
     */
    private int aac2pcm(String aacPath, String pcmPath) {
        int handle = AudioProcess.createInstance(ProcessType.AAC_DEC);
        
        int ret = AudioProcess.init(handle);
        if (ret != 0) {
            Log.e("初始化失败aac2pcm:-1", "ret= " + ret);
            return -1;
        }

        int result = AudioProcess.procssFile(handle, aacPath, pcmPath);
        Log.i("aac2pcm procssFile", "result= " + result);

        AudioProcess.unInit(handle);
        AudioProcess.destroyInstance(handle);
        
        return result;
    }
    
    /**
     * PCM转SPEEX
     * @param pcmPath 略
     * @param spxPath 同上
     * @return 返回0是对的 -1初始化错误 其他是异常
     */
    private int pcm2spx(String pcmPath, String spxPath) {
        int handle = AudioProcess.createInstance(ProcessType.SPEEX_ENC);
        AudioProcess.setParam(handle, "sampleRate", 16000);
        AudioProcess.setParam(handle, "channels", 1);
        AudioProcess.setParam(handle, "bitRate", 256000);
        
        int ret = AudioProcess.init(handle);
        if (ret != 0) {
            Log.e("初始化失败pcm2spx:-1", "ret= " + ret);
            return -1;
        }

        int result = AudioProcess.procssFile(handle, pcmPath, spxPath);
        Log.i("pcm2spx procssFile", "result= " + result);

        AudioProcess.unInit(handle);
        AudioProcess.destroyInstance(handle);
        
        return result;
    }
}

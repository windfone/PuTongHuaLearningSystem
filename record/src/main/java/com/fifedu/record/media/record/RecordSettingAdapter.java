package com.fifedu.record.media.record;

import android.media.MediaRecorder.AudioSource;

/**
 * 音频录制参数对象
 * @author HongYu
 *
 * 2015年7月27日
 */
public class RecordSettingAdapter implements IRecorderAdapter{
    private int mAudioSource = AudioSource.MIC;
    private int mSampleRate = PcmRecorder.SAMPLE_RATE_16K;
    
    private static RecordSettingAdapter _instance;
    
    public static RecordSettingAdapter getInstance(){
        if(null == _instance)
        {
            _instance = new RecordSettingAdapter();
        }
        return _instance;
    }
    
    private RecordSettingAdapter() {
    }
    @Override
    public int getSampleRate() {
        return mSampleRate;
    }

    /**
     * 外部调用设采样
     * @param value
     */
    public void setSampleRate(int value){
        mSampleRate = value;
    }
    
    /**
     * 设置分辨率
     * @param type
     */
    public void setAudioSource(int type){
        mAudioSource = type;
    }
    @Override
    public int getAudioSource(int type) {
        if (type == IRecorderAdapter.TYPE_DEF){
            return mAudioSource;
        }else {
            return -1;
        } 
    }

}

package com.fifedu.record.media.record;

import android.media.AudioFormat;

/**
 * 录音配置接口
 * @author zhangyun
 *
 */
public interface IRecorderAdapter {
    public final int TYPE_DEF = 0; //默认的音频源
    public final int TYPE_RESERVE = 1; //备选的音频源
    public final int CHANNEL_IN_FRONT_BACK = AudioFormat.CHANNEL_IN_FRONT | AudioFormat.CHANNEL_IN_BACK; //
    public final int CHANNEL_IN_MONO = AudioFormat.CHANNEL_IN_MONO;
    public final int CHANNEL_IN_STEREO = AudioFormat.CHANNEL_IN_STEREO;
    public int getSampleRate(); //采样率
    public int getAudioSource(int type);//音频源类型
}


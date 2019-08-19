package com.fifedu.record.media.player;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.util.Log;


/**
 * AudioData class ,use AudioTrack class deal audio data.
 */
public class IflyAudioPlayer {
    private static final String TAG = "SPEECH_AudioPlayer";

    private AudioTrack mAudio = null;
    private int mStreamType = AudioManager.STREAM_MUSIC;
    private static final int SAMPLE_RATE = 16000;
    private static final int DEF_BUFFER_SIZE = 1280;
    private int mBuffSize; // Need runtime calculation.
    private boolean isStopping = false;
    private Object mAudioLock = new Object();

    /**
     * 创建一个默认的Audio播放
     * 
     * @return
     */
    public static IflyAudioPlayer createAudioPlayer() {
        return new IflyAudioPlayer(AudioManager.STREAM_MUSIC, SAMPLE_RATE,
                AudioFormat.CHANNEL_OUT_MONO);
    }

    public static IflyAudioPlayer createAudioPlayer(int audioType) {
        return new IflyAudioPlayer(audioType, SAMPLE_RATE,
                AudioFormat.CHANNEL_OUT_MONO);
    }

    public IflyAudioPlayer(int audioType, int sampleRate, int channelType) {
        createAudio(audioType, sampleRate, channelType);
    }

    private void createAudio(int audioType, int sampleRate, int channelType)
            throws IllegalArgumentException {
        if (0 == sampleRate) {
            sampleRate = SAMPLE_RATE;
        } 
        mStreamType = audioType;
        mBuffSize = AudioTrack.getMinBufferSize(sampleRate, channelType,
                AudioFormat.ENCODING_PCM_16BIT) * 4;
        if (null != mAudio) {
            release();
        }
        if (mBuffSize <= 0) {
            mBuffSize = DEF_BUFFER_SIZE;
        }

        try {
            mAudio = new AudioTrack(mStreamType, sampleRate, channelType,
                    AudioFormat.ENCODING_PCM_16BIT, mBuffSize,
                    AudioTrack.MODE_STREAM);
        } catch (IllegalArgumentException e) {
            Log.d(TAG, "AudioTrack create error buffer = " + mBuffSize);
        }

        if (null == mAudio) {
            mBuffSize = AudioTrack.getMinBufferSize(sampleRate, channelType,
                    AudioFormat.ENCODING_PCM_16BIT);
            try {
                mAudio = new AudioTrack(mStreamType, sampleRate, channelType,
                        AudioFormat.ENCODING_PCM_16BIT, mBuffSize,
                        AudioTrack.MODE_STREAM);
            } catch (IllegalArgumentException e) {
                Log.d(TAG, "AudioTrack create error buffer = " + mBuffSize);
            }
        }

        if (null != mAudio) {
            Log.d(TAG, "AudioTrack create ok buffer = " + mBuffSize);
        }
    }

    public void release() {
        synchronized (mAudioLock) {
            if (null != mAudio) {
                if (mAudio.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
                    mAudio.stop();
                }
                mAudio.release();
                mAudio = null;
            }
        }
    }

    public void pause() {
        if (null != mAudio
                && mAudio.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            mAudio.pause();
        }
    }

    /**
     * 只是重置标志
     */
    public void setStoping() {
        isStopping = true;
    }

    public void stop() {
        isStopping = true;
        if (null != mAudio
                && (mAudio.getPlayState() == AudioTrack.PLAYSTATE_PLAYING || mAudio
                        .getPlayState() == AudioTrack.PLAYSTATE_PAUSED)) {
            mAudio.flush();
            mAudio.stop();
        }
    }

    public void play(int length, byte[] dataOld) {
        if (null == mAudio) {
            Log.e(TAG, "play mAudio null");
            return;
        }
        if (mAudio.getState() != AudioTrack.STATE_INITIALIZED) {
            Log.e(TAG, "play mAudio STATE_INITIALIZED");
            return;
        }
        isStopping = false;

        synchronized (mAudioLock) {
            try {
                if (mAudio.getPlayState() != AudioTrack.PLAYSTATE_PLAYING
                        && !isStopping) {
                    Log.e(TAG, " play mAudio not PLAYSTATE_PLAYING");
                    mAudio.play();
                }

                int ret = mAudio.write(dataOld, 0, length);
                if (ret <= 0) {
                    Log.e(TAG, " mAudio write data ret =" + ret);
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    /**
     * 当前类型
     * 
     * @return
     */
    public int getSreamType() {
        if (null != mAudio) {
            return mAudio.getStreamType();
        }
        Log.e(TAG, "getSreamType mAudio null");
        return -1;
    }

    /**
     * 获取当前Buffer大小
     * 
     * @return
     */
    public int getBufferSize() {
        return mBuffSize;
    }

}

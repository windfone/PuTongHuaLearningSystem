package com.fifedu.record.media.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.SystemClock;
import android.util.Log;

/**
 * 专门用于通话录音的工具类
 * 
 * @author zhangyun
 * @date 2014-5-6
 */
public class PcmRecorder {
    private static final String TAG = "PcmRecorder";
    public static final int SAMPLE_RATE_8K = 8 * 1000; 
    public static final int SAMPLE_RATE_16K = 16 * 1000;
    public static final int SAMPLE_RATE_22050 = 22050;//使用22000无法支持
    public static final int SAMPLE_RATE_24K = 24 * 1000;
    public static final int SAMPLE_RATE_32K = 32 * 1000;
    public static final int SAMPLE_RATE_48K = 48 * 1000;
    
    private static final int DEF_CHANNEL = AudioFormat.CHANNEL_IN_MONO;
    private static final int DEF_FORMAT = AudioFormat.ENCODING_PCM_16BIT;
    
    private AudioRecord mRecorder = null;
    private Object mReadLock = new Object(); // 读取数据锁

    private IRecorderData mListener = null;
    private Thread mReadThread = null; // 读取数据的线程
    private boolean mIsRecording = false; // 外部控制停止
    private long mStartTime = 0; // 记录开始时间
    private byte[] mReadBuffer; // 录音数据数取的Buffer
    private int mReadData = 0;

    /**
     * 创建录音器的过程，可能会出现异常
     * @param listener
     * @param audiosource
     * @param sample
     * @throws IllegalStateException
     */
    public PcmRecorder(IRecorderData listener,int audiosource,int sample) 
            throws IllegalStateException {
        mListener = listener;  
        createAudioRecord(audiosource,sample,DEF_CHANNEL);
        if (mRecorder == null) {
            throw new IllegalStateException("create AudioRecord error");
        }
    }

    /**
     * 创建AudioRecord实例
     */
    private void createAudioRecord(int audiosource,int sample,int channel) {
        int readBuffer = 0;
        try {
            int minSize = AudioRecord.getMinBufferSize(sample,
                    DEF_CHANNEL, DEF_FORMAT);
            //readBuffer = sample * 8 / 100; // 8000采样时为640,16K时为1280（40ms数据）
            readBuffer = sample * 16 / 100; // 8000采样时为1280,16K时为2560（80ms数据）
            int devBuff = readBuffer * 16; // 设为16倍的读Buff，约20K；
            if (devBuff < minSize) {
                devBuff = minSize;
            }
            mRecorder = new AudioRecord(audiosource,
                    sample,channel, DEF_FORMAT, devBuff);

            Log.e(TAG, "createAudioRecord()  readBuffer=" + readBuffer
                    + " devBuff=" + devBuff + " minBuff=" + minSize
                    + " audioSource=" + audiosource + " channel=" + channel
                    + " sampleRate=" + sample);
            mReadBuffer = new byte[readBuffer];
            if (mRecorder.getState() != AudioRecord.STATE_INITIALIZED) {
                mRecorder.release();
                mRecorder = null;
                Log.e(TAG, "create AudioRecord error");
            }
        } catch (Exception e) {
        }
    }

    /**
     * 开始录音
     */
    public void startRecording() {
        if (null == mRecorder
                || mRecorder.getState() == AudioRecord.STATE_UNINITIALIZED) {
            Log.e(TAG, "startRecording STATE_UNINITIALIZED");
            return;
        }
        if (mRecorder.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
            Log.e(TAG, "startRecording RECORDSTATE_RECORDING");
            return;
        }

        try {
            mRecorder.startRecording();
            mIsRecording = true;
            mStartTime = SystemClock.elapsedRealtime();
            startReadThread();
        } catch (IllegalStateException e) {
            Log.e("startRecording", e.toString());
        }
    }

    /**
     * * 创建数据读取线程
     */
    private void startReadThread() {
        mReadThread = new Thread("RecordThread") {
            @Override
            public void run() {
                while (mIsRecording) {
                    synchronized (mReadLock) {
                        readRecordData();
                    }
                    SystemClock.sleep(5);
                }
            }

        };
        mReadThread.setPriority(Thread.MAX_PRIORITY);
        mReadThread.start();
    }

    /**
     * 读取录音数据
     * 
     * @return
     */

    private int readRecordData() {
        int readSize = 0;
        try {
            if (mRecorder != null) {
                if (mRecorder.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
                    Log.e(TAG, "readRecordData error state: "
                            + mRecorder.getRecordingState());
                    SystemClock.sleep(500);
                    return 0;
                }
                readSize = mRecorder.read(mReadBuffer, 0, mReadBuffer.length);
                if (readSize > 0 && mListener != null) {
                    mReadData += readSize;
                    mListener.onRecordData(mReadBuffer, readSize,
                            SystemClock.elapsedRealtime() - mStartTime);
                } else {
                    Log.e(TAG, "readRecordData size = " + readSize);
                }
                if (readSize < 0 && mListener != null){
                	mListener.onReadDataError();
                	mListener = null;
                }
            } else {
                Log.e(TAG, "readRecordData null");
            }
        } catch (Exception e) {
            Log.e(TAG, "readRecordData error" +e.toString());
        }
        return readSize;
    }

    /**
     * 停止录音
     */
    public void stopRecording() {
        mIsRecording = false;
        if (mRecorder != null) {
            Log.e(TAG, "stopRecording into");
            if (mRecorder.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
                synchronized (mReadLock) {
                    mRecorder.stop();
                }
            }
            Log.d(TAG, "stopRecording end");
        }
    }

    /**
     * 释放录音设备
     */
    public void release() {
        mIsRecording = false;
        if (null != mRecorder
                && mRecorder.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
            stopRecording();
        }
        Log.d(TAG, "release begin");
        synchronized (mReadLock) {
            if (mRecorder != null) {
                mRecorder.release();
                mRecorder = null;
            }
            Log.d(TAG, "release device.");
        }
        Log.d(TAG, "release end");
    }
    
    /**
     * 是否在录音中状态
     * @return
     */
    public boolean isRecording() {
        if (mRecorder != null) {
            return mRecorder.getRecordingState() 
                    == AudioRecord.RECORDSTATE_RECORDING;
        } else {
            return false;
        }
    }
    
    public int getAudioSource(){
        if (mRecorder != null) {
            return mRecorder.getAudioSource();
        }
        return  -1;
    }
    public int getSampleRate() {
        if (mRecorder != null) {
            return mRecorder.getSampleRate();
        }
        return SAMPLE_RATE_8K;
    }
    
    /** 获取已经读取数据长度 */
    public int getReadDataSize(){
        return mReadData;
    }
    
    public short getChannels(){
        if (null != mRecorder){
            return (short)mRecorder.getChannelCount();
        }
        return 0;
    }
}

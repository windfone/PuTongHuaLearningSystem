package com.fifedu.record.recinbox.bl.record;

import android.util.Log;

import com.fifedu.record.app.BaseThread;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 录音数据缓存处理线程
 * 
 * @author zhangyun
 * @date 2014-9-28
 */
public class RecorderDataThread extends BaseThread {
    private static final String TAG = "RecorderDataThread";
    private static final int LOG_COUNT = 20; // 20帧，相当于1秒输出一次日志
    private int mLogCount;
    private LinkedBlockingQueue<byte[]> mQueueAudioData;
    private IRecorderDataWriter mOutput;

    public interface IRecorderDataWriter{
        public int onWriteData(byte[] data);
    }
    public RecorderDataThread() {
        mQueueAudioData = new LinkedBlockingQueue<byte[]>();
    }

    public void setRecorderDataListener(IRecorderDataWriter listener) {
        mOutput = listener;
    }

    public boolean appendData(byte[] byData, int nLen) {
        byte[] data = new byte[nLen];
        System.arraycopy(byData, 0, data, 0, nLen);
        if (mQueueAudioData.size() > 5000) { // 1个数据块2K左右，如果大于5000，已经出问题了
            Log.e(TAG, "mQueueAudioData size large.");
            return false;
        }
        return mQueueAudioData.add(data);
    }

    public void reset() {
        mQueueAudioData.clear();
    }

    protected void threadProc() {
        this.setPriority(Thread.MAX_PRIORITY - 1); // 仅次于录音线程
        while (running) {
            byte[] byData = null;
            try {
                byData = mQueueAudioData.take();
            } catch (InterruptedException e) {
                Log.e(TAG, "InterruptedException");
            }
            if (null != byData) {
                int size = mQueueAudioData.size();
                if ((size > 0) && (size % 10 == 0)) {
                    Log.i(TAG, "take data size." + mQueueAudioData.size());
                }
                writeData(byData);
            }
        }
    }

    /**
     * 处理数据保存
     * 
     * @param data
     */
    private void writeData(byte[] data) {
        
        if (null != data && mOutput != null) { 
            mOutput.onWriteData(data);
        }else {
            Log.i(TAG, "writeData.null");
            return;
        }
        mLogCount++;
        if (mLogCount > LOG_COUNT) {
            mLogCount = 0;
        }
    }
}
package com.fifedu.record.recinbox.bl.record;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import com.fifedu.record.app.BaseThread;
import com.fifedu.record.media.player.IflyAudioPlayer;
import com.fifedu.record.media.record.IRecorderAdapter;
import com.fifedu.record.media.record.IRecorderData;
import com.fifedu.record.media.record.PcmRecorder;
import com.fifedu.record.media.speech.SpeechError;

import java.util.concurrent.LinkedBlockingQueue;


/**
 * 普通录音管理类
 * 
 * @author zhangyun
 * @date 2014-9-28
 * 
 */
public class RecorderManager implements IRecorderData, IRecorderManager, RecorderDataThread.IRecorderDataWriter {
    private static final String TAG = "RecorderManager";

    private static final int MSG_START_RECORD = 1; // 开始录音
    private static final int MSG_STOP_RECORD = 2; // 停止录音
    private static boolean PCM_LOG = false; //日志 , 
        
    private static RecorderManager mSelf = null;
    private MainThread mMainThread = null; // 主消息控制
    private PcmRecorder mRecord = null; // 录音器
    private RecorderDataThread mWriteThread; // 专门用于缓存数据的线程
    private RecordStatus mStatus = RecordStatus.IDLE; // 录音状态
    private RecordParams mRunParams = null; // 当前运行的录音参数
    private IRecorderListener mRunListener = null; //当前录音回调
    private PcmFileLog mPcmLog = null; //原始音频日志
    
    private enum RecordStatus {
        IDLE, // 空闲(常状)
        START, // 启动录音 (中间状态)
        RECORDING, // 录音中 (常状)
        FAILED, // 失败 (常状)
        STOP, // 停止录音 (中间状态)
    }

    private RecorderManager(Context ctx) {

    }

    /** 创建实例, 在APP启动时调用 */
    public static synchronized RecorderManager createInstance(Context ctx) {
        if (null == mSelf) {
            mSelf = new RecorderManager(ctx);
        }
        return mSelf;
    }

    public static RecorderManager getInstance() {
        return mSelf;
    }

    @Override
    public synchronized void startRecord(IRecorderListener listener,
            RecordParams params, IRecorderAdapter config) {
        checkInit();
        RecordMessage msg = new RecordMessage();
        msg.what = MSG_START_RECORD;
        msg.params = params;
        msg.listener = listener;
        msg.config = config;
        mMainThread.postMessage(msg); 
    }

    @Override
    public void stopRecord(IRecorderListener listener) {
        checkInit();
        RecordMessage msg = new RecordMessage();
        msg.what = MSG_STOP_RECORD;
        msg.listener = listener;
        mMainThread.postMessage(msg);
    }

    @Override
    public synchronized void destroy() {
        if (null != mMainThread) {
            mMainThread.clear();
            mMainThread.stop(0);
            mMainThread = null;
        }
        if (null != mRecord) {
            mRecord.release();
            mRecord = null;
        }
        if (null != mWriteThread) {
            mWriteThread.reset();
            mWriteThread.stop(0);
            mWriteThread = null;
        }
    }
    
    /**
     * 当读取读取错误后自动停止
     */
    @Override
    public void onReadDataError() {
    	checkInit();
    	
        RecordMessage msg = new RecordMessage();
        msg.what = MSG_STOP_RECORD;
        msg.listener = mRunListener;
        mMainThread.postMessage(msg);
        
        onSelfInterrupt(mRunListener);
    }

    @Override
    public void onRecordData(byte[] data, int length, long timeMillisecond) {        
        if (null != mWriteThread) {
            mWriteThread.appendData(data, length);
        }
    }

    @Override
    public boolean isRecording() {
        return (mStatus == RecordStatus.RECORDING);
    }

    /**
     * 初始化工作线程
     */
    private synchronized void checkInit() {
        if (null == mMainThread) {
            mMainThread = new MainThread();
            mMainThread.setName("RecorderManagerThread");
            mMainThread.setPriority(Thread.MAX_PRIORITY);
            mMainThread.start();

            mWriteThread = new RecorderDataThread();
            mWriteThread.setName("RecorderDataThread");
            mWriteThread.start();
            mWriteThread.setRecorderDataListener(this);
            Log.d(TAG, "checkInit ok.");
        }
    }

    /**
     * 主消息处理类
     */
    private class MainThread extends BaseThread {
        /** 重试再创建录音器的间隔时间 */
        private static final int RETRY_DELAY = 1000;
        private static final int MIN_DATA_SIZE = 1280; // 最小数据
        private static final int AUDIO_DATA_SIZE = 1280 * 20; // 800ms长度
        private LinkedBlockingQueue<RecordMessage> mQueueMessage = new LinkedBlockingQueue<RecordMessage>();
        private IflyAudioPlayer mAudio;

        @Override
        protected void threadProc() {
            while (running) {
                try {
                    RecordMessage m = mQueueMessage.take();
                    processMessage(m);
                } catch (InterruptedException e) {
                    Log.e(TAG, "InterruptedException");
                }
            }
        }

        public void clear() {
            mQueueMessage.clear();
        }

        public void postMessage(RecordMessage msg) {
            mQueueMessage.add(msg);
        }

        private void processMessage(RecordMessage msg) {
            switch (msg.what) {
            case MSG_START_RECORD:
                onStartRecord(msg);
                break;
            case MSG_STOP_RECORD:
                onStopRecord(msg);
                break;
            }
        }

        /** 开始录音 */
        private void onStartRecord(RecordMessage msg) {
            // 1.0 预处理
            RecordParams params = msg.params;
            IRecorderAdapter config = msg.config;
            if (null == params || null == config) {
                Log.e(TAG, "onStartRecord null params.");
                return;
            }
            Log.d(TAG,"onStartRecord begin");
            // 1.1 状态判断
            if (mStatus != RecordStatus.IDLE) {
                // 不处理 ，可能去电、接通2个事件会同时发生
                Log.d(TAG, "onStartRecord is run, status=" + mStatus);
                return;
            }
            mRunParams = params;
            mRunListener = msg.listener;
            // 2、判断有无备选通道,并启动主通道
            int audioSourceReserve = config
                    .getAudioSource(IRecorderAdapter.TYPE_RESERVE);
            int audioSource = config.getAudioSource(IRecorderAdapter.TYPE_DEF);
            boolean haveReserve = (audioSourceReserve >= 0);
            setStatus(RecordStatus.START);
            try {
                mRecord = new PcmRecorder(mSelf, audioSource,
                        config.getSampleRate());
            } catch (Exception e) {
                mRecord = null;
                if (e != null) {
                    Log.d(TAG, "onStartRecord  Exception:" + e.getMessage()
                            + " audiosource=" + audioSource);
                }
            }
            // 2.1开始录音
            if (null != mRecord) {
                mRecord.startRecording();
                if (!mRecord.isRecording()) {
                    mRecord.release();
                    mRecord = null;
                    Log.d(TAG, "onStartRecord  first failed.");
                }
            }
            // 2.2判断数据
            if (null != mRecord) {
                // 增加判断有没有数据到达
                if (haveReserve) {
                    Log.d(TAG, "onStartRecord waitRecordData--1--");
                    waitRecordData();
                    Log.d(TAG, "onStartRecord waitRecordData--2--");
                }
            }
            // 3、重试一次，同时判断是否要播放一下提示音
            if (null == mRecord) {
                // 使用第２个重试一次
                if (haveReserve) {
                    audioSource = audioSourceReserve;
                }
                // 播放一段提示音
                mAudio = IflyAudioPlayer.createAudioPlayer();
                mAudio.play(AUDIO_DATA_SIZE, new byte[AUDIO_DATA_SIZE]);
                try {
                    mRecord = new PcmRecorder(mSelf, audioSource,
                            config.getSampleRate());
                } catch (Exception e) {
                    mRecord = null;
                    Log.d(TAG, "onStartRecord retry Exception:" + e.getMessage()
                                    + " audiosource=" + audioSource);
                }
            }
            // 4.开始录音
            if (null != mRecord) {
                mRecord.startRecording();
                if (mRecord.isRecording()) {
                    //先回调打开文件然后切换状态
                    //后来修改为先切换状态,然后回调 2015-1-20,打开文件出错暂不处理
                    params.setAudioSource(mRecord.getAudioSource());
                    params.setSampleRate(mRecord.getSampleRate());
                    setStatus(RecordStatus.RECORDING);
                    onSelfStart(msg.listener, mRunParams);
                } else {
                    // 释放
                    mRecord.release();
                    mRecord = null;
                    setStatus(RecordStatus.FAILED);
                    Log.d(TAG, "onStartRecord retry start failed.");
                }
            } else {
                // 创建出错
                setStatus(RecordStatus.FAILED);
            }
            
            // 回调出错
            if (null == mRecord) {
                onSelfError(msg.listener, mRunParams, SpeechError.ERROR_CREATE_RECORD);
            }
            Log.d(TAG, "onStartRecord end.");
        }

        /** 停止录音,同时释放设备 */
        private void onStopRecord(RecordMessage msg) {
            Log.d(TAG, "onStopRecord into");
            if (mStatus == RecordStatus.IDLE) {
                onSelfStop(msg.listener, mRunParams);
                Log.d(TAG, "onStopRecord  not start.");
                return;
            }
            setStatus(RecordStatus.STOP);
            if (null != mRecord) {
                mRecord.stopRecording();
                mRecord.release();
                mRecord = null;
            }
            if (null != mAudio) {
                mAudio.release();
                mAudio = null;
            }
            // 直接回调界面
            onSelfStop(msg.listener, mRunParams);
            setStatus(RecordStatus.IDLE);
            Log.d(TAG, "onStopRecord end");
        }

        /**
         * 等待录音数据
         */
        private void waitRecordData() {
            for (int i = 0; i < RETRY_DELAY; i++) {
                SystemClock.sleep(100);
                i += 100;
                if (mRecord.getReadDataSize() > MIN_DATA_SIZE) {
                    break;
                }
            }

            if (mRecord.getReadDataSize() < MIN_DATA_SIZE) {
                mRecord.release();
                mRecord = null;
                Log.d(TAG, "onStartRecord waitRecordData empty.");
            }
        }
    }

    /**
     * 向外界面回调开始
     * 
     * @param listener
     */
    private boolean onSelfStart(IRecorderListener listener, RecordParams runParams) {       
        boolean ret = true;
        try {
            if (null != listener) {
                ret = listener.onStart(runParams);
                Log.d(TAG, "onSelfStart ret=" + ret + "  file:" + runParams.getFileName());
            }            
            if (PCM_LOG ){ //只有测试版本且pcm_log标志打开
                mPcmLog = new PcmFileLog();
                String logFile = runParams.getFileName() + ".mp3";
                mPcmLog.open(logFile, runParams.getSampleRate());
            }
        } catch (Exception e) {
            Log.d(TAG,e.toString());
            if (null != e) {
                Log.d(TAG, "listener.onStart:" + e.toString());
            }
        }
        return ret;
    }

    /**
     * 录音开启失败
     * 
     * @param listener
     * @param runParams
     * @param error
     */
    private void onSelfError(IRecorderListener listener,
            RecordParams runParams, int error) {
        try {
            if (null != listener){
                listener.onError(runParams, error);
            }
            if (null != mPcmLog){
                mPcmLog.close();
                mPcmLog = null;
            }
        } catch (Exception e) {
            if (null != e) {
                Log.d(TAG, "listener.onError:" + e.toString());
            }
        } 
    }

    /**
     * 向外界面回调结束
     * 
     * @param listener
     */
    private void onSelfStop(IRecorderListener listener, RecordParams runParams) {
        try {
            if (null != listener){
                listener.onFinished(runParams);
            }
            if (null != mPcmLog){
                mPcmLog.close();
                mPcmLog = null;
            }
        } catch (Exception e) {
            if (null != e) {
                Log.d(TAG, "listener.onFinished:" + e.toString());
            }
        }
    }

	/**
	 * 向外界面回调中断
	 * 
	 * @param listener
	 */
	private void onSelfInterrupt(IRecorderListener listener) {
		try {
			if (null != listener) {
				listener.onRecordInterrupt();
			}
		} catch (Exception e) {
            Log.d(TAG, e.toString());
		}
	}

    private void setStatus(RecordStatus status) {
        RecordStatus old = mStatus;
        mStatus = status;
        Log.d(TAG, " change status " + old + " ==> " + status);
    }

    /**
     * 内部消息类
     */
    private class RecordMessage {
        public int what;
        public RecordParams params;
        public IRecorderListener listener;
        public IRecorderAdapter config;
    }

    @Override
    public int onWriteData(byte[] data) {
        int ret = 0;
        if (mStatus != RecordStatus.RECORDING) {
            Log.e(TAG, "onWriteData not RECORDING:" + mStatus);
            return ret;
        }
        try {
            if (null != mRunListener) {
                ret = mRunListener.onRecordData(data);
            }
            if (null != mPcmLog){
                mPcmLog.writeData(data);
            }
        } catch (Exception e) {
            if (null != e) {
                Log.e(TAG, "onWriteData Exception," + e.getMessage());
            }
        }
        return ret;
    }
}

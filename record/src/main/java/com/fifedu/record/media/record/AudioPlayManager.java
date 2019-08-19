package com.fifedu.record.media.record;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.text.TextUtils;
import android.view.View;

/**
 * 音频播放管理类
 * @author HongYu
 *
 * 2015年7月14日
 */
public class AudioPlayManager {
    static final String TAG = "AudioPlayManager";

    private static AudioPlayManager _manager;

    private MediaPlayer _mediaPlayer;// 音频播放器

    private AudioplayInterface _audioPlayListen;// 音频状态

    private String _audioPath;// 音频播放地址

   // private String _audioName;// 音频名称，用于默认地址
    private int position = 0;// 音频播放偏移量

    private View _currentView;// 当前播放的View


    public synchronized static AudioPlayManager getManager(){
        if(_manager == null)
            _manager = new AudioPlayManager();
        return _manager;
    }
    private AudioPlayManager(){
        _mediaPlayer = new MediaPlayer();
    }

    public MediaPlayer getAudioPlay(){
        return _mediaPlayer;
    }
    /**
     * 外部设置播放器
     * 使用完要记得释放掉
     * @param mediaPlay
     */
    public void setAudioPlay(MediaPlayer mediaPlay){
        _mediaPlayer = mediaPlay;
    }
    /**
     * 重置
     */
    public void reset(){
        if(_mediaPlayer != null)
        {
            _mediaPlayer.stop();
            _mediaPlayer.reset();//added by huangguangle,Cause "mediaplayer went away with unhandled events"
            _mediaPlayer.release();
            _mediaPlayer = null;
        }
        _mediaPlayer=new MediaPlayer();
        position = 0;
    }
    /**
     * 返回当前播放状态
     * @return
     */
    public boolean isPlaying(){
        boolean isPlaying = false;
        try {
            isPlaying = _mediaPlayer.isPlaying();
        } catch (Exception e) {
            isPlaying = false;
        }
        return isPlaying;
    }
    public View getCurrentView(){
        return _currentView;
    }
    /**
     * 暂停播放
     */
    public void pause(){
        if(_mediaPlayer != null && _mediaPlayer.isPlaying())
        {
            position = _mediaPlayer.getCurrentPosition();
            _mediaPlayer.pause();
        }

    }
    /**
     * 恢复播放
     */
    public void resume(){
        if(_mediaPlayer != null && !_mediaPlayer.isPlaying())
        {
            _mediaPlayer.seekTo(position);
            _mediaPlayer.start();
        }
    }
    /**
     * 停止的时候释放录音机回调中断事件
     */
    public void stop(){
        if(_mediaPlayer != null )
        {
            if(_audioPlayListen != null && _mediaPlayer.isPlaying()){
                _mediaPlayer.stop();
                _audioPlayListen.interupt();
            }
            _mediaPlayer.reset();
            _mediaPlayer.release();
            _mediaPlayer = null;
        }
        if(_currentView != null)
            _currentView = null;
    }

    /**
     * 返回当前播放音频文件路径
     * @return
     */
    public String getCurrentAudioPath(){
        return _audioPath;
    }
    /**
     * 返回当前状态监听
     * @return
     */
    public AudioplayInterface getAudioPlayListen(){
        return _audioPlayListen;
    }

    private void start(String filePath){

        if(isPlaying() || _mediaPlayer == null) {
            reset();
        }
        _mediaPlayer.reset();
        try {
            _mediaPlayer.setDataSource(filePath);
            _mediaPlayer.prepare();
            _mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    _audioPlayListen.complete();
                }
            });
            _mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
            _audioPlayListen.onerror("audio file is error");
        }
    }

    /**
     * 播放来自assets的文件
     * @param fileDescriptor
     * @return duration
     */
    private int start(AssetFileDescriptor fileDescriptor){
        int duraion = -1;

        if(isPlaying() || _mediaPlayer == null) {
            reset();
        }
        _mediaPlayer.reset();
        try {
            _mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
            _mediaPlayer.prepare();
            _mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    _audioPlayListen.complete();
                }
            });
            _mediaPlayer.start();
            duraion=_mediaPlayer.getDuration()/1000;
        } catch (Exception e) {
            e.printStackTrace();
            _audioPlayListen.onerror("audio file is error");
            duraion= -1;
        }
        return duraion;
    }
    /**
     * 供外部调用使用接口
     * @param filePath
     *              文件路径
     * @param fileName
     *              文件名称
     * @param listen
     *              播放回调
     */
    public void startPlay(String filePath, String fileName, AudioplayInterface listen, View view){
        _currentView = view;
        _audioPlayListen = listen;
        if(!TextUtils.isEmpty(filePath))
        {
            start(filePath);
            return;
        }
        // 只有文件名使用默认的存储路径
//        String path = AppConstants.RECORD_PATH + fileName + AppConstants.AUDIO_FILE_SUFFIX;
//        start(path);
    }

    /**
     * 播放来自asssets的文件
     * @param fileDescriptor
     * @param listen
     * @param view
     * @return duration
     */
    public int startPlay(AssetFileDescriptor fileDescriptor,AudioplayInterface listen, View view){
        _currentView = view;
        _audioPlayListen = listen;
        if(fileDescriptor!=null)
        {
            return start(fileDescriptor);
        }else {
            return -1;
        }
        // 只有文件名使用默认的存储路径
//        String path = AppConstants.RECORD_PATH + fileName + AppConstants.AUDIO_FILE_SUFFIX;
//        start(path);
    }
}

package com.fifedu.record.media.record;

import android.media.MediaPlayer;
import android.util.Log;

import com.fifedu.record.common.ShowFrom;

/**
 * 电子书音频播放管理类（单例）
 *
 * @author yuyang2
 */
public class PlayAudioManager {

    private static PlayAudioManager playAudioManager = null;

    private MediaPlayer player;

    private int position;
    //播放入口
    private ShowFrom from=ShowFrom.Trend;

    //当前正在播放的音频
    private String audioPath;

    private PlayAudioManager() {

    }

    public synchronized static PlayAudioManager getInstance() {
        if (playAudioManager == null) {
            playAudioManager = new PlayAudioManager();
            playAudioManager.player = new MediaPlayer();
            playAudioManager.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
//                    EventBus.getDefault().post(new BaseEvents(EventsConfig.FINISH_PLAY));
                }
            });
        }


        return playAudioManager;
    }

    public MediaPlayer getAudioPlayer() {
        return player;
    }

    /**
     * 是否正在播放
     */
    public boolean isAudoPlaying(){
        return player.isPlaying();
    }

    /**
     * 获取正在播放的音频的名字
     */
    public String getAudioPath(){
        return this.audioPath;
    }



    /**
     * 播放声音
     */
    public void playAudio(String audioPath, ShowFrom from) {
       this.audioPath = audioPath;
       this.from=from;
        if (audioPath == null || "".equals(audioPath)) {
            Log.e("PlayAudioManager", "audioPath is null!!!");
            return;
        }
        position = 0;
        try {
            player.reset();// 重置为初始状态
            player.setDataSource(audioPath);
            player.prepare();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("播放", "播放异常");
//            EventBus.getDefault().post(new BaseEvents(EventsConfig.FINISH_PLAY));
            MediaPlayer temp = new MediaPlayer();
            player = temp;
            playAudioManager.player = temp;
            playAudioManager.player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
//                    EventBus.getDefault().post(new BaseEvents(EventsConfig.FINISH_PLAY));
                }
            });
            e.printStackTrace();
            player.start();
        }
        try {
            player.start();
            new upDateProgress().start();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Log.e("播放", "开始异常");
        }

    }

    /**
     * 暂停声音
     */
    public void pauseAudio() {
        if (player.isPlaying()) {
            position = player.getCurrentPosition();// 保存当前播放点
            player.pause();
        }
    }

    /**
     * 继续播放
     */
    public void continueAudio() {
        try {
            player.seekTo(position);
            player.start();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 停止播放
     */
    public void stopAudio() {
        try {
            if (player.isPlaying()) {
                player.stop();
                //	player.release();
                position = 0;
                /**
                 * 发送停止播放的广播
                 */
                try {
                    //因为更新进度是延迟200ms，所以要延迟停止
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
//                EventBus.getDefault().post(new BaseEvents(EventsConfig.FINISH_PLAY));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    /**
     * 重置声音播放管理
     */
    public void resetPlayAudioManager() {
        playAudioManager = null;
    }

    class upDateProgress extends Thread {
        @Override
        public void run() {
            int CurrentPosition = 0;// 设置默认进度条当前位置
            int total = player.getDuration();//
            while (player != null && CurrentPosition < total&&player.isPlaying()) {
                try {
                    Thread.sleep(100);
                    if (player != null) {
                        CurrentPosition = player.getCurrentPosition();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

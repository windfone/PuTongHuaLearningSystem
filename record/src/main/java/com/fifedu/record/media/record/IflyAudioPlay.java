package com.fifedu.record.media.record;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

/**
 * 单例模式创建的音频播放类
 *
 * @author chenxi5 2014-11-28
 */
public class IflyAudioPlay {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 单例
     */
    private static IflyAudioPlay instance = null;
    public MediaPlayer player;

    public static IflyAudioPlay getInstansce(Context context) {
        if (instance == null) {
            instance = new IflyAudioPlay(context);
            instance.player=new MediaPlayer();
        }
        return instance;
    }

    private IflyAudioPlay(Context context) {
        this.context = context;
    }

    /**
     * 媒体播放器
     */

    /**
     * 开始播放，路径在AudioConfig中初始化
     * @param name 播放的文件名称（包含后缀名）
     */
    public synchronized void startPlay(String path, String name)
    {

        File playfile=new File(path);
        if(!playfile.exists())
        {
            playfile.mkdirs();
        }

        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
             //   Toast.makeText(context,"播放错误！",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {

                if (player != null && player.isPlaying()) {
                    player.stop();
                    player.release();
                    player = new MediaPlayer();
                }
            }
        });
        try {
            if(player.isPlaying()) {
                player.reset();
            }
            player.setDataSource(playfile.getAbsolutePath()+"/"+name);
            player.prepare();
            player.start();

        } catch (IOException e) {
            player.stop();
            player.release();
            player=new MediaPlayer();
           // Toast.makeText(context, "没找到您要播放的文件", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }


    /**
     * 停止播放
     */
    public synchronized void stopPlay() {
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
            player=new MediaPlayer();
        }
    }

    /**
     * 暂停播放
     */
    public synchronized void pausePlay() {
        if (player != null) {
            if (player.isPlaying() == true) {
                player.pause();
            } else {
                player.start();
            }
        }
    }

}

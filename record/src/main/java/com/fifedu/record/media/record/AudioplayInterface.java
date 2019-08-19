package com.fifedu.record.media.record;
/**
 * 音频播放状态接口
 * @author HongYu
 *
 * 2015年7月14日
 */
public interface AudioplayInterface {
    void complete();// 音频下载完成
    void onerror(String str);// 音频下载错误
    void interupt();// 播放中断
}

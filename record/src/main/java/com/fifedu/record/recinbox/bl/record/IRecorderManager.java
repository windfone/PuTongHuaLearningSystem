package com.fifedu.record.recinbox.bl.record;

import com.fifedu.record.media.record.IRecorderAdapter;


/**
 * 录音接口
 * @author zhangyun
 * @date 2014-5-7
 */
public interface IRecorderManager { 
    /** 开始录音 */
    public void startRecord(IRecorderListener listener, RecordParams params
            , IRecorderAdapter config);
    /** 停止录音 */
    public void stopRecord(IRecorderListener listener);
    
    /** 是否在录音 中 */
    public boolean isRecording();
    /** 关闭 */
    public void destroy();
    
}

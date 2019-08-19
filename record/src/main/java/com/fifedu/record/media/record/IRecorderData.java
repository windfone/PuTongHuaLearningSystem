package com.fifedu.record.media.record;
/**
 * 录音数据回调接口
 * @author zhangyun
 *
 */
public interface IRecorderData { 
    void onRecordData(byte[] data, int length, long timeMillisecond);
    void onReadDataError();
}

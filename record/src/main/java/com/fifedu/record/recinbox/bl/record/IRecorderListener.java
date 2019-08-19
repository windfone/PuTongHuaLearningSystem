package com.fifedu.record.recinbox.bl.record;
/**
 * 录音管理调接口
 * @author zhangyun
 * @date 2014-11-25 增加录音中断回调
 */
public interface IRecorderListener { 
    public boolean onStart(RecordParams params);//录音开始
    public void onError(RecordParams params, int errorCode);//录音报错
    public int onRecordData(byte[] data);
    public void onRecordInterrupt();//录音中断
    public void onFinished(RecordParams params); //录音结束


}

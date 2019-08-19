package com.fifedu.record.recinbox.bl.record;

/**
 * 录音参数类
 * @author zhangyun
 * @date 2014-101-15
 */
public class RecordParams {  
    private char type; //录音类型
    private String fileId; //ID
    private String callNumber; //电话号码（仅通话录音使用）
    private int sampleRate; //当前采样率
    private int audioSource; //当前使用的音频源
    private String fileName; //当前的文件名
    private String launchType; //启动类型(通知栏还是主界面)
    
    public char getType() {
        return type;
    }
    public void setType(char type) {
        this.type = type;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getCallNumber() {
        return callNumber;
    }
    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
    public int getSampleRate() {
        return sampleRate;
    }
    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }
    public int getAudioSource() {
        return audioSource;
    }
    public void setAudioSource(int audioSource) {
        this.audioSource = audioSource;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getLaunchType() {
        return launchType;
    }
    public void setLaunchType(String launchType) {
        this.launchType = launchType;
    } 
}

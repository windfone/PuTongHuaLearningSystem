package com.hlxyedu.putonghualearningsystem.model.event;

public class HanZiEvent {

    public static final String CONTENT = "CONTENT";

    public static final String PRE = "PRE"; // 点击右边箭头，上一遍

    public static final String NEXT = "NEXT"; // 点击右边箭头，下一篇

    private String type;

    private String pinYin; // 拼音

    private String pinYinCN; // 汉字

    private String videoUrl; // 视频URL


    public HanZiEvent(String pinYin, String pinYinCN,String videoUrl){
        this.pinYin = pinYin;
        this.pinYinCN = pinYinCN;
        this.videoUrl = videoUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public String getPinYinCN() {
        return pinYinCN;
    }

    public void setPinYinCN(String pinYinCN) {
        this.pinYinCN = pinYinCN;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}

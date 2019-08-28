package com.hlxyedu.putonghualearningsystem.model.bean;

public class DetailVO {

    /**
     * imgUrl : resources/img/《“能吞能吐”的森林》.png
     * audioUrl : resources/mp4/“能吞能吐”的森林.mp3
     * audioLength : 0
     */

    private String[] txtData;
    private String audioUrl;
    private int audioLength;
    private String videoUrl;
    private String pinyin;
    private String pinYinCN;

    public String[] getTxtData() {
        return txtData;
    }

    public void setTxtData(String[] txtData) {
        this.txtData = txtData;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public int getAudioLength() {
        return audioLength;
    }

    public void setAudioLength(int audioLength) {
        this.audioLength = audioLength;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPinYin() {
        return pinyin;
    }

    public void setPinYin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getPinYinCN() {
        return pinYinCN;
    }

    public void setPinYinCN(String pinYinCN) {
        this.pinYinCN = pinYinCN;
    }

}

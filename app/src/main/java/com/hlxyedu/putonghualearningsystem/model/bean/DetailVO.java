package com.hlxyedu.putonghualearningsystem.model.bean;

import com.google.gson.annotations.SerializedName;

public class DetailVO {

    /**
     * imgUrl : resources/img/《“能吞能吐”的森林》.png
     * audioUrl : resources/mp4/“能吞能吐”的森林.mp3
     * audioLength : 0
     */

    private String[] txtData;
    private String conDetail;
    private String audioUrl;
    private int audioLength;
    private String videoUrl;
    private String pinyin;
    private String pinYinCN;
    private String wordImg;
    /**
     * conTitle : 普通话单词跟读教程词语表1 第001-100
     * audioLength : null
     */

    private String conTitle;


    public String[] getTxtData() {
        return txtData;
    }

    public void setTxtData(String[] txtData) {
        this.txtData = txtData;
    }

    public String getConDetail() {
        return conDetail;
    }

    public void setConDetail(String conDetail) {
        this.conDetail = conDetail;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
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

    public String getPinYinCN() {
        return pinYinCN;
    }

    public void setPinYinCN(String pinYinCN) {
        this.pinYinCN = pinYinCN;
    }

    public String getConTitle() {
        return conTitle;
    }

    public void setConTitle(String conTitle) {
        this.conTitle = conTitle;
    }

    public String getWordImg() {
        return wordImg;
    }

    public void setWordImg(String wordImg) {
        this.wordImg = wordImg;
    }
}

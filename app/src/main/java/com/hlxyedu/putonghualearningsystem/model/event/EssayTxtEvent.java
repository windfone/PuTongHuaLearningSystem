package com.hlxyedu.putonghualearningsystem.model.event;

public class EssayTxtEvent {

    public static final String CONTENT = "CONTENT";

    private String type;

    private String shortEssayTxt; // 短文文字内容

    private String totalTimeLength; // 音频总时长

    public EssayTxtEvent(String shortEssayTxt){
        this.shortEssayTxt = shortEssayTxt;
    }

    public EssayTxtEvent(String shortEssayTxt, String totalTimeLength){
        this.shortEssayTxt = shortEssayTxt;
        this.totalTimeLength = totalTimeLength;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortEssayTxt() {
        return shortEssayTxt;
    }

    public void setShortEssayTxt(String shortEssayTxt) {
        this.shortEssayTxt = shortEssayTxt;
    }

    public String getTotalTimeLength() {
        return totalTimeLength;
    }

    public void setTotalTimeLength(String totalTimeLength) {
        this.totalTimeLength = totalTimeLength;
    }
}

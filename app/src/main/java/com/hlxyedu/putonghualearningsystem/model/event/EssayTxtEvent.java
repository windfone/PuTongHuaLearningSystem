package com.hlxyedu.putonghualearningsystem.model.event;

public class EssayTxtEvent {

    public static final String CONTENT = "CONTENT";

    public static final String PRE = "PRE"; // 点击右边箭头，上一遍

    public static final String NEXT = "NEXT"; // 点击右边箭头，下一篇

    private String type;

    private String shortEssayTxt; // 短文文字内容

    private String totalTimeLength; // 音频总时长

    private String itemName; // item 列表内容，详情哪一篇的 名字

    public EssayTxtEvent(String shortEssayTxt){
        this.shortEssayTxt = shortEssayTxt;
    }

    public EssayTxtEvent(String shortEssayTxt, String itemName){
        this.shortEssayTxt = shortEssayTxt;
        this.itemName = itemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

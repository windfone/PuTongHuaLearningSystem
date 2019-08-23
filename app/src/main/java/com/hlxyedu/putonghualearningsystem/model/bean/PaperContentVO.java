package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PaperContentVO implements Parcelable {

    /**
     * sontitle : 第一题，请在4分钟时间内朗读单音节汉字100个
     * sonContent : ["哲","洽","许","腾","缓","昂","翻","容","选","闻","悦","围","波","信","铭","欧","测","敷","闰","巢","字","披","翁","辆","申","按","捐","旗","黑","咬","瞥","贺","失","广","晒","兵","卦","拔","君","仍","胸","撞","非","眸","葬","昭","览","脱","嫩","所","德","柳","砚","甩","豹","壤","凑","坑","绞","崔","我","初","蔽","匀","铝","枪","柴","搭","穷","董","池","款","杂","此","艘","粉","阔","您","镁","帘","械","搞","堤","捡","魂","躺","瘸","蛀","游","蠢","固","浓","钾","酸","莫","捧","队","耍","踹","儿"]
     * answerTime : 240
     */

    private String sontitle;
    private int answerTime;
    private List<String> sonContent;
    private int type;

    public String getSontitle() {
        return sontitle;
    }

    public void setSontitle(String sontitle) {
        this.sontitle = sontitle;
    }

    public int getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(int answerTime) {
        this.answerTime = answerTime;
    }

    public List<String> getSonContent() {
        return sonContent;
    }

    public void setSonContent(List<String> sonContent) {
        this.sonContent = sonContent;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sontitle);
        dest.writeInt(this.answerTime);
        dest.writeStringList(this.sonContent);
        dest.writeInt(this.type);
    }

    public PaperContentVO() {
    }

    protected PaperContentVO(Parcel in) {
        this.sontitle = in.readString();
        this.answerTime = in.readInt();
        this.sonContent = in.createStringArrayList();
        this.type = in.readInt();
    }

    public static final Creator<PaperContentVO> CREATOR = new Creator<PaperContentVO>() {
        @Override
        public PaperContentVO createFromParcel(Parcel source) {
            return new PaperContentVO(source);
        }

        @Override
        public PaperContentVO[] newArray(int size) {
            return new PaperContentVO[size];
        }
    };

}

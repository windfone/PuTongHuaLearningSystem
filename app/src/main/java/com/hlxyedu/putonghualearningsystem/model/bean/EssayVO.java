package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class EssayVO implements Parcelable {


    /**
     * name : “能吞能吐”的森林.mp3
     */

    private String name;

    private String id; // 自己添加的字段,播放音频库是根据id 播放，随机设置的只要不重复id 即可

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
    }

    public EssayVO() {
    }

    protected EssayVO(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<EssayVO> CREATOR = new Parcelable.Creator<EssayVO>() {
        @Override
        public EssayVO createFromParcel(Parcel source) {
            return new EssayVO(source);
        }

        @Override
        public EssayVO[] newArray(int size) {
            return new EssayVO[size];
        }
    };
}

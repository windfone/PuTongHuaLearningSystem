package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataVO implements Parcelable {



    /**
     * 短文跟读
     * name : “能吞能吐”的森林.mp3
     */

    private String name;

    private String id; // 自己添加的字段,播放音频库是根据id 播放，随机设置的只要不重复id 即可

    /**
     * title : 普通话拼音学习教程第一课   a   o   e
     */
    private String title;
    /**
     * 普通话拼音学习教程第一课   a   o   e
     * conId : 4
     * conTitle : aweq
     */

    private int conId;
    private String conTitle;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.conId);
        dest.writeString(this.conTitle);
    }

    public DataVO() {
    }

    protected DataVO(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.conId = in.readInt();
        this.conTitle = in.readString();
    }

    public static final Creator<DataVO> CREATOR = new Creator<DataVO>() {
        @Override
        public DataVO createFromParcel(Parcel source) {
            return new DataVO(source);
        }

        @Override
        public DataVO[] newArray(int size) {
            return new DataVO[size];
        }
    };

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConTitle() {
        return conTitle;
    }

    public void setConTitle(String conTitle) {
        this.conTitle = conTitle;
    }
}

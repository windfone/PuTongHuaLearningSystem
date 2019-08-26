package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoVO implements Parcelable {


    /**
     * teaId : 3
     * teaTitle : aas
     * browseNum : 132
     */

    private int teaId;
    private String teaTitle;
    private String teaVideoUrl;
    private String createTime;
    private int browseNum;

    public String getTeaVideoUrl() {
        return teaVideoUrl;
    }

    public void setTeaVideoUrl(String teaVideoUrl) {
        this.teaVideoUrl = teaVideoUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public static Creator<VideoVO> getCREATOR() {
        return CREATOR;
    }

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public String getTeaTitle() {
        return teaTitle;
    }

    public void setTeaTitle(String teaTitle) {
        this.teaTitle = teaTitle;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }

    public VideoVO() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.teaId);
        dest.writeString(this.teaTitle);
        dest.writeString(this.teaVideoUrl);
        dest.writeString(this.createTime);
        dest.writeInt(this.browseNum);
    }

    protected VideoVO(Parcel in) {
        this.teaId = in.readInt();
        this.teaTitle = in.readString();
        this.teaVideoUrl = in.readString();
        this.createTime = in.readString();
        this.browseNum = in.readInt();
    }

    public static final Creator<VideoVO> CREATOR = new Creator<VideoVO>() {
        @Override
        public VideoVO createFromParcel(Parcel source) {
            return new VideoVO(source);
        }

        @Override
        public VideoVO[] newArray(int size) {
            return new VideoVO[size];
        }
    };
}

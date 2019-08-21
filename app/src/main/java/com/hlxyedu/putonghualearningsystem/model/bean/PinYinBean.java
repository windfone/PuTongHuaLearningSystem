package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class PinYinBean implements Parcelable {

    private String title;

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
        dest.writeString(this.title);
    }

    public PinYinBean() {
    }

    protected PinYinBean(Parcel in) {
        this.title = in.readString();
    }

    public static final Parcelable.Creator<PinYinBean> CREATOR = new Parcelable.Creator<PinYinBean>() {
        @Override
        public PinYinBean createFromParcel(Parcel source) {
            return new PinYinBean(source);
        }

        @Override
        public PinYinBean[] newArray(int size) {
            return new PinYinBean[size];
        }
    };
}

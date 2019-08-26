package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TopTitleVO implements Parcelable {


    /**
     * exType : 1
     * typeId : 1
     */

    private String teType;
    private String exType;
    private int typeId;

    public String getTeType() {
        return teType;
    }

    public void setTeType(String teType) {
        this.teType = teType;
    }

    public static Creator<TopTitleVO> getCREATOR() {
        return CREATOR;
    }

    public String getExType() {
        return exType;
    }

    public void setExType(String exType) {
        this.exType = exType;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.exType);
        dest.writeInt(this.typeId);
    }

    public TopTitleVO() {
    }

    protected TopTitleVO(Parcel in) {
        this.exType = in.readString();
        this.typeId = in.readInt();
    }

    public static final Parcelable.Creator<TopTitleVO> CREATOR = new Parcelable.Creator<TopTitleVO>() {
        @Override
        public TopTitleVO createFromParcel(Parcel source) {
            return new TopTitleVO(source);
        }

        @Override
        public TopTitleVO[] newArray(int size) {
            return new TopTitleVO[size];
        }
    };
}

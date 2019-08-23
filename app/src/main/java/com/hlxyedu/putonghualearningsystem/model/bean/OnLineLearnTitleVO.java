package com.hlxyedu.putonghualearningsystem.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class OnLineLearnTitleVO implements Parcelable {


    /**
     * exType : 1
     * typeId : 1
     */

    private String exType;
    private int typeId;

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

    public OnLineLearnTitleVO() {
    }

    protected OnLineLearnTitleVO(Parcel in) {
        this.exType = in.readString();
        this.typeId = in.readInt();
    }

    public static final Parcelable.Creator<OnLineLearnTitleVO> CREATOR = new Parcelable.Creator<OnLineLearnTitleVO>() {
        @Override
        public OnLineLearnTitleVO createFromParcel(Parcel source) {
            return new OnLineLearnTitleVO(source);
        }

        @Override
        public OnLineLearnTitleVO[] newArray(int size) {
            return new OnLineLearnTitleVO[size];
        }
    };
}

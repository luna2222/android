package com.study.android.ex43_telephonyex;

import android.graphics.Bitmap;

public class AddressItem {
    String   Name;
    String TelNum;
    Bitmap resId;

    public AddressItem(String name, String telNum, Bitmap resId) {
        Name = name;
        TelNum = telNum;
        this.resId = resId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTelNum() {
        return TelNum;
    }

    public void setTelNum(String telNum) {
        TelNum = telNum;
    }

    public Bitmap getResId() {
        return resId;
    }

    public void setResId(Bitmap resId) {
        this.resId = resId;
    }
}

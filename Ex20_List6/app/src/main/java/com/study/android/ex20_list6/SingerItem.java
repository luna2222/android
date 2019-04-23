package com.study.android.ex20_list6;

public class SingerItem {
    private String name;
    private String telnum;
    private String resId;

    public  SingerItem(String name,String telnum, int reaId){
        this.name=name;
        this.telnum=telnum;
        this.resId=resId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTel(String age) {
        this.telnum = telnum;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }
}

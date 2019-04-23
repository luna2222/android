package com.study.android.ex18_list4;

public class SingerItem {
    private String name;
    private String age;
    private String resId;

    public  SingerItem(String name,String age, int reaId){
        this.name=name;
        this.age=age;
        this.resId=resId;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }
}

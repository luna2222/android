package com.study.android.ex45_sqlite2;


public class SingerItem {
    String Name;
    int Age;
    String Mobile;

    public SingerItem(String name, int age, String mobile) {
        Name = name;
        Age = age;
        Mobile = mobile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}

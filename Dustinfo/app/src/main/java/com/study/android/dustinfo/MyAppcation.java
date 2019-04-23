package com.study.android.dustinfo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyAppcation extends Application {

    @Override
    public  void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(configuration);

    }
}

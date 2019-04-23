package com.study.android.dustinfo.data;

import com.study.android.dustinfo.model.dust_material.FineDust;

import retrofit2.Callback;

public interface FineDustRepository {
    //나중에 코딩이 복잡하지 않도록 하기 위함
    boolean isAvailable();
    //retrofit2에서 제공하는 Callback
    void getFineDustData(Callback<FineDust> callback);

}

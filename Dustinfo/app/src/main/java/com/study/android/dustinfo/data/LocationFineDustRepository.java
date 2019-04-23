package com.study.android.dustinfo.data;

import com.study.android.dustinfo.model.dust_material.FineDust;
import com.study.android.dustinfo.util.FineDustUtil;

import retrofit2.Callback;

public class LocationFineDustRepository  implements FineDustRepository {
    private FineDustUtil mFineDustUtil;
    private double mLatitude;
    private double mLongitude;

    public LocationFineDustRepository(double lat, double lng) {
        this();
        this.mLatitude = lat;
        this.mLongitude = lng;
    }

    public LocationFineDustRepository() {
        mFineDustUtil = new FineDustUtil(); //초기화

    }

    @Override
    public boolean isAvailable() {
        if (mLatitude != 0.0 && mLongitude != 0.0){
            return true;
    }
        return false;
}
    @Override
    public void getFineDustData(Callback<FineDust> callback) {
        mFineDustUtil.getApi().getFineDust(mLatitude,mLongitude)
                .enqueue(callback);
    }
}

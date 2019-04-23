package com.study.android.dustinfo.finedust;

import com.study.android.dustinfo.model.dust_material.FineDust;

public class FineDustContract {
    public interface  View{
        void showFineDustResult(FineDust fineDust);
        void showLoadError(String message);
        void loadingStart();
        void loadingEnd();
        void reload(double lat, double lng);

    }
   public interface UserActionsListener{
        void  loadFineDustData();
    }
}

package com.study.android.ex53_admobex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.view.View;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //초기화
        String bannerid = getResources().getString(R.string.ad_unit_id_1);
        MobileAds.initialize(getApplicationContext(), bannerid);
        // 테스트 광고 부르기
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice("60B83A939CE358A5D13527CF86E2F709").build();// 테스트시 블럭 지정하곳 함
        mAdView.loadAd(adRequest);

        // 다음의 리스너는 구현하지 않아도 된다.
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d(TAG, "b:"+errorCode);
            }
            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }
            @Override
            public void onAdClosed() {
                // Code to be executed when when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }
    public  void hide(View v){
        if(mAdView.isEnabled())
            mAdView.setEnabled(false);
        if(mAdView.getVisibility()!=View.INVISIBLE)
          mAdView.setVisibility(View.INVISIBLE);

}
    public  void shoxAd(View v){
        if(mAdView.isEnabled())
            mAdView.setEnabled(true);
        if(mAdView.getVisibility()!=View.INVISIBLE)
            mAdView.setVisibility(View.INVISIBLE);
    }
}

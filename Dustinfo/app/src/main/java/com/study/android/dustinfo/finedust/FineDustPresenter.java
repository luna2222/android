package com.study.android.dustinfo.finedust;

import com.study.android.dustinfo.data.FineDustRepository;
import com.study.android.dustinfo.model.dust_material.FineDust;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FineDustPresenter implements FineDustContract.UserActionsListener {
    private  final FineDustRepository mRepository;
    private  final FineDustContract.View mView;

    public FineDustPresenter(FineDustRepository repository, FineDustContract.View view) {
        this.mRepository = repository;
        this.mView = view;
    }

    @Override
    public void loadFineDustData() {
        //데이터 제공이 가능하면
        if(mRepository.isAvailable()) {
            //로딩시작
            mView.loadingStart();
            //데에터 가져오기
            mRepository.getFineDustData(new Callback<FineDust>() {
                @Override
                public void onResponse(Call<FineDust> call, Response<FineDust> response) {
                    //데이터 표시하기
                    mView.showFineDustResult(response.body());
                   //로딩 끝
                    mView.loadingEnd();
                }

                @Override
                public void onFailure(Call<FineDust> call, Throwable t) {
                   //에러표시
                    mView.showLoadError(t.getLocalizedMessage());
                   //로딩끝
                    mView.loadingEnd();
                }
            });
        }
    }
}

package com.study.android.dustinfo.util;

import com.study.android.dustinfo.model.dust_material.FineDust;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface  FineDustApi {
    String  BASE_URL="http://api.weatherplanet.co.kr/";

    @Headers("appkey: 6b200e091d1a4d7e83fb9b4732809b33")
    //쿼리
    @GET("weather/dust?version=1")
    Call<FineDust> getFineDust(@Query("lat") double latitude,
                               @Query("lon") double longitude);
}

/*//https://www.youtube.com/watch?v=0ajAQTi44vA&list=PLxTmPHxRH3VWTd-8KB67Itegihkl4SVKe&index=49
//다른사람 appkey // 별도로 인증받아서 내것으로 사용하도록 해야함
public interface FineDustApi {
    String BASE_URL = "http://api.weatherplanet.co.kr/";

    @Headers("appkey: 6b200e091d1a4d7e83fb9b4732809b33")
    //@Headers("appkey: d39d6ed5-38b2-3205-b7f2-db02ea0ecf3a")

    @GET("weather/dust?versuib=1")
    Call<FineDust> getFineDust(@Query("lat") double latitude,
                               @Query("lon") double longitude);

}*/

package com.study.android.dustinfo.model.dust_material;

public class FineDust {
    private Weather weather;
    private Common common;
    private Result result;

    public  FineDust(Weather weather, Common common, Result result){
         this.weather= weather;
         this.common =common;
         this.result= result;
    }
    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

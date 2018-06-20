package com.example.kelvin.weather.Interface;

import com.example.kelvin.weather.Model.DailyForecast;
import com.example.kelvin.weather.Model.HourlyForecast;
import com.example.kelvin.weather.Model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kelvin on 6/17/2018.
 */

public interface ApiInterface {

    @GET("weather")
    Call<Weather> getWeatherInfo(@Query("q") String city, @Query("units") String unit, @Query("APPID") String appid);

    @GET("forecast")
    Call<List<HourlyForecast>> getHourlyInfo(@Query("mode") String format, @Query("q") String city, @Query("units") String unit, @Query("APPID") String appid);

    @GET("forecast/daily")
    Call<List<DailyForecast>> getDailyInfo(@Query("mode") String format, @Query("q") String city, @Query("units") String unit, @Query("APPID") String appid);

}

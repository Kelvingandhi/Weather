package com.example.kelvin.weather.Interface;

import com.example.kelvin.weather.Model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kelvin on 6/17/2018.
 */

public interface ApiInterface {

    @GET
    Call<Weather> getWeatherInfo(@);

}

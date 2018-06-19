package com.example.kelvin.weather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kelvin on 6/17/2018.
 */

public class OpenWeatherAPIWebClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String IMG_URL = "http://openweathermap.org/img/w/";

    public static Retrofit retrofit;

    public Retrofit getWeatherAPIClient(String city){

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();
        }

        return retrofit;
    }

    public Retrofit getForecastAPIClient(String city){

        //code

        return retrofit;
    }

}

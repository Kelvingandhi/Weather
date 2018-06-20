package com.example.kelvin.weather;

import android.util.Log;

import com.example.kelvin.weather.Interface.ApiInterface;
import com.example.kelvin.weather.Model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kelvin on 6/17/2018.
 */

public class OpenWeatherAPIWebClient {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private static String IMG_URL = "http://openweathermap.org/img/w/";

    private String APP_ID = "b06b9e2eeb05f80e044cb9d954d81f80";

    public static Retrofit retrofit;


    Weather w;

    public Retrofit getWeatherAPIClient(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();
        }

        return retrofit;
    }

    /*public Retrofit getForecastAPIClient(String city){

        //code

        return retrofit;
    }*/

    public String getWeatherData(String city){



        ApiInterface apiInterface = this.getWeatherAPIClient().create(ApiInterface.class);

        Call<Weather> call = apiInterface.getWeatherInfo(city, "imperial",APP_ID);

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                w  = response.body();

                Log.d("OpenweatherAPICLIENT", "onResponse: "+w.getStatus_code());

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });

        return "";
    }

}

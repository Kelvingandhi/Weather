package com.example.kelvin.weather.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kelvin on 1/27/2018.
 */

public class WeatherForecast {

    private final String TAG = "WeatherForcast";

    private List<HourlyForecast> hoursForecast = new ArrayList<HourlyForecast>();
    private List<DailyForecast> daysForecast = new ArrayList<DailyForecast>();

    public void addHourlyForecast(HourlyForecast hourlyForecast){
        hoursForecast.add(hourlyForecast);
        Log.d(TAG, "addHourlyForecast: "+hourlyForecast);
    }

    public List<HourlyForecast> getHourlyForecast(){
        return hoursForecast;
    }

    public void addDailyForecast(DailyForecast dailyForecast){
        daysForecast.add(dailyForecast);
        Log.d(TAG, "addHourlyForecast: "+dailyForecast);
    }

    public List<DailyForecast> getDailyForecast(){
        return daysForecast;
    }

}

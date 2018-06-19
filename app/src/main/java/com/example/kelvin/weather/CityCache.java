package com.example.kelvin.weather;

import android.content.Context;
import android.graphics.Typeface;

import com.example.kelvin.weather.Service.CityNameParserService;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Kelvin on 6/13/2018.
 */

public class CityCache {

    public static HashMap<String, String[] > citycache = new HashMap<>();

    public static String[] getCityArray(Context context, String cityList){

        String[] cityArray = citycache.get(cityList);

        if(cityArray == null){

            citycache.put(cityList, CityNameParserService.strary);
        }

        return cityArray;
    }

    public static void putCityArray(Context context, String cityList, String[] array){

            citycache.put(cityList,array);

    }

}

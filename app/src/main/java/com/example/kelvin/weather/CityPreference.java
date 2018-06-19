package com.example.kelvin.weather;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Kelvin on 6/9/2018.
 */

public class CityPreference {

    SharedPreferences cityPref;

    public CityPreference(Activity activity) {
        cityPref = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    // If the user has not chosen a city yet, return
    // some city as the default city

    String getCity(){
        return cityPref.getString("city", "Sydney, AU");
    }

    void setCity(String city){
        cityPref.edit().putString("city", city).commit();
    }

}

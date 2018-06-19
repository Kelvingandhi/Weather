package com.example.kelvin.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Kelvin on 6/9/2018.
 */

public class CitySelection extends Activity{


    public SharedPreferences firstTimePref;
    public SharedPreferences.Editor mEditor;
    public static final String PREF = "FIRST_TIME_PREF";

    private Boolean flag;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firstTimePref = getSharedPreferences(PREF,MODE_PRIVATE);
        flag = firstTimePref.getBoolean("isFirstTime", true);

        if(!flag){
            //Second time activity
            Intent intent= new Intent(CitySelection.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            //First time activity

            setContentView(R.layout.city_selection_activity);

            mEditor = firstTimePref.edit();
            mEditor.putBoolean("isFirstTime", false);
            mEditor.commit();

        }

    }
}

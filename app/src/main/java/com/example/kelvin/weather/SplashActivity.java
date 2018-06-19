package com.example.kelvin.weather;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.example.kelvin.weather.Service.CityNameParserService;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    public SharedPreferences firstTimePref;
    public SharedPreferences.Editor mEditor;
    public static final String PREF = "FIRST_TIME_PREF";

    private Boolean timerflag;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        firstTimePref = getSharedPreferences(PREF,MODE_PRIVATE);

        mEditor = firstTimePref.edit();
        mEditor.putString("isFirstTime","True");
        mEditor.commit();

    }
*/

    public static int SPLASH_TIME_OUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        firstTimePref = getSharedPreferences(PREF,MODE_PRIVATE);
        timerflag = firstTimePref.getBoolean("isFirstTime", true);

        if(!timerflag){
            //Second time
            SPLASH_TIME_OUT = 3000;
        }else{
            //First time
            SPLASH_TIME_OUT = 10000;
        }

        if(!isMyServiceRunning(CityNameParserService.class))
        {
            startService(new Intent(SplashActivity.this,CityNameParserService.class));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(SplashActivity.this,CitySelection.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}

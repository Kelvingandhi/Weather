package com.example.kelvin.weather.Service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.kelvin.weather.CityCache;
import com.example.kelvin.weather.Model.Cities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kelvin on 3/25/2018.
 */

public class CityNameParserService extends Service {

    private static final String TAG = "CityNameParserService";
    private Gson gson;


    public SharedPreferences cityPref;
    public SharedPreferences.Editor mEditor;
    public static final String PREF = "CITY_PREF";

    public static String[] strary;

    //public static List<Cities> cityList = new LinkedList<>();

    public static List<String> cityList = new LinkedList<>();

    Handler handler2 = new Handler();

    public int onStartCommand(Intent intent, int flags, int startId) {

        //Toast.makeText(CityNameParserService.this, "hi", Toast.LENGTH_LONG).show();

                handler2.post(new Runnable() {
                    @Override
                    public void run() {

                        Log.d(TAG, "run: inside");
                        //Toast.makeText(CityNameParserService.this, "inside run hi", Toast.LENGTH_LONG).show();

                        new CityNames().execute();


                    }
                });


        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class CityNames extends AsyncTask<Object, Object, List<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<String> doInBackground(Object... params) {



            /*String cities_json = null;

            try {
                InputStream is = getAssets().open("cities.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                cities_json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }

            return cities_json;*/

            try{
                InputStream is = getAssets().open("cities.json");

                JsonReader reader = new JsonReader(new InputStreamReader(is,"UTF-8"));

                //Read file in stream mode
                reader.beginArray();

                Gson gson = new GsonBuilder().create();

                while(reader.hasNext()){

                    Cities cityjson = gson.fromJson(reader, Cities.class);
                    /*Cities city = new Cities();

                    city.setCity_id((int) cityjson.getCity_id());
                    city.setCity_name(cityjson.getCity_name());
                    city.setLat((double)cityjson.getLat());
                    city.setLongi((double)cityjson.getLongi());
                    city.setCountry_code(cityjson.getCountry_code());*/

                    cityList.add(cityjson.getCity_name()+ ","+cityjson.getCountry_code());
                }

                reader.close();

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
            return cityList;
        }

        /*@Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ArrayList<Cities> cityList = new ArrayList<>();

            try {

                JSONArray m_jArry = new JSONArray(result);

                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject jo_inside = m_jArry.getJSONObject(i);
                    Cities cityObj = new Cities();
                    cityObj.setCity_id((int) jo_inside.getInt("cityid"));
                    cityObj.setLat((double) jo_inside.getDouble("latitude"));
                    cityObj.setLongi((double) jo_inside.getDouble("longitude"));
                    cityObj.setCity_name(jo_inside.getString("name"));
                    cityObj.setCountry_code(jo_inside.getString("countrycode"));


                    Log.d(TAG, "onPostExecute: "+cityObj.getCity_name()+cityObj.getCountry_code());

                    //Add your values in your `ArrayList` as below:
                    cityList.add(cityObj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }*/

        @Override
        protected void onPostExecute(List<String> result) {
            super.onPostExecute(result);

            Toast.makeText(CityNameParserService.this, result.get(2), Toast.LENGTH_SHORT).show();

            /*Log.d(TAG, "onPostExecute: "+strary[2]);

            if(CityCache.getCityArray(getApplicationContext(), "NewCityArray") == null){
                Log.d(TAG, "onPostExecute: Cache null");
                CityCache.putCityArray(getApplicationContext(), "NewCityArray",strary);
                Log.d(TAG, "onPostExecute: City array cached");
            }*/


            cityPref = getSharedPreferences(PREF,MODE_PRIVATE);
            mEditor = cityPref.edit();


            if(cityPref.getString(PREF, null) == null){

                Object[] obj = result.toArray();

                strary = Arrays.copyOf(obj,obj.length,String[].class);

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < strary.length; i++) {
                    sb.append(strary[i]).append("~");
                }
                mEditor.putString(PREF, sb.toString());
                mEditor.commit();

                Log.d(TAG, "onPostExecute: First time city list stored");
            }
            else {
                Log.d(TAG, "onPostExecute: City list already stored");
            }

        }
    }

}

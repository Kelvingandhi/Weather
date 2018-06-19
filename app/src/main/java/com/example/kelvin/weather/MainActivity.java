package com.example.kelvin.weather;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.kelvin.weather.Model.Cities;
import com.example.kelvin.weather.Service.CityNameParserService;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MaterialSearchView.SearchViewListener{


    private final String TAG = "MainActivity";

    private ImageButton current_loc_btn;
    private ImageButton search_btn;
    private ImageButton setting_btn;
    private MaterialSearchView searchView;
    Toolbar toolbar;

    public SharedPreferences cityPref;
    public SharedPreferences.Editor mEditor;
    public static final String PREF = "CITY_PREF";

    private String[] citySuggestions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        cityPref = getSharedPreferences(PREF,MODE_PRIVATE);
        mEditor = cityPref.edit();

        //String city_string = cityPref.getString(PREF, null);
        //Gson gson = new Gson();

        String city_string = cityPref.getString(PREF, null);

        if(city_string != null) {
            citySuggestions = city_string.split("~");
        }
        else{
            citySuggestions = new String[]{"New Delhi, IN","New York City, US","Jersey City, US","Los Angeles, US",
                    "Toronto, CA","Melbourne, AU","Dubai, AE","Beijing, CN","Mumbai, IN"};
        }




        Log.d(TAG, "onCreate: "+citySuggestions[2]);

        initializeVariables();

        searchView = (MaterialSearchView) findViewById(R.id.material_search_view);
        searchView.setCursorDrawable(R.drawable.ic_action_action_search);

        ArrayList<String> a = new ArrayList<>();
        a.add("abc");
        a.add("xyz");
        a.add("def");
        a.add("mno");

        //Object[] obj = CityNameParserService.cityList.toArray();

        //String[] strary = Arrays.copyOf(obj,obj.length,String[].class);

        //Log.d(TAG, "onCreate: "+CityCache.getCityArray(MainActivity.this,"NewCityArray"));

        searchView.setEllipsize(true);
        searchView.setSuggestions(citySuggestions);
        //searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

    }

    private void initializeVariables() {

        current_loc_btn = (ImageButton) findViewById(R.id.current_location_btn);
        search_btn = (ImageButton) findViewById(R.id.search_location_btn);
        setting_btn = (ImageButton) findViewById(R.id.settings_btn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /*getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
*/


        return true;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.search_location_btn:

                //new CityNames().execute();

                Log.d(TAG, "onClick: Search button");
                //searchView.setVisibility(View.VISIBLE);
                onSearchViewShown();

            case R.id.current_location_btn:
                Log.d(TAG, "onClick: Current Location Button");

            case R.id.settings_btn:
                break;

        };

    }


    @Override
    public void onSearchViewShown() {

        Log.d(TAG, "onSearchViewShown: ");
        
        searchView.animate();
        searchView.showSearch();
    }

    @Override
    public void onSearchViewClosed() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        if(searchView.isSearchOpen()){
            searchView.closeSearch();
        }else{
            super.onBackPressed();
        }

    }


}

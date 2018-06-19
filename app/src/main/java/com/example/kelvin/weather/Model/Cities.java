package com.example.kelvin.weather.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kelvin on 3/24/2018.
 */

public class Cities implements Serializable{

    @SerializedName("cityid")
    private long city_id;

    @SerializedName("name")
    private String city_name;

    @SerializedName("latitude")
    private double lat;

    @SerializedName("longitude")
    private double longi;

    @SerializedName("countrycode")
    private String country_code;

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
}

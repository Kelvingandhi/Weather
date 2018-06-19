package com.example.kelvin.weather.Model;

/**
 * Created by Kelvin on 1/27/2018.
 */

public class DailyForecast {

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public class DailyForecastTemp {

        private float minTemp;
        private float maxTemp;

        public float getMinTemp() {
            return minTemp;
        }

        public void setMinTemp(float minTemp) {
            this.minTemp = minTemp;
        }

        public float getMaxTemp() {
            return maxTemp;
        }

        public void setMaxTemp(float maxTemp) {
            this.maxTemp = maxTemp;
        }
    }

}

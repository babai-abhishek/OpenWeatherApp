
package com.example.abhishek.weatherforecast.models.db;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherDBModel {


    private String cod;

    private java.util.List<WeatherListDBModel> weatherListDBModel = new ArrayList<WeatherListDBModel>();

    private CityDBModel cityDBModel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherDBModel() {
    }

    /**
     * 
     * @param cod
     * @param weatherListDBModel
     * @param city
     */
    public WeatherDBModel(String cod, java.util.List<WeatherListDBModel> weatherListDBModel, CityDBModel cityDBModel) {
        super();
        this.cod = cod;
        this.weatherListDBModel = weatherListDBModel;
        this.cityDBModel = cityDBModel;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public java.util.List<WeatherListDBModel> getWeatherListDBModel() {
        return weatherListDBModel;
    }

    public void setWeatherListDBModel(java.util.List<WeatherListDBModel> weatherListDBModel) {
        this.weatherListDBModel = weatherListDBModel;
    }

    public CityDBModel getCityDBModel() {
        return cityDBModel;
    }

    public void setCityDBModel(CityDBModel cityDBModel) {
        this.cityDBModel = cityDBModel;
    }

}

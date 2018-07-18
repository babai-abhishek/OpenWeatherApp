
package com.example.abhishek.weatherforecast.model.api;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherApiModel {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("list")
    @Expose
    private java.util.List<WeatherListApiModel> weatherListApiModel = new ArrayList<WeatherListApiModel>();
    @SerializedName("city")
    @Expose
    private CityApiModel cityApiModel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherApiModel() {
    }

    /**
     * 
     * @param cod
     * @param list
     * @param city
     */
    public WeatherApiModel(String cod, java.util.List<WeatherListApiModel> weatherListApiModel, CityApiModel cityApiModel) {
        super();
        this.cod = cod;
        this.weatherListApiModel = weatherListApiModel;
        this.cityApiModel = cityApiModel;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public java.util.List<WeatherListApiModel> getWeatherListApiModel() {
        return weatherListApiModel;
    }

    public void setWeatherListApiModel(java.util.List<WeatherListApiModel> weatherListApiModel) {
        this.weatherListApiModel = weatherListApiModel;
    }

    public CityApiModel getCityApiModel() {
        return cityApiModel;
    }

    public void setCityApiModel(CityApiModel cityApiModel) {
        this.cityApiModel = cityApiModel;
    }

}

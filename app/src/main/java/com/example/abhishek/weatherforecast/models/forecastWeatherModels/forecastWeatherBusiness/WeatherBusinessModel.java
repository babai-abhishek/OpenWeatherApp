
package com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness;

import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;

import java.util.ArrayList;

public class WeatherBusinessModel {

    private String cod;
    private java.util.List<WeatherListBusinessModel> weatherListBusinessModel = new ArrayList<WeatherListBusinessModel>();
    private CityBusinessModel cityBusinessModel;


    public WeatherBusinessModel() {
    }

    public WeatherBusinessModel(WeatherApiModel weatherApiModel){
        this.cod = weatherApiModel.getCod();
        this.cityBusinessModel = new CityBusinessModel(weatherApiModel.getCityApiModel());
        for(WeatherListApiModel weatherListApiModel: weatherApiModel.getWeatherListApiModel()){
            this.weatherListBusinessModel.add(new WeatherListBusinessModel(weatherListApiModel));
        }
    }


    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public java.util.List<WeatherListBusinessModel> getWeatherListBusinessModel() {
        return weatherListBusinessModel;
    }

    public void setWeatherListBusinessModel(java.util.List<WeatherListBusinessModel> weatherListBusinessModel) {
        this.weatherListBusinessModel = weatherListBusinessModel;
    }

    public CityBusinessModel getCityBusinessModel() {
        return cityBusinessModel;
    }

    public void setCityBusinessModel(CityBusinessModel cityBusinessModel) {
        this.cityBusinessModel = cityBusinessModel;
    }

}

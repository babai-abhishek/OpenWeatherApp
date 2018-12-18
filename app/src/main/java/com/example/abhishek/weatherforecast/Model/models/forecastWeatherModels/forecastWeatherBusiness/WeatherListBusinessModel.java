
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;

import com.example.abhishek.weatherforecast.IWeatherDetails;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherInfoApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherRoomDBEntity.ForecastWeather;

import java.util.ArrayList;

public class WeatherListBusinessModel implements IWeatherDetails {

    private long dt;
    private MainBusinessModel mainBusinessModel;
    private java.util.List<WeatherInfoBusinessModel> weatherInfoBusinessModel = new ArrayList<WeatherInfoBusinessModel>();


    /**
     * No args constructor for use in serialization
     *
     */
    public WeatherListBusinessModel() {
    }

    /**
     *
     * @param cloudsBusinessModel
     * @param dt
     * @param windBusinessModel
     * @param sysBusinessModel
     * @param weatherInfoBusinessModel
     * @param mainBusinessModel
     */
    public WeatherListBusinessModel(long dt, MainBusinessModel mainBusinessModel, java.util.List<WeatherInfoBusinessModel> weatherInfoBusinessModel, CloudsBusinessModel cloudsBusinessModel, WindBusinessModel windBusinessModel, SysBusinessModel sysBusinessModel) {
        this.dt = dt;
        this.mainBusinessModel = mainBusinessModel;
        this.weatherInfoBusinessModel = weatherInfoBusinessModel;

    }

    public WeatherListBusinessModel(WeatherListApiModel weatherListApiModel) {
        this.dt = weatherListApiModel.getDt();
        this.mainBusinessModel = new MainBusinessModel(weatherListApiModel.getMainApiModel());
        for(WeatherInfoApiModel weatherInfoApiModel: weatherListApiModel.getWeatherInfoApiModel()){
            this.weatherInfoBusinessModel.add(new WeatherInfoBusinessModel(weatherInfoApiModel));
        }

    }

    public WeatherListBusinessModel(ForecastWeather forecastWeather) {
        this.dt = (long) forecastWeather.date;
        this.mainBusinessModel = new MainBusinessModel(forecastWeather.minTemp, forecastWeather.maxTemp);
        this.weatherInfoBusinessModel.add(new WeatherInfoBusinessModel(forecastWeather.weatherId, forecastWeather.weatherDescription, forecastWeather.iconId));

    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public MainBusinessModel getMainBusinessModel() {
        return mainBusinessModel;
    }

    public void setMainBusinessModel(MainBusinessModel mainBusinessModel) {
        this.mainBusinessModel = mainBusinessModel;
    }

    public java.util.List<WeatherInfoBusinessModel> getWeatherInfoBusinessModel() {
        return weatherInfoBusinessModel;
    }

    public void setWeatherInfoBusinessModel(java.util.List<WeatherInfoBusinessModel> weatherInfoBusinessModel) {
        this.weatherInfoBusinessModel = weatherInfoBusinessModel;
    }

}

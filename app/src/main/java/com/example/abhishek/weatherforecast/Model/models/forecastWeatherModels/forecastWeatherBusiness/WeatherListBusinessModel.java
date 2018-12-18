
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;

import com.example.abhishek.weatherforecast.IWeatherDetails;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherInfoApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;

import java.util.ArrayList;

public class WeatherListBusinessModel implements IWeatherDetails {

    private long dt;
    private MainBusinessModel mainBusinessModel;
    private java.util.List<WeatherInfoBusinessModel> weatherInfoBusinessModel = new ArrayList<WeatherInfoBusinessModel>();
    private CloudsBusinessModel cloudsBusinessModel;
    private WindBusinessModel windBusinessModel;
    private SysBusinessModel sysBusinessModel;

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
        this.cloudsBusinessModel = cloudsBusinessModel;
        this.windBusinessModel = windBusinessModel;
        this.sysBusinessModel = sysBusinessModel;
    }

    public WeatherListBusinessModel(WeatherListApiModel weatherListApiModel) {
        this.dt = weatherListApiModel.getDt();
        this.mainBusinessModel = new MainBusinessModel(weatherListApiModel.getMainApiModel());
        for(WeatherInfoApiModel weatherInfoApiModel: weatherListApiModel.getWeatherInfoApiModel()){
            this.weatherInfoBusinessModel.add(new WeatherInfoBusinessModel(weatherInfoApiModel));
        }
        this.cloudsBusinessModel = new CloudsBusinessModel(weatherListApiModel.getCloudsApiModel());
        this.windBusinessModel = new WindBusinessModel(weatherListApiModel.getWindApiModel());
        this.sysBusinessModel = new SysBusinessModel(weatherListApiModel.getSysApiModel());
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

    public CloudsBusinessModel getCloudsBusinessModel() {
        return cloudsBusinessModel;
    }

    public void setCloudsBusinessModel(CloudsBusinessModel cloudsBusinessModel) {
        this.cloudsBusinessModel = cloudsBusinessModel;
    }

    public WindBusinessModel getWindBusinessModel() {
        return windBusinessModel;
    }

    public void setWindBusinessModel(WindBusinessModel windBusinessModel) {
        this.windBusinessModel = windBusinessModel;
    }

    public SysBusinessModel getSysBusinessModel() {
        return sysBusinessModel;
    }

    public void setSysBusinessModel(SysBusinessModel sysBusinessModel) {
        this.sysBusinessModel = sysBusinessModel;
    }

}

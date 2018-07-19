
package com.example.abhishek.weatherforecast.models.db;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherListDBModel {

    private int dt;
    private MainDBModel mainDBModel;
    private java.util.List<WeatherInfoDBModel> weatherInfoDBModel = new ArrayList<WeatherInfoDBModel>();

    private CloudsDBModel cloudsDBModel;

    private WindDBModel windDBModel;

    private SysDBModel sysDBModel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherListDBModel() {
    }

    /**
     * 
     * @param clouds
     * @param dt
     * @param wind
     * @param sys
     * @param weather
     * @param main
     */
    public WeatherListDBModel(int dt, MainDBModel mainDBModel, java.util.List<WeatherInfoDBModel> weatherInfoDBModel, CloudsDBModel cloudsDBModel, WindDBModel windDBModel, SysDBModel sysDBModel) {
        super();
        this.dt = dt;
        this.mainDBModel = mainDBModel;
        this.weatherInfoDBModel = weatherInfoDBModel;
        this.cloudsDBModel = cloudsDBModel;
        this.windDBModel = windDBModel;
        this.sysDBModel = sysDBModel;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public MainDBModel getMainDBModel() {
        return mainDBModel;
    }

    public void setMainDBModel(MainDBModel mainDBModel) {
        this.mainDBModel = mainDBModel;
    }

    public java.util.List<WeatherInfoDBModel> getWeatherInfoDBModel() {
        return weatherInfoDBModel;
    }

    public void setWeatherInfoDBModel(java.util.List<WeatherInfoDBModel> weatherInfoDBModel) {
        this.weatherInfoDBModel = weatherInfoDBModel;
    }

    public CloudsDBModel getCloudsDBModel() {
        return cloudsDBModel;
    }

    public void setCloudsDBModel(CloudsDBModel cloudsDBModel) {
        this.cloudsDBModel = cloudsDBModel;
    }

    public WindDBModel getWindDBModel() {
        return windDBModel;
    }

    public void setWindDBModel(WindDBModel windDBModel) {
        this.windDBModel = windDBModel;
    }

    public SysDBModel getSysDBModel() {
        return sysDBModel;
    }

    public void setSysDBModel(SysDBModel sysDBModel) {
        this.sysDBModel = sysDBModel;
    }

}

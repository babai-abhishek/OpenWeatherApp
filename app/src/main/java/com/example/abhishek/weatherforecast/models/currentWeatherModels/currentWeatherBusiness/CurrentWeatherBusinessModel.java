
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness;

import com.example.abhishek.weatherforecast.IWeatherDetails;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherInfoApiModel;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherBusinessModel implements IWeatherDetails{

    private CurrentWeatherCoordBusinessModel currentWeatherCoordBusinessModel;
    private List<CurrentWeatherInfoBusinessModel> currentWeatherInfoBusinessModel = new ArrayList<CurrentWeatherInfoBusinessModel>();
    private CurrentWeatherMainBusinessModel currentWeatherMainBusinessModel;
    private CurrentWeatherWindBusinessModel currentWeatherWindBusinessModel;
    private CurrentWeatherCloudsBusinessModel currentWeatherCloudsBusinessModel;
    private int dt;
    private CurrentWeatherSysBusinessModel currentWeatherSysBusinessModel;
    private int id;
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrentWeatherBusinessModel() {
    }

    /**
     * 
     * @param id
     * @param dt
     * @param currentWeatherCloudsBusinessModel
     * @param currentWeatherCoordBusinessModel
     * @param currentWeatherWindBusinessModel
     * @param currentWeatherSysBusinessModel
     * @param name
     * @param currentWeatherInfoBusinessModel
     * @param currentWeatherMainBusinessModel
     */
    public CurrentWeatherBusinessModel(CurrentWeatherCoordBusinessModel currentWeatherCoordBusinessModel, List<CurrentWeatherInfoBusinessModel> currentWeatherInfoBusinessModel, CurrentWeatherMainBusinessModel currentWeatherMainBusinessModel, CurrentWeatherWindBusinessModel currentWeatherWindBusinessModel, CurrentWeatherCloudsBusinessModel currentWeatherCloudsBusinessModel, int dt, CurrentWeatherSysBusinessModel currentWeatherSysBusinessModel, int id, String name) {
        this.currentWeatherCoordBusinessModel = currentWeatherCoordBusinessModel;
        this.currentWeatherInfoBusinessModel = currentWeatherInfoBusinessModel;
        this.currentWeatherMainBusinessModel = currentWeatherMainBusinessModel;
        this.currentWeatherWindBusinessModel = currentWeatherWindBusinessModel;
        this.currentWeatherCloudsBusinessModel = currentWeatherCloudsBusinessModel;
        this.dt = dt;
        this.currentWeatherSysBusinessModel = currentWeatherSysBusinessModel;
        this.id = id;
        this.name = name;
    }

    public CurrentWeatherBusinessModel(CurrentWeatherApiModel currentWeather) {
        this.dt = currentWeather.getDt();
        this.id = currentWeather.getId();
        this.name = currentWeather.getName();
        this.currentWeatherSysBusinessModel = new CurrentWeatherSysBusinessModel(currentWeather.getCurrentWeatherSysApiModel().getCountry());
        this.currentWeatherCoordBusinessModel = new CurrentWeatherCoordBusinessModel(currentWeather.getCurrentWeatherCoordApiModel());
        this.currentWeatherMainBusinessModel = new CurrentWeatherMainBusinessModel(currentWeather.getCurrentWeatherMainApiModel());
        this.currentWeatherWindBusinessModel = new CurrentWeatherWindBusinessModel(currentWeather.getCurrentWeatherWindApiModel());
        this.currentWeatherCloudsBusinessModel = new CurrentWeatherCloudsBusinessModel(currentWeather.getCurrentWeatherCloudsApiModel());
        for(CurrentWeatherInfoApiModel current: currentWeather.getCurrentWeatherInfoApiModel()){
            this.currentWeatherInfoBusinessModel.add(new CurrentWeatherInfoBusinessModel(current));
        }
    }

    public CurrentWeatherCoordBusinessModel getCurrentWeatherCoordBusinessModel() {
        return currentWeatherCoordBusinessModel;
    }

    public void setCurrentWeatherCoordBusinessModel(CurrentWeatherCoordBusinessModel currentWeatherCoordBusinessModel) {
        this.currentWeatherCoordBusinessModel = currentWeatherCoordBusinessModel;
    }

    public List<CurrentWeatherInfoBusinessModel> getCurrentWeatherInfoBusinessModel() {
        return currentWeatherInfoBusinessModel;
    }

    public void setCurrentWeatherInfoBusinessModel(List<CurrentWeatherInfoBusinessModel> currentWeatherInfoBusinessModel) {
        this.currentWeatherInfoBusinessModel = currentWeatherInfoBusinessModel;
    }

    public CurrentWeatherMainBusinessModel getCurrentWeatherMainBusinessModel() {
        return currentWeatherMainBusinessModel;
    }

    public void setCurrentWeatherMainBusinessModel(CurrentWeatherMainBusinessModel currentWeatherMainBusinessModel) {
        this.currentWeatherMainBusinessModel = currentWeatherMainBusinessModel;
    }

    public CurrentWeatherWindBusinessModel getCurrentWeatherWindBusinessModel() {
        return currentWeatherWindBusinessModel;
    }

    public void setCurrentWeatherWindBusinessModel(CurrentWeatherWindBusinessModel currentWeatherWindBusinessModel) {
        this.currentWeatherWindBusinessModel = currentWeatherWindBusinessModel;
    }

    public CurrentWeatherCloudsBusinessModel getCurrentWeatherCloudsBusinessModel() {
        return currentWeatherCloudsBusinessModel;
    }

    public void setCurrentWeatherCloudsBusinessModel(CurrentWeatherCloudsBusinessModel currentWeatherCloudsBusinessModel) {
        this.currentWeatherCloudsBusinessModel = currentWeatherCloudsBusinessModel;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public CurrentWeatherSysBusinessModel getCurrentWeatherSysBusinessModel() {
        return currentWeatherSysBusinessModel;
    }

    public void setCurrentWeatherSysBusinessModel(CurrentWeatherSysBusinessModel currentWeatherSysBusinessModel) {
        this.currentWeatherSysBusinessModel = currentWeatherSysBusinessModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

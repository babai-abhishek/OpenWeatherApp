
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness;

import com.example.abhishek.weatherforecast.IWeatherDetails;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherInfoApiModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherInfoDBModel;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherBusinessModel implements IWeatherDetails{

    private CurrentWeatherCoordBusinessModel currentWeatherCoordBusinessModel;
    private List<CurrentWeatherInfoBusinessModel> currentWeatherInfoBusinessModel = new ArrayList<CurrentWeatherInfoBusinessModel>();
    private CurrentWeatherMainBusinessModel currentWeatherMainBusinessModel;
    private CurrentWeatherWindBusinessModel currentWeatherWindBusinessModel;
    private CurrentWeatherCloudsBusinessModel currentWeatherCloudsBusinessModel;
    private long dt;
    private CurrentWeatherSysBusinessModel currentWeatherSysBusinessModel;
    private int id;
    private String name;
    private int cod;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrentWeatherBusinessModel() {
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
        this.cod = currentWeather.getCod();
    }

    public CurrentWeatherBusinessModel(CurrentWeatherDBModel currentWeatherDBModel) {
        this.dt = currentWeatherDBModel.getDt();
        this.id = currentWeatherDBModel.getId();
        this.name = currentWeatherDBModel.getName();
        this.currentWeatherSysBusinessModel = new CurrentWeatherSysBusinessModel(currentWeatherDBModel.getCurrentWeatherSysDBModel().getCountry());
        this.currentWeatherCoordBusinessModel = new CurrentWeatherCoordBusinessModel(currentWeatherDBModel.getCurrentWeatherCoordDBModel());
        this.currentWeatherMainBusinessModel = new CurrentWeatherMainBusinessModel(currentWeatherDBModel.getCurrentWeatherMainDBModel());
        this.currentWeatherWindBusinessModel = new CurrentWeatherWindBusinessModel(currentWeatherDBModel.getCurrentWeatherWindDBModel());
        this.currentWeatherCloudsBusinessModel = new CurrentWeatherCloudsBusinessModel(currentWeatherDBModel.getCurrentWeatherCloudsDBModel());
        for(CurrentWeatherInfoDBModel current: currentWeatherDBModel.getCurrentWeatherInfoDBModel()){
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

    public long getDt() {
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

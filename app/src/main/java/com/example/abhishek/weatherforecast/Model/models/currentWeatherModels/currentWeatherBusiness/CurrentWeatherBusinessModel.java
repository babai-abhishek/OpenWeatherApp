
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness;

import com.example.abhishek.weatherforecast.IWeatherDetails;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherInfoApiModel;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity.CurrentWeather;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherBusinessModel implements IWeatherDetails{

    private List<CurrentWeatherInfoBusinessModel> currentWeatherInfoBusinessModel = new ArrayList<CurrentWeatherInfoBusinessModel>();
    private CurrentWeatherMainBusinessModel currentWeatherMainBusinessModel;
    private CurrentWeatherWindBusinessModel currentWeatherWindBusinessModel;
    private CurrentWeatherCloudsBusinessModel currentWeatherCloudsBusinessModel;
    private long dt;
    private CurrentWeatherSysBusinessModel currentWeatherSysBusinessModel;
    private int id;
    private String name;

    public CurrentWeatherBusinessModel(CurrentWeather currentWeather) {
        this.dt = (long) currentWeather.date;
        this.id = currentWeather.cityId;
        this.name = currentWeather.cityName;
        this.currentWeatherSysBusinessModel = new CurrentWeatherSysBusinessModel(currentWeather.country);
        this.currentWeatherInfoBusinessModel.add(new CurrentWeatherInfoBusinessModel(currentWeather.weatherDescription,
                currentWeather.iconId, currentWeather.weatherId));
        this.currentWeatherMainBusinessModel = new CurrentWeatherMainBusinessModel(currentWeather.minTemp,
                currentWeather.maxTemp);
    }


    public CurrentWeatherBusinessModel(CurrentWeatherApiModel currentWeather) {
        this.dt = currentWeather.getDt();
        this.id = currentWeather.getId();
        this.name = currentWeather.getName();
        this.currentWeatherSysBusinessModel = new CurrentWeatherSysBusinessModel(currentWeather.getCurrentWeatherSysApiModel().getCountry());
        this.currentWeatherMainBusinessModel = new CurrentWeatherMainBusinessModel(currentWeather.getCurrentWeatherMainApiModel());
        this.currentWeatherWindBusinessModel = new CurrentWeatherWindBusinessModel(currentWeather.getCurrentWeatherWindApiModel());
        this.currentWeatherCloudsBusinessModel = new CurrentWeatherCloudsBusinessModel(currentWeather.getCurrentWeatherCloudsApiModel());
        for(CurrentWeatherInfoApiModel current: currentWeather.getCurrentWeatherInfoApiModel()){
            this.currentWeatherInfoBusinessModel.add(new CurrentWeatherInfoBusinessModel(current));
        }
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

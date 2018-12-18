
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherWindApiModel;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherDb.CurrentWeatherWindDBModel;

public class CurrentWeatherWindBusinessModel {

    private double speed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherWindBusinessModel() {
    }

    /**
     * 
     * @param speed
     */
    public CurrentWeatherWindBusinessModel(double speed) {
        super();
        this.speed = speed;
    }

    public CurrentWeatherWindBusinessModel(CurrentWeatherWindApiModel currentWeatherWindApiModel) {
        this.speed = currentWeatherWindApiModel.getSpeed();
    }

    public CurrentWeatherWindBusinessModel(CurrentWeatherWindDBModel currentWeatherWindDBModel) {
        this.speed = currentWeatherWindDBModel.getSpeed();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}

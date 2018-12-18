
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WindApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherDb.WindDBModel;

public class WindBusinessModel {

    private double speed;
    private double deg;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WindBusinessModel() {
    }

    /**
     * 
     * @param speed
     * @param deg
     */
    public WindBusinessModel(double speed, double deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

    public WindBusinessModel(WindApiModel windApiModel) {
        this.speed = windApiModel.getSpeed();
        this.deg = windApiModel.getDeg();
    }

    public WindBusinessModel(WindDBModel windDBModel) {
        this.speed = windDBModel.getSpeed();
        this.deg = windDBModel.getDeg();
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

}

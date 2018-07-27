
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb;


public class CurrentWeatherWindDBModel {

    private double speed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherWindDBModel() {
    }

    /**
     * 
     * @param speed
     */
    public CurrentWeatherWindDBModel(double speed) {
        super();
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}

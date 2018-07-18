
package com.example.abhishek.weatherforecast.model.business;


import com.example.abhishek.weatherforecast.model.api.WindApiModel;

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

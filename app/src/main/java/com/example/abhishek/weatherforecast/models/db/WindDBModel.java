
package com.example.abhishek.weatherforecast.models.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindDBModel {


    private double speed;

    private double deg;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WindDBModel() {
    }

    /**
     * 
     * @param speed
     * @param deg
     */
    public WindDBModel(double speed, double deg) {
        super();
        this.speed = speed;
        this.deg = deg;
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

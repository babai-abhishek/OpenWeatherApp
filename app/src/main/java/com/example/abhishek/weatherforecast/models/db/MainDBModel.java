
package com.example.abhishek.weatherforecast.models.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainDBModel {


    private double temp;

    private double tempMin;

    private double tempMax;

    private long humidity;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainDBModel() {
    }

    /**
     * 
     * @param humidity
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public MainDBModel(double temp, double tempMin, double tempMax, int humidity) {
        super();
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

}

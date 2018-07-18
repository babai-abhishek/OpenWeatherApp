
package com.example.abhishek.weatherforecast.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainApiModel {

    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;
    @SerializedName("humidity")
    @Expose
    private long humidity;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainApiModel() {
    }

    /**
     * 
     * @param humidity
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public MainApiModel(double temp, double tempMin, double tempMax, long humidity) {
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

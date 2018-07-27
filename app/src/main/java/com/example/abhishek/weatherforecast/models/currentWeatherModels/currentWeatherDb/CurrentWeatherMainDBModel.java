
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb;


public class CurrentWeatherMainDBModel {

    private int humidity;
    private double tempMin;
    private double tempMax;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherMainDBModel() {
    }

    /**
     * 
     * @param humidity
     * @param tempMax
     * @param tempMin
     */
    public CurrentWeatherMainDBModel(int humidity, double tempMin, double tempMax) {
        super();
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
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

}

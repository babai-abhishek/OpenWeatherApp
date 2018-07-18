
package com.example.abhishek.weatherforecast.model.business;


import com.example.abhishek.weatherforecast.model.api.MainApiModel;

public class MainBusinessModel {

    private double temp;
    private double tempMin;
    private double tempMax;
    private long humidity;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainBusinessModel() {
    }

    /**
     * 
     * @param humidity
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public MainBusinessModel(double temp, double tempMin, double tempMax, long humidity) {
        super();
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
    }

    public MainBusinessModel(MainApiModel mainApiModel) {
        this.temp = mainApiModel.getTemp();
        this.tempMin = mainApiModel.getTempMin();
        this.tempMax = mainApiModel.getTempMax();
        this.humidity = mainApiModel.getHumidity();
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

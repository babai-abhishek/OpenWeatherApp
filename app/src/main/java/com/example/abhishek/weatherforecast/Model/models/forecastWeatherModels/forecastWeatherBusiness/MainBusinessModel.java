
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.MainApiModel;

public class MainBusinessModel {

    private double tempMin;
    private double tempMax;

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
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public MainBusinessModel(MainApiModel mainApiModel) {
        this.tempMin = mainApiModel.getTempMin();
        this.tempMax = mainApiModel.getTempMax();
    }

    public MainBusinessModel(double minTemp, double maxTemp) {
        this.tempMin = minTemp;
        this.tempMax = maxTemp;
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

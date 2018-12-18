
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherMainApiModel;

public class CurrentWeatherMainBusinessModel {

    private int humidity;
    private double tempMin;
    private double tempMax;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherMainBusinessModel() {
    }

    /**
     * 
     * @param humidity
     * @param tempMax
     * @param tempMin
     */
    public CurrentWeatherMainBusinessModel(int humidity, double tempMin, double tempMax) {
        super();
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public CurrentWeatherMainBusinessModel(CurrentWeatherMainApiModel currentWeatherMainApiModel) {
        this.humidity = currentWeatherMainApiModel.getHumidity();
        this.tempMax = currentWeatherMainApiModel.getTempMax();
        this.tempMin = currentWeatherMainApiModel.getTempMin();
    }

   /* public CurrentWeatherMainBusinessModel(CurrentWeatherMainDBModel currentWeatherMainDBModel) {
        this.humidity = currentWeatherMainDBModel.getHumidity();
        this.tempMin = currentWeatherMainDBModel.getTempMin();
        this.tempMax = currentWeatherMainDBModel.getTempMax();
    }*/

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

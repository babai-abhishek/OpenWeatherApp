
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherMainApiModel;

public class CurrentWeatherMainBusinessModel {

    private double tempMin;
    private double tempMax;

    /**
     * 
     * @param tempMax
     * @param tempMin
     */
    public CurrentWeatherMainBusinessModel(int humidity, double tempMin, double tempMax) {
        super();
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public CurrentWeatherMainBusinessModel(CurrentWeatherMainApiModel currentWeatherMainApiModel) {
        this.tempMax = currentWeatherMainApiModel.getTempMax();
        this.tempMin = currentWeatherMainApiModel.getTempMin();
    }

    public CurrentWeatherMainBusinessModel(double minTemp, double maxTemp) {
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

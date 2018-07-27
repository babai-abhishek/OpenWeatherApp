
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherCloudsApiModel;

public class CurrentWeatherCloudsBusinessModel {

    private int all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherCloudsBusinessModel() {
    }

    /**
     * 
     * @param all
     */
    public CurrentWeatherCloudsBusinessModel(int all) {
        super();
        this.all = all;
    }

    public CurrentWeatherCloudsBusinessModel(CurrentWeatherCloudsApiModel currentWeatherCloudsApiModel) {
        this.all = currentWeatherCloudsApiModel.getAll();
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}

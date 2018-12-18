
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherCloudsApiModel;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCloudsDBModel;

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

    public CurrentWeatherCloudsBusinessModel(CurrentWeatherCloudsDBModel currentWeatherCloudsDBModel) {
        this.all = currentWeatherCloudsDBModel.getAll();
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}

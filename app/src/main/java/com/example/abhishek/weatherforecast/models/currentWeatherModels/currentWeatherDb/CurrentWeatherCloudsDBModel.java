
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb;


public class CurrentWeatherCloudsDBModel {

    private int all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherCloudsDBModel() {
    }

    /**
     * 
     * @param all
     */
    public CurrentWeatherCloudsDBModel(int all) {
        super();
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

}

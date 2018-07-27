
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb;


public class CurrentWeatherSysDBModel {

    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherSysDBModel() {
    }

    /**
     * 
     * @param country
     */
    public CurrentWeatherSysDBModel(String country) {
        super();
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

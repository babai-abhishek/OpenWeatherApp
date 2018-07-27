
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness;


public class CurrentWeatherSysBusinessModel {

    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherSysBusinessModel() {
    }

    /**
     * 
     * @param country
     */
    public CurrentWeatherSysBusinessModel(String country) {
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

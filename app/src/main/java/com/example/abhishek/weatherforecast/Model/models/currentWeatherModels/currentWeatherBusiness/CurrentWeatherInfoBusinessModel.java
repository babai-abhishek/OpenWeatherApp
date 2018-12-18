
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherInfoApiModel;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherDb.CurrentWeatherInfoDBModel;

public class CurrentWeatherInfoBusinessModel {

    private String description;
    private String icon;
    private int weatherId;

    public CurrentWeatherInfoBusinessModel(CurrentWeatherInfoDBModel current) {
        this.description = current.getDescription();
        this.icon = current.getIcon();
        this.weatherId = current.getWeatherId();
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherInfoBusinessModel() {
    }

    /**
     * 
     * @param icon
     * @param description
     * @param main
     */
    public CurrentWeatherInfoBusinessModel(String main, String description, String icon, int id) {
        super();
        this.description = description;
        this.icon = icon;
        this.weatherId = weatherId;
    }

    public CurrentWeatherInfoBusinessModel(CurrentWeatherInfoApiModel current) {
        this.description = current.getDescription();
        this.icon = current.getIcon();
        this.weatherId = current.getWeatherId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}

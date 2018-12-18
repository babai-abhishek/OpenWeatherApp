
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherInfoApiModel;

public class WeatherInfoBusinessModel {

    private int id;
    private String description;
    private String icon;

    /**
     * No args constructor for use in serialization
     *
     */
    public WeatherInfoBusinessModel() {
    }

    /**
     * 
     * @param id
     * @param icon
     * @param description
     * @param main
     */
    public WeatherInfoBusinessModel(int id, String main, String description, String icon) {
        this.id = id;
        this.description = description;
        this.icon = icon;
    }

    public WeatherInfoBusinessModel(WeatherInfoApiModel weatherInfoApiModel) {
        this.id = weatherInfoApiModel.getId();
        this.description = weatherInfoApiModel.getDescription();
        this.icon = weatherInfoApiModel.getIcon();
    }

    public WeatherInfoBusinessModel(int weatherId, String weatherDescription, String iconId) {
        this.id = weatherId;
        this.description = weatherDescription;
        this.icon = iconId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


package com.example.abhishek.weatherforecast.model.business;


import com.example.abhishek.weatherforecast.model.api.WeatherInfoApiModel;

public class WeatherInfoBusinessModel {

    private long id;
    private String main;
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
    public WeatherInfoBusinessModel(long id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public WeatherInfoBusinessModel(WeatherInfoApiModel weatherInfoApiModel) {
        this.id = weatherInfoApiModel.getId();
        this.main = weatherInfoApiModel.getMain();
        this.description = weatherInfoApiModel.getDescription();
        this.icon = weatherInfoApiModel.getIcon();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
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

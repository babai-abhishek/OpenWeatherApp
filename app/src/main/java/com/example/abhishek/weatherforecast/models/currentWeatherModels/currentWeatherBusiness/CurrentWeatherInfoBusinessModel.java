
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherInfoApiModel;

public class CurrentWeatherInfoBusinessModel {

    private String main;
    private String description;
    private String icon;
    private int weatherId;

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
        this.main = main;
        this.description = description;
        this.icon = icon;
        this.weatherId = weatherId;
    }

    public CurrentWeatherInfoBusinessModel(CurrentWeatherInfoApiModel current) {
        this.description = current.getDescription();
        this.icon = current.getIcon();
    }

   /* public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }*/

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


package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb;


public class CurrentWeatherInfoDBModel {

//    private String main;
    private String description;
    private String icon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherInfoDBModel() {
    }

    /**
     * 
     * @param icon
     * @param description
     * @param main
     */
    public CurrentWeatherInfoDBModel(String main, String description, String icon) {
        super();
       // this.main = main;
        this.description = description;
        this.icon = icon;
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

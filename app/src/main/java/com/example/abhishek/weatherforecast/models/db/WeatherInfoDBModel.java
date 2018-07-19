
package com.example.abhishek.weatherforecast.models.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherInfoDBModel {


    private int id;

    private String main;

    private String description;

    private String icon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherInfoDBModel() {
    }

    /**
     * 
     * @param id
     * @param icon
     * @param description
     * @param main
     */
    public WeatherInfoDBModel(int id, String main, String description, String icon) {
        super();
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

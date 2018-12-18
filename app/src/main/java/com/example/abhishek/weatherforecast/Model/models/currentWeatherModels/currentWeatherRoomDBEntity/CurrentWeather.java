package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by abhishek on 18/12/18.
 */

@Entity(tableName = "currentWeather")
public class CurrentWeather {

    @PrimaryKey
    public final int cityId;

    public final String cityName;
    public final String country;
    public final double date;
    public final int weatherId;
    public final double minTemp;
    public final double maxTemp;
    public final String iconId;
    public final String weatherDescription;


    public CurrentWeather(int cityId, String cityName, String country, double date, int weatherId, double minTemp, double maxTemp, String iconId, String weatherDescription) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
        this.date = date;
        this.weatherId = weatherId;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.iconId = iconId;
        this.weatherDescription = weatherDescription;
    }
}

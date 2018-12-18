package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by abhishek on 18/12/18.
 */

@Entity(tableName = "currentWeather")
public class CurrentWeather {

    @PrimaryKey
    String cityId;

    String cityName;
    String country;
    double date;
    int weatherId;
    double minTemp;
    double maxTemp;
    int iconId;
    String weatherDescription;


    public CurrentWeather(String cityId, String cityName, String country, double date, int weatherId, double minTemp, double maxTemp, int iconId, String weatherDescription) {
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

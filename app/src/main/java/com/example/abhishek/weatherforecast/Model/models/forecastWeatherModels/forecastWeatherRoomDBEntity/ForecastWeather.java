package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherRoomDBEntity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity.CurrentWeather;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by abhishek on 18/12/18.
 */

@Entity(tableName = "forecastWeather",
        foreignKeys = @ForeignKey(entity = CurrentWeather.class,
        parentColumns = "cityId",
        childColumns = "cityId",
        onDelete = CASCADE))

public class ForecastWeather {

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


    public ForecastWeather(String cityId, String cityName, String country, double date, int weatherId, double minTemp, double maxTemp, int iconId, String weatherDescription) {
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

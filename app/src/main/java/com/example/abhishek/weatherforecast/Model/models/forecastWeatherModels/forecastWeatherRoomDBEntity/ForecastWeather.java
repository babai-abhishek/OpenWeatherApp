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
        primaryKeys = { "cityId", "date" },
        foreignKeys = @ForeignKey(entity = CurrentWeather.class,
        parentColumns = "cityId",
        childColumns = "cityId"))

public class ForecastWeather {

    public final long cityId;

    public final String cityName;
    public final double date;
    public final int weatherId;
    public final double minTemp;
    public final double maxTemp;
    public final String iconId;
    public final String weatherDescription;


    public ForecastWeather(long cityId, String cityName, double date, int weatherId, double minTemp, double maxTemp, String iconId, String weatherDescription) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.date = date;
        this.weatherId = weatherId;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.iconId = iconId;
        this.weatherDescription = weatherDescription;
    }
}

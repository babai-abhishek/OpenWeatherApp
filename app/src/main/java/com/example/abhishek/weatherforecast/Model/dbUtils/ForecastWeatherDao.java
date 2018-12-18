package com.example.abhishek.weatherforecast.Model.dbUtils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity.CurrentWeather;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherRoomDBEntity.ForecastWeather;

import java.util.List;

/**
 * Created by abhishek on 18/12/18.
 */

@Dao
public interface ForecastWeatherDao {
    @Insert
    void insert(List<ForecastWeather> forecastWeatherList);

    @Update
    void update(ForecastWeather forecastWeathers);

    @Delete
    void delete(ForecastWeather forecastWeathers);

    @Query("SELECT * FROM forecastWeather")
    List<ForecastWeather> getAllforecastWeathers();

    @Query("SELECT * FROM forecastWeather WHERE cityId=:cityId")
    List<ForecastWeather> getAllforecastWeathersById(long cityId);
}

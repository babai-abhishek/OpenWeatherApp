package com.example.abhishek.weatherforecast.Model.dbUtils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity.CurrentWeather;

import java.util.List;

/**
 * Created by abhishek on 18/12/18.
 */

@Dao
public interface CurrentWeatherDao {

    @Insert
    void insert(CurrentWeather currentWeather);

    @Update
    void update(CurrentWeather currentWeathers);

    @Delete
    void delete(CurrentWeather currentWeathers);

    @Query("SELECT * FROM currentWeather")
    List<CurrentWeather> getAllCurrentWeathers();

}

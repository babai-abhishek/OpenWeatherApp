package com.example.abhishek.weatherforecast.Model.dbUtils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity.CurrentWeather;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherRoomDBEntity.ForecastWeather;

@Database(entities = { CurrentWeather.class, ForecastWeather.class },
        version = 6)
public abstract class WeatherDatabase extends RoomDatabase {

    private static final String DB_NAME = "weatherDatabase.db";
    private static volatile WeatherDatabase instance;

   public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static WeatherDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                WeatherDatabase.class,
                DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    public abstract CurrentWeatherDao getCurrentWeatherDao();
    public abstract ForecastWeatherDao getForecastWeatherDao();
}

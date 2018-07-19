package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abhishek.weatherforecast.models.business.WeatherBusinessModel;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherForecastDBDao {

    private static WeatherForecastDBHelper weatherForecastDBHelper;

    public static void insertData(WeatherBusinessModel weather, Context ctx){
        ContentValues[] weatherValues = WeatherForecastJsonUtils
                .getWeatherForecastContentValuesFromJson(weather.getWeatherListBusinessModel(),
                        weather.getCityBusinessModel().getId());

        ContentValues cityContentValue = WeatherForecastJsonUtils
                .getCityContentValues(weather.getCityBusinessModel());

        weatherForecastDBHelper = new WeatherForecastDBHelper(ctx);

        if(weatherValues.length != 0){
            SQLiteDatabase database = weatherForecastDBHelper.getWritableDatabase();
            database.beginTransaction();
            int rowsInserted = 0;
            try {

                database.insert(WeatherForecastContract.CityEntry.CITY_TABLE_NAME,
                        null,
                        cityContentValue);

                for (ContentValues cv: weatherValues){
                    long id = database.insert(WeatherForecastContract.WeatherEntry.WEATHER_TABLE_NAME,
                            null,
                            cv);
                    if(id != -1){
                        rowsInserted++;
                        Log.d("#", "row "+rowsInserted);
                    }
                }
                database.setTransactionSuccessful();
            }finally {
                database.endTransaction();
            }
        }

    }
}

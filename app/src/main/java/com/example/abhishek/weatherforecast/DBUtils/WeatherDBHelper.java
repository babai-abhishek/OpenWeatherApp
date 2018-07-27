package com.example.abhishek.weatherforecast.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_COUNTRY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DESCRIPTION;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_HUMIDITY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_ICON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LAT;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MAX_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MIN_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WIND_SPEED;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 23;
    public static final String DATABASE_NAME = "weather.db";


    public WeatherDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        final String SQL_CREATE_WEATHER_FORECAST_TABLE =  "CREATE TABLE " + WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME + " (" +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE + " INTEGER NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID + " INTEGER NOT NULL," +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CITY_NAME  + " TEXT NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_COUNTRY  + " TEXT NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MIN_TEMP   + " REAL NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MAX_TEMP   + " REAL NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_HUMIDITY   + " REAL NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_ICON       + " TEXT NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LAT + " REAL NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LON + " REAL NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DESCRIPTION    + " TEXT NOT NULL, " +
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE    + " REAL NOT NULL, " +
                "PRIMARY KEY("+ WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE +","+ WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID+"));";

        final String SQL_CREATE_CURRENT_WEATHER_TABLE =  "CREATE TABLE " + CURRENT_WEATHER_TABLE_NAME + " (" +
                CURRENT_WEATHER_TABLE_COLUMN_DATE + " INTEGER NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID + " INTEGER NOT NULL PRIMARY KEY," +
                CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME  + " TEXT NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_COUNTRY + " TEXT NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_MIN_TEMP  + " REAL NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_MAX_TEMP   + " REAL NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_HUMIDITY  + " REAL NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_ICON      + " TEXT NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_LAT + " REAL NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_LON + " REAL NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_DESCRIPTION    + " TEXT NOT NULL, " +
                CURRENT_WEATHER_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE    + " REAL NOT NULL);";


        db.execSQL(SQL_CREATE_CURRENT_WEATHER_TABLE);
        db.execSQL(SQL_CREATE_WEATHER_FORECAST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME);
        onCreate(db);
    }
}

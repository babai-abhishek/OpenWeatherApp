package com.example.abhishek.weatherforecast.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weatherforecast.db";


    public WeatherDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_CITY_TABLE =  "CREATE TABLE " + WeatherContract.CityEntry.CITY_TABLE_NAME + " (" +
                WeatherContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID + " INTEGER PRIMARY KEY, " +
                WeatherContract.CityEntry.CITY_TABLE_COLUMN_CITY_NAME + " TEXT NOT NULL, " +
                WeatherContract.CityEntry.CITY_TABLE_COLUMN_LAT + " REAL NOT NULL,"                  +
                WeatherContract.CityEntry.CITY_TABLE_COLUMN_LON   + " REAL NOT NULL, "                    +
                WeatherContract.CityEntry.CITY_TABLE_COLUMN_COUNTRY   + " TEXT NOT NULL);";


        final String SQL_CREATE_WEATHER_TABLE =  "CREATE TABLE " + WeatherContract.WeatherEntry.WEATHER_TABLE_NAME + " (" +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_DATE + " INTEGER NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_WEATHER_OF_CITY_ID + " INTEGER NOT NULL," +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_MIN_TEMP   + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_MAX_TEMP   + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_HUMIDITY   + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_DEGREES    + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_WIND_SPEED + " REAL NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_ICON       + " TEXT NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_DESCRIPTION    + " TEXT NOT NULL, " +
                WeatherContract.WeatherEntry.WEATHER_COLUMN_CLOUDS_IN_PERCENTAGE    + " REAL NOT NULL, " +
                " FOREIGN KEY ("+WeatherContract.WeatherEntry.WEATHER_COLUMN_WEATHER_OF_CITY_ID+") REFERENCES "+ WeatherContract.CityEntry.CITY_TABLE_NAME+"("+ WeatherContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID+"));";

        db.execSQL(SQL_CREATE_CITY_TABLE);
        db.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

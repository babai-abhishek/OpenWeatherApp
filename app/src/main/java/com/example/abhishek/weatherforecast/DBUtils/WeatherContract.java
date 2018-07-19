package com.example.abhishek.weatherforecast.DBUtils;

import android.provider.BaseColumns;

/**
 * Created by abhishek on 18/7/18.
 */

public class WeatherContract {

    public static final class WeatherEntry implements BaseColumns {

        public static final String WEATHER_TABLE_NAME = "weather";
        public static final String WEATHER_COLUMN_DATE = "date";
        public static final String WEATHER_COLUMN_WEATHER_OF_CITY_ID = "weather_of_city_id";
        public static final String WEATHER_COLUMN_MIN_TEMP = "min";
        public static final String WEATHER_COLUMN_MAX_TEMP = "max";
        public static final String WEATHER_COLUMN_HUMIDITY = "humidity";
        public static final String WEATHER_COLUMN_WIND_SPEED = "wind";
        public static final String WEATHER_COLUMN_DEGREES = "degrees";
        public static final String WEATHER_COLUMN_ICON = "icon_id";
        public static final String WEATHER_COLUMN_DESCRIPTION = "weather_description";
        public static final String WEATHER_COLUMN_CLOUDS_IN_PERCENTAGE = "clouds";
    }

    public static final class CityEntry implements BaseColumns{

        public static final String CITY_TABLE_NAME = "city";
        public static final String CITY_TABLE_COLUMN_CITY_ID = "id";
        public static final String CITY_TABLE_COLUMN_CITY_NAME = "name";
        public static final String CITY_TABLE_COLUMN_LAT = "lat";
        public static final String CITY_TABLE_COLUMN_LON = "lon";
        public static final String CITY_TABLE_COLUMN_COUNTRY = "country";

    }

}

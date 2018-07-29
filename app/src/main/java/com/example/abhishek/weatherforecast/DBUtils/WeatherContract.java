package com.example.abhishek.weatherforecast.DBUtils;

import android.provider.BaseColumns;

/**
 * Created by abhishek on 18/7/18.
 */

public class WeatherContract {

    public static final class WeatherForecastEntry implements BaseColumns {

        public static final String WEATHER_FORECAST_TABLE_NAME = "forecastweather";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_DATE = "date";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID = "_city_id";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_WEATHER_CONDITION_ID = "weather_id";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_MIN_TEMP = "min_temp";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_MAX_TEMP = "max_temp";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_HUMIDITY = "humidity";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_WIND_SPEED = "wind";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_ICON = "icon_id";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_DESCRIPTION = "weather_description";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE = "clouds";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_CITY_NAME = "name";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_LAT = "lat";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_LON = "lon";
        public static final String WEATHER_FORECAST_TABLE_COLUMN_COUNTRY = "country";
    }

    public static final class CurrentWeatherEntry implements BaseColumns{

        public static final String CURRENT_WEATHER_TABLE_NAME = "currentweather";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_DATE = "date";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID = "_city_id";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME = "name";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_LAT = "lat";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_LON = "lon";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_COUNTRY = "country";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_WEATHER_CONDITION_ID = "weather_id";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_MIN_TEMP = "min_temp";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_MAX_TEMP = "max_temp";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_HUMIDITY = "humidity";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_WIND_SPEED = "wind";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_ICON = "icon_id";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_DESCRIPTION = "weather_description";
        public static final String CURRENT_WEATHER_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE = "clouds";

    }

}

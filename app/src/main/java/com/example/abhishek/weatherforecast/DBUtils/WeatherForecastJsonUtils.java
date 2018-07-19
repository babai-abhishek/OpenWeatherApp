package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.util.Log;

import com.example.abhishek.weatherforecast.models.business.CityBusinessModel;
import com.example.abhishek.weatherforecast.models.business.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.business.WeatherListBusinessModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_CLOUDS_IN_PERCENTAGE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_DATE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_DEGREES;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_DESCRIPTION;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_HUMIDITY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_ICON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_MAX_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_MIN_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_WIND_SPEED;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherForecastJsonUtils {
    public static ContentValues[] getWeatherForecastContentValuesFromJson(List<WeatherListBusinessModel> weather, long id){

        ContentValues[] weatherContentValues = new ContentValues[weather.size()];

        for(int i=0; i<weather.size(); i++){
            ContentValues weatherContentValue = new ContentValues();
            weatherContentValue.put(WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_WEATHER_OF_CITY_ID, id);
            weatherContentValue.put(WEATHER_COLUMN_DATE, weather.get(i).getDt());
            weatherContentValue.put(WEATHER_COLUMN_MIN_TEMP, weather.get(i).getMainBusinessModel().getTempMin());
            weatherContentValue.put(WEATHER_COLUMN_MAX_TEMP, weather.get(i).getMainBusinessModel().getTempMax());
            weatherContentValue.put(WEATHER_COLUMN_HUMIDITY, weather.get(i).getMainBusinessModel().getHumidity());
            weatherContentValue.put(WEATHER_COLUMN_WIND_SPEED, weather.get(i).getWindBusinessModel().getSpeed());
            weatherContentValue.put(WEATHER_COLUMN_ICON, weather.get(i).getWeatherInfoBusinessModel().get(0).getIcon());
            weatherContentValue.put(WEATHER_COLUMN_DESCRIPTION, weather.get(i).getWeatherInfoBusinessModel().get(0).getDescription());
            weatherContentValue.put(WEATHER_COLUMN_CLOUDS_IN_PERCENTAGE, weather.get(i).getCloudsBusinessModel().getAll());

            weatherContentValues[i] = weatherContentValue;
        }

        return weatherContentValues;
    }

    public static ContentValues getCityContentValues(CityBusinessModel cityBusinessModel) {

        ContentValues cityContentValue = new ContentValues();
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID, cityBusinessModel.getId());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_NAME, cityBusinessModel.getName());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_LAT, cityBusinessModel.getCoordBusinessModel().getLat());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_LON, cityBusinessModel.getCoordBusinessModel().getLon());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_COUNTRY, cityBusinessModel.getCountry());

        return cityContentValue;
    }
}

package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.util.Log;

import com.example.abhishek.weatherforecast.models.business.CityBusinessModel;
import com.example.abhishek.weatherforecast.models.business.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.business.WeatherListBusinessModel;
import com.example.abhishek.weatherforecast.models.db.CityDBModel;
import com.example.abhishek.weatherforecast.models.db.WeatherDBModel;
import com.example.abhishek.weatherforecast.models.db.WeatherListDBModel;

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
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_WEATHER_OF_CITY_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_WIND_SPEED;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherForecastJsonUtils {
    public static ContentValues[] getWeatherForecastContentValuesFromJson(List<WeatherListDBModel> weather, long id){

        ContentValues[] weatherContentValues = new ContentValues[weather.size()];

        for(int i=0; i<weather.size(); i++){
            ContentValues weatherContentValue = new ContentValues();
            weatherContentValue.put(WEATHER_COLUMN_WEATHER_OF_CITY_ID, id);
            weatherContentValue.put(WEATHER_COLUMN_DATE, weather.get(i).getDt());
            weatherContentValue.put(WEATHER_COLUMN_MIN_TEMP, weather.get(i).getMainDBModel().getTempMin());
            weatherContentValue.put(WEATHER_COLUMN_MAX_TEMP, weather.get(i).getMainDBModel().getTempMax());
            weatherContentValue.put(WEATHER_COLUMN_HUMIDITY, weather.get(i).getMainDBModel().getHumidity());
            weatherContentValue.put(WEATHER_COLUMN_WIND_SPEED, weather.get(i).getWindDBModel().getSpeed());
            weatherContentValue.put(WEATHER_COLUMN_ICON, weather.get(i).getWeatherInfoDBModel().get(0).getIcon());
            weatherContentValue.put(WEATHER_COLUMN_DESCRIPTION, weather.get(i).getWeatherInfoDBModel().get(0).getDescription());
            weatherContentValue.put(WEATHER_COLUMN_CLOUDS_IN_PERCENTAGE, weather.get(i).getCloudsDBModel().getAll());

            weatherContentValues[i] = weatherContentValue;
        }

        return weatherContentValues;
    }

    public static ContentValues getCityContentValues(CityDBModel cityDBModel) {

        ContentValues cityContentValue = new ContentValues();
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID, cityDBModel.getId());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_NAME, cityDBModel.getName());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_LAT, cityDBModel.getCoordDBModel().getLat());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_LON, cityDBModel.getCoordDBModel().getLon());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_COUNTRY, cityDBModel.getCountry());

        return cityContentValue;
    }
}

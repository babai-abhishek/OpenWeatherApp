package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry;
import com.example.abhishek.weatherforecast.Utils;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCloudsDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCoordDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherMainDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherSysDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherWindDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.CloudsDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.MainDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.SysDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherListDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WindDBModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherDBDao {

    private static WeatherDBHelper weatherDBHelper;
    private static boolean isTimeAlreadyPresent = false;

    public static void insertWeatherForecastIntoDB(WeatherDBModel weather, Context ctx) {

        //convert business model class into db model class
        WeatherDBModel weatherDBModel = weather;

        ContentValues[] weatherForecastContentValues = Utils
                .convertIntoContentValues(weatherDBModel);

        weatherDBHelper = new WeatherDBHelper(ctx);

        //GET AVAILABLE DATE VALUES OF THE RESPECTIVE CITY
         List<String> availableDateAndTime = Utils.DBUtils.getAlreadyPresentDatesFromDB(String.valueOf(weather.getCityDBModel().getId()),
                weatherDBHelper);

        //CHECK WHETHER WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE VALUE
        //MATHCES WITH NEW INCOMING DATE VALUE
        for (ContentValues weathervalue : weatherForecastContentValues) {
            String date = String.valueOf(weathervalue.get(WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE));

            //CHECK ALREADY DATE PRESENT OR NOT
            isTimeAlreadyPresent = availableDateAndTime.contains(date);

            //IF UNIQUE THEN INSERT
            if (!isTimeAlreadyPresent) {
                //INSERT UNIQUE DATE
                SQLiteDatabase database = weatherDBHelper.getWritableDatabase();
                database.beginTransaction();
                int rowsInserted = 0;

                long id = database.insert(WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME,
                        null,
                        weathervalue);
                if (id != -1) {
                    rowsInserted++;
                    Log.d("#", "row " + rowsInserted);

                }

                database.setTransactionSuccessful();
                database.endTransaction();

            }

        }

    }

    public static void insertCurrentWeatherIntoDB(CurrentWeatherDBModel currentWeatherDBModel, Context ctx) {

        ContentValues currentWeatherContentValue = Utils
                .convertIntoContentValues(currentWeatherDBModel);

        weatherDBHelper = new WeatherDBHelper(ctx);
        long dt = 0;
        //GET DATE VALUE PRESENT IN TABLE FOR PARTICULAR CITY
        String qry = "SELECT "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE+" " +
                "FROM "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME+"" +
                " WHERE "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID+" = \""+currentWeatherDBModel.getId()+"\"";
        SQLiteDatabase db = weatherDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(qry, null);
        if(cursor.getCount() <= 0){
            SQLiteDatabase writeOperation = weatherDBHelper.getWritableDatabase();
            writeOperation.beginTransaction();
            writeOperation.insert(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME,
                    null,
                    currentWeatherContentValue);
            writeOperation.setTransactionSuccessful();
            writeOperation.endTransaction();
        }
        else {
            if(cursor.moveToFirst()){
                dt = cursor.getLong(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE));
            }
            cursor.close();

            if(String.valueOf(currentWeatherDBModel.getDt()).equals(String.valueOf(dt))){
                return;
            }

            SQLiteDatabase updateOperation = weatherDBHelper.getWritableDatabase();
            updateOperation.beginTransaction();
            updateOperation.update(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME,
                    currentWeatherContentValue,
                    "_city_id="+currentWeatherContentValue.get(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID),
                    null);
            updateOperation.setTransactionSuccessful();
            updateOperation.endTransaction();

        }
    }

    public static CurrentWeatherDBModel getCurrentWeather(String city, Context ctx){

        String sqry = "SELECT * FROM " + WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME
                + " WHERE " + WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME
                + " = \"" + city + "\"";
        SQLiteDatabase db = new WeatherDBHelper(ctx).getReadableDatabase();
        Cursor cursor = db.rawQuery(sqry, null);
        CurrentWeatherDBModel currentWeatherDBModel = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            currentWeatherDBModel = new CurrentWeatherDBModel();
            currentWeatherDBModel.setDt(cursor.getLong(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE)));
            CurrentWeatherMainDBModel mainDBModel = new CurrentWeatherMainDBModel();
            mainDBModel.setTempMin(cursor.getDouble(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MIN_TEMP)));
            mainDBModel.setTempMax(cursor.getDouble(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MAX_TEMP)));
            currentWeatherDBModel.setCurrentWeatherMainDBModel(mainDBModel);
            List<CurrentWeatherInfoDBModel> infoDBModels = new ArrayList<>();
            CurrentWeatherInfoDBModel infoDBModel = new CurrentWeatherInfoDBModel();
            infoDBModel.setWeatherId(cursor.getInt(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_CONDITION_ID)));
            infoDBModel.setIcon(String.valueOf(cursor.getInt(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_ICON))));
            infoDBModel.setDescription(String.valueOf(cursor.getInt(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DESCRIPTION))));
            infoDBModels.add(infoDBModel);
            currentWeatherDBModel.setCurrentWeatherInfoDBModel(infoDBModels);
            currentWeatherDBModel.setCurrentWeatherSysDBModel(new CurrentWeatherSysDBModel(cursor.getString(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_COUNTRY))));
            currentWeatherDBModel.setId(cursor.getInt(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID)));
            currentWeatherDBModel.setName(cursor.getString(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME)));
            currentWeatherDBModel.setCurrentWeatherCloudsDBModel(new CurrentWeatherCloudsDBModel());
            CurrentWeatherWindDBModel windDBModel = new CurrentWeatherWindDBModel();
            windDBModel.setSpeed(cursor.getDouble(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WIND_SPEED)));
            currentWeatherDBModel.setCurrentWeatherWindDBModel(windDBModel);
            CurrentWeatherCoordDBModel coordDBModel = new CurrentWeatherCoordDBModel();
            coordDBModel.setLon(cursor.getDouble(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LON)));
            coordDBModel.setLat(cursor.getDouble(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LAT)));
            currentWeatherDBModel.setCurrentWeatherCoordDBModel(coordDBModel);
        }

        return currentWeatherDBModel;

    }

    public static List<WeatherListDBModel> getForecastWeather(int cityid, long dateTime, Context ctx){
        List<WeatherListDBModel> listOfDbModels = new ArrayList<>();

        String qry = "SELECT * FROM forecastweather WHERE _city_id=\"" + cityid + "\" AND date>\"" + dateTime + "\"";
        SQLiteDatabase db = new WeatherDBHelper(ctx).getReadableDatabase();
        Cursor cursor = db.rawQuery(qry, null);
        if (cursor.moveToFirst()) {
            do {
                WeatherListDBModel dbModel = new WeatherListDBModel();
                dbModel.setDt(cursor.getLong(cursor.getColumnIndex(WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE)));

                WeatherInfoDBModel weatherInfoDBModel = new WeatherInfoDBModel();
                weatherInfoDBModel.setId(cursor.getInt(cursor.getColumnIndex(WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_CONDITION_ID)));
                weatherInfoDBModel.setDescription(cursor.getString(cursor.getColumnIndex(WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DESCRIPTION)));
                weatherInfoDBModel.setIcon(cursor.getString(cursor.getColumnIndex(WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_ICON)));
                List<WeatherInfoDBModel> dbModelList = new ArrayList<>();
                dbModelList.add(weatherInfoDBModel);
                dbModel.setWeatherInfoDBModel(dbModelList);

                MainDBModel mainDBModel = new MainDBModel();
                mainDBModel.setTempMax(cursor.getDouble(cursor.getColumnIndex(WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MAX_TEMP)));
                mainDBModel.setTempMin(cursor.getDouble(cursor.getColumnIndex(WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MIN_TEMP)));
                dbModel.setMainDBModel(mainDBModel);

                dbModel.setSysDBModel(new SysDBModel());
                dbModel.setCloudsDBModel(new CloudsDBModel());
                dbModel.setWindDBModel(new WindDBModel());
                listOfDbModels.add(dbModel);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listOfDbModels;
    }

}


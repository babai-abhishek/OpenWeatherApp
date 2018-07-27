package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.CityDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.MainDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherListDBModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CITY_NAME;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_COUNTRY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DESCRIPTION;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_HUMIDITY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_ICON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LAT;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MAX_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MIN_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WIND_SPEED;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherForecastUtils {

    public static ContentValues[] getWeatherForecastContentValuesFromJson(WeatherDBModel weather){

        ContentValues[] weatherContentValues = new ContentValues[weather.getWeatherListDBModel().size()];

        for(int i=0; i<weather.getWeatherListDBModel().size(); i++){

            ContentValues weatherContentValue = new ContentValues();
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID, weather.getCityDBModel().getId());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_DATE, weather.getWeatherListDBModel().get(i).getDt());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_MIN_TEMP, weather.getWeatherListDBModel().get(i).getMainDBModel().getTempMin());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_MAX_TEMP, weather.getWeatherListDBModel().get(i).getMainDBModel().getTempMax());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_HUMIDITY, weather.getWeatherListDBModel().get(i).getMainDBModel().getHumidity());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_WIND_SPEED, weather.getWeatherListDBModel().get(i).getWindDBModel().getSpeed());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_ICON, weather.getWeatherListDBModel().get(i).getWeatherInfoDBModel().get(0).getIcon());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_DESCRIPTION, weather.getWeatherListDBModel().get(i).getWeatherInfoDBModel().get(0).getDescription());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE, weather.getWeatherListDBModel().get(i).getCloudsDBModel().getAll());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_CITY_NAME, weather.getCityDBModel().getName());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_LAT, weather.getCityDBModel().getCoordDBModel().getLat());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_LON, weather.getCityDBModel().getCoordDBModel().getLon());
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_COUNTRY, weather.getCityDBModel().getCountry());

            weatherContentValues[i] = weatherContentValue;
        }

        return weatherContentValues;
    }

   /* public static ContentValues getCityContentValues(CityDBModel cityDBModel) {

        ContentValues cityContentValue = new ContentValues();
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID, cityDBModel.getId());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_NAME, cityDBModel.getName());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_LAT, cityDBModel.getCoordDBModel().getLat());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_LON, cityDBModel.getCoordDBModel().getLon());
        cityContentValue.put(WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_COUNTRY, cityDBModel.getCountry());

        return cityContentValue;
    }*/

   public static List<String> getAlreadyPresentDatesFromDB(String cityId, WeatherForecastDBHelper weatherForecastDBHelper){
        List<String> dates = new ArrayList<>();
        SQLiteDatabase database = weatherForecastDBHelper.getReadableDatabase();
        String qry = "SELECT "+WEATHER_FORECAST_TABLE_COLUMN_DATE+" FROM "+
                WeatherForecastContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME+" where "
                + WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID+" = "+cityId+"";
        Cursor cursor = database.rawQuery(qry, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return dates;
        }
        if (cursor.moveToFirst()){
            do{
                dates.add(String.valueOf(cursor.getString(cursor.getColumnIndex(WEATHER_FORECAST_TABLE_COLUMN_DATE))));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return dates;
    }

 /*    public static boolean checkCityAlreadyPresentInDB(long id, WeatherForecastDBHelper weatherForecastDBHelper) {
        SQLiteDatabase database = weatherForecastDBHelper.getReadableDatabase();
        String qry = "SELECT * FROM "+WeatherForecastContract.CityEntry.CITY_TABLE_NAME+" where "+WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID+" = "+id+"";
        Cursor cursor = database.rawQuery(qry, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }*/

    /*public static void insertCityIntoTable(ContentValues cityContentValue, WeatherForecastDBHelper weatherForecastDBHelper) {
        SQLiteDatabase database = weatherForecastDBHelper.getWritableDatabase();
        database.beginTransaction();
        try {

            database.insert(WeatherForecastContract.CityEntry.CITY_TABLE_NAME,
                    null,cityContentValue);
            database.setTransactionSuccessful();
        }finally {
            database.endTransaction();
        }
    }

    public static void insertWeatherForecastInfoIntoTable(ContentValues[] weatherContentVlues, WeatherForecastDBHelper weatherForecastDBHelper){
      //  int rowsInserted = 0;
        SQLiteDatabase database = weatherForecastDBHelper.getWritableDatabase();
        database.beginTransaction();

        for(ContentValues cv: weatherContentVlues){
            long id = database.insert(WeatherForecastContract.WeatherForecastEntry.WEATHER_TABLE_NAME,
                    null,
                    cv);
            *//*if(id != -1){
                rowsInserted++;
                Log.d("#", "row "+rowsInserted);
            }*//*
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public static List<WeatherListDBModel> getWeatherDetailsFromToday(long cityId, WeatherForecastDBHelper weatherForecastDBHelper) {

        List<WeatherListDBModel> listOfWeatherListDBModelsFromToday = new ArrayList<>();
        List<WeatherListDBModel> singleWeatherDataPerDateFromToday = new ArrayList<>();

        SQLiteDatabase database = weatherForecastDBHelper.getReadableDatabase();

        long getTodayMidnightEpoch = getEpochForTodayMidnight();

        //query to get dates from today upto 5 days from today
        String sqlQry = "SELECT * FROM "+WEATHER_TABLE_NAME+" WHERE " +
                "date >= \""+getTodayMidnightEpoch+"\" and _city_id = \""+cityId+"\"";

        Cursor cursor = database.rawQuery(sqlQry, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return singleWeatherDataPerDateFromToday;
        }

        if (cursor.moveToFirst()){
            do{
                WeatherListDBModel weatherListDBModel = new WeatherListDBModel();

                weatherListDBModel.setDt(cursor.getLong(cursor.getColumnIndex(WeatherForecastContract.WeatherForecastEntry.WEATHER_COLUMN_DATE)));

                MainDBModel mainDBModel = new MainDBModel();
                mainDBModel.setTempMin(cursor.getDouble(cursor.getColumnIndex(WeatherForecastContract.WeatherForecastEntry.WEATHER_COLUMN_MIN_TEMP)));
                mainDBModel.setTempMax(cursor.getDouble(cursor.getColumnIndex(WeatherForecastContract.WeatherForecastEntry.WEATHER_COLUMN_MAX_TEMP)));
                weatherListDBModel.setMainDBModel(mainDBModel);

                WeatherInfoDBModel weatherInfoDBModel = new WeatherInfoDBModel();
                weatherInfoDBModel.setDescription(cursor.getString(cursor.getColumnIndex(WeatherForecastContract.WeatherForecastEntry.WEATHER_COLUMN_DESCRIPTION)));
                weatherInfoDBModel.setIcon(cursor.getString(cursor.getColumnIndex(WeatherForecastContract.WeatherForecastEntry.WEATHER_COLUMN_ICON)));
                List<WeatherInfoDBModel> infoDBModels = new ArrayList<>();
                infoDBModels.add(weatherInfoDBModel);
                weatherListDBModel.setWeatherInfoDBModel(infoDBModels);

                listOfWeatherListDBModelsFromToday.add(weatherListDBModel);
            }while(cursor.moveToNext());
        }
        cursor.close();

        //get single weather data per date
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for(WeatherListDBModel weatherListDBModel : listOfWeatherListDBModelsFromToday){
            long utcDate = weatherListDBModel.getDt()*1000L;
            Date dateInCurrentTimezone = new Date(utcDate);
            if(sdf.format(currentDate).equals(sdf.format(dateInCurrentTimezone))){
                singleWeatherDataPerDateFromToday.add(weatherListDBModel);
                currentDate = getNextDate(currentDate);
            }
        }

        return singleWeatherDataPerDateFromToday;
    }

    private static Date getNextDate(Date curDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
    }

    private static long getEpochForTodayMidnight(){
        Date currDate = new Date();
        Calendar cal = Calendar.getInstance();
        // compute start of the day for the timestamp
        cal.setTime(currDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTimeInMillis()/1000;
    }
*/
}

package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry;
import com.example.abhishek.weatherforecast.models.business.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.db.CityDBModel;
import com.example.abhishek.weatherforecast.models.db.CloudsDBModel;
import com.example.abhishek.weatherforecast.models.db.CoordDBModel;
import com.example.abhishek.weatherforecast.models.db.MainDBModel;
import com.example.abhishek.weatherforecast.models.db.SysDBModel;
import com.example.abhishek.weatherforecast.models.db.WeatherDBModel;
import com.example.abhishek.weatherforecast.models.db.WeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.db.WeatherListDBModel;
import com.example.abhishek.weatherforecast.models.db.WindDBModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherEntry.WEATHER_COLUMN_WEATHER_OF_CITY_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastUtils.getAlreadyPresentDatesFromDB;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastUtils.insertCityIntoTable;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastUtils.insertWeatherForecastInfoIntoTable;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherForecastDBDao {

    private static WeatherForecastDBHelper weatherForecastDBHelper;
    private static boolean isCityAlreadyPresent = false;
    private static boolean isWeatherDetailsAlreadyPresent = false;

    public static void insertData(WeatherBusinessModel weather, Context ctx){

        //convert business model class into db model class
        WeatherDBModel weatherDBModel = new WeatherDBModel();

        CoordDBModel coordDBModel = new CoordDBModel();
        coordDBModel.setLat(weather.getCityBusinessModel().getCoordBusinessModel().getLat());
        coordDBModel.setLon(weather.getCityBusinessModel().getCoordBusinessModel().getLon());
        CityDBModel cityDBModel = new CityDBModel();
        cityDBModel.setName(weather.getCityBusinessModel().getName());
        cityDBModel.setCountry(weather.getCityBusinessModel().getCountry());
        cityDBModel.setId(weather.getCityBusinessModel().getId());
        cityDBModel.setCoordDBModel(coordDBModel);
        weatherDBModel.setCityDBModel(cityDBModel);


        List<WeatherListDBModel> weatherListDBModel = new ArrayList<>();
        for(int i=0; i<weather.getWeatherListBusinessModel().size(); i++){

            WeatherListDBModel dbModel = new WeatherListDBModel();
            dbModel.setDt(weather.getWeatherListBusinessModel().get(i).getDt());

            MainDBModel mainDBModel = new MainDBModel();
            mainDBModel.setHumidity(weather.getWeatherListBusinessModel().get(i).getMainBusinessModel().getHumidity());
            mainDBModel.setTemp(weather.getWeatherListBusinessModel().get(i).getMainBusinessModel().getTemp());
            mainDBModel.setTempMax(weather.getWeatherListBusinessModel().get(i).getMainBusinessModel().getTempMax());
            mainDBModel.setTempMin(weather.getWeatherListBusinessModel().get(i).getMainBusinessModel().getTempMin());
            dbModel.setMainDBModel(mainDBModel);

            CloudsDBModel cloudsDBModel = new CloudsDBModel();
            cloudsDBModel.setAll(weather.getWeatherListBusinessModel().get(i).getCloudsBusinessModel().getAll());
            dbModel.setCloudsDBModel(cloudsDBModel);

            WindDBModel windDBModel = new WindDBModel();
            windDBModel.setDeg(weather.getWeatherListBusinessModel().get(i).getWindBusinessModel().getDeg());
            windDBModel.setSpeed(weather.getWeatherListBusinessModel().get(i).getWindBusinessModel().getSpeed());
            dbModel.setWindDBModel(windDBModel);

            SysDBModel sysDBModel = new SysDBModel();
            sysDBModel.setPod(weather.getWeatherListBusinessModel().get(i).getSysBusinessModel().getPod());
            dbModel.setSysDBModel(sysDBModel);

            List<WeatherInfoDBModel> weatherInfoDBModelList = new ArrayList<>();
            WeatherInfoDBModel infoDBModel = new WeatherInfoDBModel();
            infoDBModel.setIcon(weather.getWeatherListBusinessModel().get(i).getWeatherInfoBusinessModel().get(0).getIcon());
            infoDBModel.setDescription(weather.getWeatherListBusinessModel().get(i).getWeatherInfoBusinessModel().get(0).getDescription());
            infoDBModel.setId(weather.getWeatherListBusinessModel().get(i).getWeatherInfoBusinessModel().get(0).getId());
            infoDBModel.setMain(weather.getWeatherListBusinessModel().get(i).getWeatherInfoBusinessModel().get(0).getMain());
            weatherInfoDBModelList.add(infoDBModel);

            dbModel.setWeatherInfoDBModel(weatherInfoDBModelList);
            weatherListDBModel.add(dbModel);
        }

        weatherDBModel.setWeatherListDBModel(weatherListDBModel);
        weatherDBModel.setCod(weather.getCod());


        ContentValues[] weatherValues = WeatherForecastUtils
                .getWeatherForecastContentValuesFromJson(weatherListDBModel,
                        weatherDBModel.getCityDBModel().getId());

        ContentValues cityContentValue = WeatherForecastUtils
                .getCityContentValues(weatherDBModel.getCityDBModel());

        weatherForecastDBHelper = new WeatherForecastDBHelper(ctx);

        //check city name unique or not
        isCityAlreadyPresent = WeatherForecastUtils.checkCityAlreadyPresentInDB(weather.getCityBusinessModel().getId(), weatherForecastDBHelper);
        if(isCityAlreadyPresent){
            //insert only weather, no city will be added into city table
            //check duplicate entry in weather table and insert
            List<String> availableDateAndTime = getAlreadyPresentDatesFromDB(String.valueOf(weatherValues[0].get(WEATHER_COLUMN_WEATHER_OF_CITY_ID)), weatherForecastDBHelper);
            for(ContentValues cv : weatherValues){
                String date = String.valueOf(cv.get(WeatherEntry.WEATHER_COLUMN_DATE));
                isWeatherDetailsAlreadyPresent= availableDateAndTime.contains(date);
                if(!isWeatherDetailsAlreadyPresent){
                    int rowsInserted = 0;
                    SQLiteDatabase database = weatherForecastDBHelper.getWritableDatabase();
                    database.beginTransaction();
                    long city_id = database.insert(WeatherEntry.WEATHER_TABLE_NAME,
                            null,
                            cv);
                    if(city_id != -1){
                        rowsInserted++;
                        Log.d("#", "row "+rowsInserted);
                    }

                    database.setTransactionSuccessful();
                    database.endTransaction();
                }
            }
            isCityAlreadyPresent = false;
            isWeatherDetailsAlreadyPresent = false;

        }else {
            //insert city details in city table and add weather data into weather table
            insertCityIntoTable(cityContentValue, weatherForecastDBHelper);
            insertWeatherForecastInfoIntoTable(weatherValues, weatherForecastDBHelper);

        }
    }

    public static void retrieveWeatherForecastInfo(String location) {
        String[] loc = location.split(",");
        long cityId = 0;
        //find id for the location from DB
        SQLiteDatabase database = weatherForecastDBHelper.getReadableDatabase();
        String qry = "SELECT "+ WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID+" FROM "
                + WeatherForecastContract.CityEntry.CITY_TABLE_NAME+" where "
                + WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_NAME+" = \""+loc[0].trim()+"\" COLLATE NOCASE";
        Cursor cursor = database.rawQuery(qry, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return;
        }
        if(cursor.moveToFirst()){
            cityId = Long.parseLong(cursor.getString(cursor.getColumnIndex("id")));
            Log.d("#","id "+cityId);
        }
        cursor.close();

        List<WeatherListDBModel> weatherDetailsFromToday = WeatherForecastUtils.getWeatherDetailsFromToday(cityId, weatherForecastDBHelper);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        for(WeatherListDBModel weatherListDBModel: weatherDetailsFromToday){
            Date date = new Date(weatherListDBModel.getDt()*1000L);

            Log.d("#", loc[0]+ " on "+sdf.format(date)+" | max temp "+weatherListDBModel.getMainDBModel().getTempMax());
        }
    }
}

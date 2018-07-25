package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherForecastDBDao {

    private static WeatherForecastDBHelper weatherForecastDBHelper;
    private static boolean isCityAlreadyPresent = false;

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


        ContentValues[] weatherValues = WeatherForecastJsonUtils
                .getWeatherForecastContentValuesFromJson(weatherListDBModel,
                        weatherDBModel.getCityDBModel().getId());

        ContentValues cityContentValue = WeatherForecastJsonUtils
                .getCityContentValues(weatherDBModel.getCityDBModel());

        weatherForecastDBHelper = new WeatherForecastDBHelper(ctx);

        //check city name unique or not
        isCityAlreadyPresent = checkCityAlreadyPresentInDB(weather.getCityBusinessModel().getId(), weatherForecastDBHelper);
        if(isCityAlreadyPresent){
            //insert only weather, no city will be added into city table
            //check duplicate entry in weather table
        }else {
            //insert city details in city table and add weather data into weather table
            insertCityIntoTable(cityContentValue);
            insertWeatherForecastInfoIntoTable(weatherValues);

        }
    }
    private static boolean checkCityAlreadyPresentInDB(long id, WeatherForecastDBHelper weatherForecastDBHelper) {
        SQLiteDatabase database = weatherForecastDBHelper.getReadableDatabase();
        String qry = "SELECT * FROM "+WeatherForecastContract.CityEntry.CITY_TABLE_NAME+" where "+WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID+" = "+id+"";
        Cursor cursor = database.rawQuery(qry, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    private static void insertCityIntoTable(ContentValues cityContentValue) {
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

    private static void insertWeatherForecastInfoIntoTable(ContentValues[] weatherContentVlues){
        int rowsInserted = 0;
        SQLiteDatabase database = weatherForecastDBHelper.getWritableDatabase();
        database.beginTransaction();

        for(ContentValues cv: weatherContentVlues){
            long id = database.insert(WeatherForecastContract.WeatherEntry.WEATHER_TABLE_NAME,
                    null,
                    cv);
            if(id != -1){
                rowsInserted++;
                Log.d("#", "row "+rowsInserted);
            }
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

}

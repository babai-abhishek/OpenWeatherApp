package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry;
import com.example.abhishek.weatherforecast.Utils;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherInfoBusinessModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCloudsDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCoordDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherMainDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherSysDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherWindDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.CityDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.CloudsDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.CoordDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.MainDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.SysDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherListDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WindDBModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.abhishek.weatherforecast.Utils.getAlreadyPresentDatesFromDB;


/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherDBDao {

    private static WeatherDBHelper weatherDBHelper;
    private static boolean isTimeAlreadyPresent = false;

    public static void insertForecastData(WeatherBusinessModel weather, Context ctx) {

        //convert business model class into db model class
        WeatherDBModel weatherDBModel = new WeatherDBModel();

        CityDBModel cityDBModel = new CityDBModel();
        CoordDBModel coordDBModel = new CoordDBModel();
        coordDBModel.setLat(weather.getCityBusinessModel().getCoordBusinessModel().getLat());
        coordDBModel.setLon(weather.getCityBusinessModel().getCoordBusinessModel().getLon());
        cityDBModel.setName(weather.getCityBusinessModel().getName());
        cityDBModel.setCountry(weather.getCityBusinessModel().getCountry());
        cityDBModel.setId(weather.getCityBusinessModel().getId());
        cityDBModel.setCoordDBModel(coordDBModel);

        //SET CITYDB MODEL IN WEATHERDBMODEL
        weatherDBModel.setCityDBModel(cityDBModel);


        List<WeatherListDBModel> weatherListDBModel = new ArrayList<>();
        for (int i = 0; i < weather.getWeatherListBusinessModel().size(); i++) {

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

        //SET WEATHERLISTDBMODEL IN WEATHERDBMODEL
        weatherDBModel.setWeatherListDBModel(weatherListDBModel);

        ContentValues[] weatherForecastContentValues = Utils
                .getWeatherForecastContentValuesFromJson(weatherDBModel);

        weatherDBHelper = new WeatherDBHelper(ctx);

        //GET AVAILABLE DATE VALUES OF THE RESPECTIVE CITY
        List<String> availableDateAndTime = getAlreadyPresentDatesFromDB(String.valueOf(weather.getCityBusinessModel().getId()), weatherDBHelper);

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

    public static void insertCurrentWeatherIntoDB(CurrentWeatherBusinessModel currentWeatherBusinessModel, Context ctx) {

        //CONVERT BUSINESS MODEL CLASS TO DB MODEL CLASS
        CurrentWeatherDBModel currentWeatherDBModel = new CurrentWeatherDBModel();

        currentWeatherDBModel.setName(currentWeatherBusinessModel.getName());
        currentWeatherDBModel.setId(currentWeatherBusinessModel.getId());
        currentWeatherDBModel.setDt(currentWeatherBusinessModel.getDt());

        CurrentWeatherCoordDBModel coordDBModel = new CurrentWeatherCoordDBModel();
        coordDBModel.setLat(currentWeatherBusinessModel.getCurrentWeatherCoordBusinessModel().getLat());
        coordDBModel.setLon(currentWeatherBusinessModel.getCurrentWeatherCoordBusinessModel().getLon());
        currentWeatherDBModel.setCurrentWeatherCoordDBModel(coordDBModel);

        CurrentWeatherMainDBModel mainDBModel = new CurrentWeatherMainDBModel();
        mainDBModel.setHumidity(currentWeatherBusinessModel.getCurrentWeatherMainBusinessModel().getHumidity());
        mainDBModel.setTempMax(currentWeatherBusinessModel.getCurrentWeatherMainBusinessModel().getTempMax());
        mainDBModel.setTempMin(currentWeatherBusinessModel.getCurrentWeatherMainBusinessModel().getTempMin());
        currentWeatherDBModel.setCurrentWeatherMainDBModel(mainDBModel);

        CurrentWeatherWindDBModel windDBModel = new CurrentWeatherWindDBModel();
        windDBModel.setSpeed(currentWeatherBusinessModel.getCurrentWeatherWindBusinessModel().getSpeed());
        currentWeatherDBModel.setCurrentWeatherWindDBModel(windDBModel);

        CurrentWeatherSysDBModel sysDBModel = new CurrentWeatherSysDBModel();
        sysDBModel.setCountry(currentWeatherBusinessModel.getCurrentWeatherSysBusinessModel().getCountry());
        currentWeatherDBModel.setCurrentWeatherSysDBModel(sysDBModel);

        CurrentWeatherCloudsDBModel cloudsDBModel = new CurrentWeatherCloudsDBModel();
        cloudsDBModel.setAll(currentWeatherBusinessModel.getCurrentWeatherCloudsBusinessModel().getAll());
        currentWeatherDBModel.setCurrentWeatherCloudsDBModel(cloudsDBModel);

        List<CurrentWeatherInfoDBModel> weatherInfoDBModelList = new ArrayList<>();
        for(CurrentWeatherInfoBusinessModel infoBusinessModel: currentWeatherBusinessModel.getCurrentWeatherInfoBusinessModel()){
            CurrentWeatherInfoDBModel infoDBModel = new CurrentWeatherInfoDBModel();
            infoDBModel.setDescription(infoBusinessModel.getDescription());
            infoDBModel.setIcon(infoBusinessModel.getIcon());
            infoDBModel.setWeatherId(infoBusinessModel.getWeatherId());
            weatherInfoDBModelList.add(infoDBModel);
        }
        currentWeatherDBModel.setCurrentWeatherInfoDBModel(weatherInfoDBModelList);

        ContentValues currentWeatherContentValue = Utils
                .getCurrentWeatherContentValueFromJson(currentWeatherDBModel);

        weatherDBHelper = new WeatherDBHelper(ctx);

        long dt = 0;

        //GET DATE VALUE PRESENT IN TABLE FOR PARTICULAR CITY
        String qry = "SELECT "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE+" " +
                "FROM "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME+"" +
                " WHERE "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID+" = \""+currentWeatherBusinessModel.getId()+"\"";
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

            if(String.valueOf(currentWeatherBusinessModel.getDt()).equals(String.valueOf(dt))){
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

}


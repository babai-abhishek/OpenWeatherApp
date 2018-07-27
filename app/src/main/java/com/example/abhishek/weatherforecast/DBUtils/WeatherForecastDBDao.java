package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abhishek.weatherforecast.DBUtils.WeatherForecastContract.WeatherForecastEntry;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.abhishek.weatherforecast.DBUtils.WeatherForecastUtils.getAlreadyPresentDatesFromDB;


/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherForecastDBDao {

    private static WeatherForecastDBHelper weatherForecastDBHelper;
    private static boolean isTimeAlreadyPresent = false;

    public static void insertData(WeatherBusinessModel weather, Context ctx){

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

        //SET WEATHERLISTDBMODEL IN WEATHERDBMODEL
        weatherDBModel.setWeatherListDBModel(weatherListDBModel);

        ContentValues[] weatherForecastContentValues = WeatherForecastUtils
                .getWeatherForecastContentValuesFromJson(weatherDBModel);


        /*ContentValues cityContentValue = WeatherForecastUtils
                .getCityContentValues(weatherDBModel.getCityDBModel());*/

        weatherForecastDBHelper = new WeatherForecastDBHelper(ctx);

        //GET AVAILABLE DATE VALUES OF THE RESPECTIVE CITY
        List<String> availableDateAndTime = getAlreadyPresentDatesFromDB(String.valueOf(weather.getCityBusinessModel().getId()), weatherForecastDBHelper);

        //CHECK WHETHER WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE VALUE
        //MATHCES WITH NEW INCOMING DATE VALUE
        for(ContentValues weathervalue : weatherForecastContentValues){
            String date = String.valueOf(weathervalue.get(WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE));

            //CHECK ALREADY DATE PRESENT OR NOT
            isTimeAlreadyPresent = availableDateAndTime.contains(date);

            //IF UNIQUE THEN INSERT
            if(!isTimeAlreadyPresent){
                //INSERT UNIQUE DATE
                SQLiteDatabase database = weatherForecastDBHelper.getWritableDatabase();
                database.beginTransaction();
                int rowsInserted =0;

                    long id = database.insert(WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME,
                            null,
                            weathervalue);
                    if(id != -1){
                        rowsInserted++;
                        Log.d("#", "row "+rowsInserted);

                }

                database.setTransactionSuccessful();
                database.endTransaction();

            }

        }

    }















































      /*  //check city name unique or not
        isCityAlreadyPresent = WeatherForecastUtils.checkCityAlreadyPresentInDB(weather.getCityBusinessModel().getId(), weatherForecastDBHelper);
        if(isCityAlreadyPresent){
            //insert only weather, no city will be added into city table
            //check duplicate entry in weather table and insert
            List<String> availableDateAndTime = getAlreadyPresentDatesFromDB(String.valueOf(weatherValues[0].get(WEATHER_COLUMN_WEATHER_OF_CITY_ID)), weatherForecastDBHelper);
            for(ContentValues cv : weatherValues){
                String date = String.valueOf(cv.get(WeatherForecastEntry.WEATHER_COLUMN_DATE));
                isWeatherDetailsAlreadyPresent= availableDateAndTime.contains(date);
                if(!isWeatherDetailsAlreadyPresent){
                    int rowsInserted = 0;
                    SQLiteDatabase database = weatherForecastDBHelper.getWritableDatabase();
                    database.beginTransaction();
                    long city_id = database.insert(WeatherForecastEntry.WEATHER_TABLE_NAME,
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

        }*/
}

//    public static void retrieveWeatherForecastInfo(String location) {
//        String[] loc = location.split(",");
//        long cityId = 0;
//        //find id for the location from DB
//        SQLiteDatabase database = weatherForecastDBHelper.getReadableDatabase();
//        String qry = "SELECT "+ WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_ID+" FROM "
//                + WeatherForecastContract.CityEntry.CITY_TABLE_NAME+" where "
//                + WeatherForecastContract.CityEntry.CITY_TABLE_COLUMN_CITY_NAME+" = \""+loc[0].trim()+"\" COLLATE NOCASE";
//        Cursor cursor = database.rawQuery(qry, null);
//        if(cursor.getCount() <= 0){
//            cursor.close();
//            return;
//        }
//        if(cursor.moveToFirst()){
//            cityId = Long.parseLong(cursor.getString(cursor.getColumnIndex("id")));
//            Log.d("#","id "+cityId);
//        }
//        cursor.close();
//
//        List<WeatherListDBModel> weatherDetailsFromToday = WeatherForecastUtils.getWeatherDetailsFromToday(cityId, weatherForecastDBHelper);
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//
//        for(WeatherListDBModel weatherListDBModel: weatherDetailsFromToday){
//            Date date = new Date(weatherListDBModel.getDt()*1000L);
//
//            Log.d("#", loc[0]+ " on "+sdf.format(date)+" | max temp "+weatherListDBModel.getMainDBModel().getTempMax());
//        }
//    }


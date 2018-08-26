package com.example.abhishek.weatherforecast;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract;
import com.example.abhishek.weatherforecast.DBUtils.WeatherDBHelper;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCloudsDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCoordDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherMainDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherSysDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherWindDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.CloudsDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.MainDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.SysDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherListDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WindDBModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CITY_NAME;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_COUNTRY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DESCRIPTION;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_HUMIDITY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_ICON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LAT;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MAX_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MIN_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_CONDITION_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WIND_SPEED;

/**
 * Created by abhishek on 19/7/18.
 */

public class Utils {

    public static final int REQUEST_CODE = 1001;

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
            weatherContentValue.put(WEATHER_FORECAST_TABLE_COLUMN_WEATHER_CONDITION_ID, weather.getWeatherListDBModel().get(i).getWeatherInfoDBModel().get(0).getId());
            weatherContentValues[i] = weatherContentValue;
        }

        return weatherContentValues;
    }

    public static List<String> getAlreadyPresentDatesFromDB(String cityId, WeatherDBHelper weatherDBHelper){
        List<String> dates = new ArrayList<>();
        SQLiteDatabase database = weatherDBHelper.getReadableDatabase();
        String qry = "SELECT "+WEATHER_FORECAST_TABLE_COLUMN_DATE+" FROM "+
                WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME+" where "
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

    public static ContentValues getCurrentWeatherContentValueFromJson(CurrentWeatherDBModel currentWeatherDBModel) {
        ContentValues cv = new ContentValues();
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME, currentWeatherDBModel.getName());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE, currentWeatherDBModel.getDt());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID, currentWeatherDBModel.getId());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE, currentWeatherDBModel.getCurrentWeatherCloudsDBModel().getAll());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_COUNTRY, currentWeatherDBModel.getCurrentWeatherSysDBModel().getCountry());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LAT, currentWeatherDBModel.getCurrentWeatherCoordDBModel().getLat());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LON, currentWeatherDBModel.getCurrentWeatherCoordDBModel().getLon());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_HUMIDITY, currentWeatherDBModel.getCurrentWeatherMainDBModel().getHumidity());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MAX_TEMP, currentWeatherDBModel.getCurrentWeatherMainDBModel().getTempMax());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MIN_TEMP, currentWeatherDBModel.getCurrentWeatherMainDBModel().getTempMin());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WIND_SPEED, currentWeatherDBModel.getCurrentWeatherWindDBModel().getSpeed());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DESCRIPTION, currentWeatherDBModel.getCurrentWeatherInfoDBModel().get(0).getDescription());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_ICON, currentWeatherDBModel.getCurrentWeatherInfoDBModel().get(0).getIcon());
        cv.put(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_CONDITION_ID, currentWeatherDBModel.getCurrentWeatherInfoDBModel().get(0).getWeatherId());
        return cv;
    }

    public static List<WeatherListApiModel> getWeatherForecastListFromTomorrow(WeatherApiModel weather) {

        List<WeatherListApiModel> weatherListApiModelList = weather.getWeatherListApiModel();

        List<WeatherListApiModel> singleWeatherApiModelPerDay = new ArrayList<>();

        //GET TODAY'S DATE
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        //GET TOMORROW'S DATE
        Date tomorrow = getNextDate(currentDate);

        for(WeatherListApiModel apiModel: weatherListApiModelList){
            if(sdf.format(tomorrow).equals(sdf.format(new Date(apiModel.getDt()*1000L)))){
                singleWeatherApiModelPerDay.add(apiModel);
                tomorrow = getNextDate(tomorrow);
            }
        }
        return singleWeatherApiModelPerDay;
    }

    public static int getLargeArtResourceIdForWeatherCondition(int weatherId) {

        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.art_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.art_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.art_rain;
        } else if (weatherId == 511) {
            return R.drawable.art_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.art_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.art_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.art_fog;
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.art_storm;
        } else if (weatherId == 800) {
            return R.drawable.art_clear;
        } else if (weatherId == 801) {
            return R.drawable.art_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.art_clouds;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.art_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.art_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.art_clear;
        }

        return R.drawable.art_storm;
    }

    public static int getSmallArtResourceIdForWeatherCondition(int weatherId) {

        /*
         * Based on weather code data for Open Weather Map.
         */
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_light_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 761 || weatherId == 771 || weatherId == 781) {
            return R.drawable.ic_storm;
        } else if (weatherId == 800) {
            return R.drawable.ic_clear;
        } else if (weatherId == 801) {
            return R.drawable.ic_light_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_cloudy;
        } else if (weatherId >= 900 && weatherId <= 906) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 958 && weatherId <= 962) {
            return R.drawable.ic_storm;
        } else if (weatherId >= 951 && weatherId <= 957) {
            return R.drawable.ic_clear;
        }

        return R.drawable.ic_storm;
    }

    public static String formatTemperature(Context context, double temperature) {
        int temperatureFormatResourceId = R.string.format_temperature_celsius;

       /* if (!SunshinePreferences.isMetric(context)) {
            temperature = celsiusToFahrenheit(temperature);
            temperatureFormatResourceId = R.string.format_temperature_fahrenheit;
        }*/

       /* temperature = celsiusToFahrenheit(temperature);
        temperatureFormatResourceId = R.string.format_temperature_fahrenheit;*/

        //DEFAULT : KELVIN ; CURRENTLY CONVERT IN CELCIUS/METRIC
        double celcius = temperature-273.15;

        return String.format(context.getString(temperatureFormatResourceId), celcius);
    }

    public static String getFormattedWind(Context context, float windSpeed, float degrees) {

        int windFormat = R.string.format_wind_kmh;

        //  if (!SunshinePreferences.isMetric(context)) {
        windFormat = R.string.format_wind_mph;
        windSpeed = .621371192237334f * windSpeed;
        //  }

        String direction = "Unknown";
        if (degrees >= 337.5 || degrees < 22.5) {
            direction = "N";
        } else if (degrees >= 22.5 && degrees < 67.5) {
            direction = "NE";
        } else if (degrees >= 67.5 && degrees < 112.5) {
            direction = "E";
        } else if (degrees >= 112.5 && degrees < 157.5) {
            direction = "SE";
        } else if (degrees >= 157.5 && degrees < 202.5) {
            direction = "S";
        } else if (degrees >= 202.5 && degrees < 247.5) {
            direction = "SW";
        } else if (degrees >= 247.5 && degrees < 292.5) {
            direction = "W";
        } else if (degrees >= 292.5 && degrees < 337.5) {
            direction = "NW";
        }
        return String.format(context.getString(windFormat), windSpeed, direction);
    }

    public static String getStringForWeatherCondition(Context context, int weatherId) {
        int stringId;
        if (weatherId >= 200 && weatherId <= 232) {
            stringId = R.string.condition_2xx;
        } else if (weatherId >= 300 && weatherId <= 321) {
            stringId = R.string.condition_3xx;
        } else switch (weatherId) {
            case 500:
                stringId = R.string.condition_500;
                break;
            case 501:
                stringId = R.string.condition_501;
                break;
            case 502:
                stringId = R.string.condition_502;
                break;
            case 503:
                stringId = R.string.condition_503;
                break;
            case 504:
                stringId = R.string.condition_504;
                break;
            case 511:
                stringId = R.string.condition_511;
                break;
            case 520:
                stringId = R.string.condition_520;
                break;
            case 531:
                stringId = R.string.condition_531;
                break;
            case 600:
                stringId = R.string.condition_600;
                break;
            case 601:
                stringId = R.string.condition_601;
                break;
            case 602:
                stringId = R.string.condition_602;
                break;
            case 611:
                stringId = R.string.condition_611;
                break;
            case 612:
                stringId = R.string.condition_612;
                break;
            case 615:
                stringId = R.string.condition_615;
                break;
            case 616:
                stringId = R.string.condition_616;
                break;
            case 620:
                stringId = R.string.condition_620;
                break;
            case 621:
                stringId = R.string.condition_621;
                break;
            case 622:
                stringId = R.string.condition_622;
                break;
            case 701:
                stringId = R.string.condition_701;
                break;
            case 711:
                stringId = R.string.condition_711;
                break;
            case 721:
                stringId = R.string.condition_721;
                break;
            case 731:
                stringId = R.string.condition_731;
                break;
            case 741:
                stringId = R.string.condition_741;
                break;
            case 751:
                stringId = R.string.condition_751;
                break;
            case 761:
                stringId = R.string.condition_761;
                break;
            case 762:
                stringId = R.string.condition_762;
                break;
            case 771:
                stringId = R.string.condition_771;
                break;
            case 781:
                stringId = R.string.condition_781;
                break;
            case 800:
                stringId = R.string.condition_800;
                break;
            case 801:
                stringId = R.string.condition_801;
                break;
            case 802:
                stringId = R.string.condition_802;
                break;
            case 803:
                stringId = R.string.condition_803;
                break;
            case 804:
                stringId = R.string.condition_804;
                break;
            case 900:
                stringId = R.string.condition_900;
                break;
            case 901:
                stringId = R.string.condition_901;
                break;
            case 902:
                stringId = R.string.condition_902;
                break;
            case 903:
                stringId = R.string.condition_903;
                break;
            case 904:
                stringId = R.string.condition_904;
                break;
            case 905:
                stringId = R.string.condition_905;
                break;
            case 906:
                stringId = R.string.condition_906;
                break;
            case 951:
                stringId = R.string.condition_951;
                break;
            case 952:
                stringId = R.string.condition_952;
                break;
            case 953:
                stringId = R.string.condition_953;
                break;
            case 954:
                stringId = R.string.condition_954;
                break;
            case 955:
                stringId = R.string.condition_955;
                break;
            case 956:
                stringId = R.string.condition_956;
                break;
            case 957:
                stringId = R.string.condition_957;
                break;
            case 958:
                stringId = R.string.condition_958;
                break;
            case 959:
                stringId = R.string.condition_959;
                break;
            case 960:
                stringId = R.string.condition_960;
                break;
            case 961:
                stringId = R.string.condition_961;
                break;
            case 962:
                stringId = R.string.condition_962;
                break;
            default:
                return context.getString(R.string.condition_unknown, weatherId);
        }
        return context.getString(stringId);
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

    public static String getDateString(Context mContext, long dateInMillis) {
        Date dt = new Date(dateInMillis*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        //GET CURRENT DATE
        Date currentDate = new Date();
        String curDate = sdf.format(currentDate);

        if(curDate.equals(sdf.format(dt))){
            return mContext.getString(R.string.today);
        }else if(sdf.format(getNextDate(currentDate)).equals(sdf.format(dt))){
            return mContext.getString(R.string.tomorrow);
        }

        return new SimpleDateFormat("EEEE").format(new Date(dateInMillis*1000L));
    }

    private static Date getNextDate(Date curDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
    }

    public static boolean checkInternetConnetion(Context ctx) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public static List<IWeatherDetails> checkCurrentDataForCity(String location, Context ctx) {
        List<IWeatherDetails> weatherInfo = new ArrayList<>();
        String[] cityWithCountry = location.split(",");
        String city = formantCity(cityWithCountry[0].trim());
        String sqry = "SELECT * FROM "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_NAME
                +" WHERE "+ WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME
                +" = \""+city+"\"";
        SQLiteDatabase db = new WeatherDBHelper(ctx).getReadableDatabase();
        Cursor cursor = db.rawQuery(sqry,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            CurrentWeatherDBModel currentWeatherDBModel = new CurrentWeatherDBModel();
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
            weatherInfo.add(new CurrentWeatherBusinessModel(currentWeatherDBModel));

            List<WeatherListBusinessModel> weatherForecastList = getAvailAbleForecast(cursor.getInt(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID)),
                    cursor.getLong(cursor.getColumnIndex(WeatherDBContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE)),
                    ctx);
            for(WeatherListBusinessModel businessModel: weatherForecastList){
                weatherInfo.add(businessModel);
            }
        }
        return weatherInfo;
    }

    private static List<WeatherListBusinessModel> getAvailAbleForecast(int cityid, long dateTime, Context ctx) {
        List<WeatherListBusinessModel> listOfSingleWeatherInfoPerDay = new ArrayList<>();
        List<WeatherListDBModel> listOfDbModels = new ArrayList<>();

        String qry = "SELECT * FROM forecastweather WHERE _city_id=\""+cityid+"\" AND date>\""+dateTime+"\"";
        SQLiteDatabase db = new WeatherDBHelper(ctx).getReadableDatabase();
        Cursor cursor = db.rawQuery(qry,null);
        if (cursor.moveToFirst()){
            do{
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
            }while(cursor.moveToNext());
        }
        cursor.close();
        List<WeatherListBusinessModel> businessModels = new ArrayList<>();
        for(WeatherListDBModel dbModel: listOfDbModels){
            businessModels.add(new WeatherListBusinessModel(dbModel));
        }

        //GET TODAY'S DATE
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        //GET TOMORROW'S DATE
        Date tomorrow = getNextDate(currentDate);

        for(WeatherListBusinessModel businessModel: businessModels){
            if(sdf.format(tomorrow).equals(sdf.format(new Date(businessModel.getDt()*1000L)))){
                listOfSingleWeatherInfoPerDay.add(businessModel);
                tomorrow = getNextDate(tomorrow);
            }
        }

        return listOfSingleWeatherInfoPerDay;
    }

    private static String formantCity(String city) {
        StringBuilder citySb = new StringBuilder();
        citySb.append(city.substring(0, 1).toUpperCase());
        citySb.append(city.substring(1, city.length()).toLowerCase());
        return String.valueOf(citySb);
}

    public static void restartSyncWeatherData(Context context){
        syncCurrentWeatherData(context);
    }

    public static void syncCurrentWeatherData(Context context) {
        setAlarm(context);
    }

    private static void setAlarm(Context context) {
        long interval = 10 * 1000;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, CurrentWeatherSyncService.class);
        PendingIntent alarmIntent = PendingIntent.getService(context, REQUEST_CODE, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC,
                System.currentTimeMillis(),
                interval,
                alarmIntent);
    }

    public static boolean isAlreadyDataPresentInDB(CurrentWeatherBusinessModel cwBusinessModel) {
        return false;
    }

    public static class NotificationUtils {

        private static final int CURRENT_WEATHER_NOTIFICATION_ID = 1138;
        private static final int CURRENT_WEATHER_PENDING_INTENT_ID = 1097;
        private static final String CURRENT_WEATHER_NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";

        public static void showNotificationBecauseNetworkUnAvailAble(Context context) {
            setNotification(context,context.getString(R.string.charging_reminder_notification_title),
                    context.getString(R.string.charging_reminder_notification_body),
                    R.drawable.ic_no_internet);
        }


        private static void setNotification(Context context, String notificationTitle, String notificationText, int iconId){
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel(
                        CURRENT_WEATHER_NOTIFICATION_CHANNEL_ID,
                        context.getString(R.string.main_notification_channel_name),
                        NotificationManager.IMPORTANCE_HIGH
                );
                notificationManager.createNotificationChannel(channel);
            }

            NotificationCompat.Builder builder = new NotificationCompat
                    .Builder(context, CURRENT_WEATHER_NOTIFICATION_CHANNEL_ID)
                    .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    .setSmallIcon(iconId)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationText)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(notificationText))
                    .setContentIntent(contentIntent(context))
                    .setAutoCancel(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                    && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                builder.setPriority(NotificationCompat.PRIORITY_HIGH);
            }
            Log.d("#", "current time "+String.valueOf(System.currentTimeMillis()));
            notificationManager.notify(CURRENT_WEATHER_NOTIFICATION_ID, builder.build());
        }

        private static PendingIntent contentIntent(Context context){
            Intent startActivityIntent = new Intent(context, MainActivity.class);

            return PendingIntent.getActivity(
                    context,
                    CURRENT_WEATHER_PENDING_INTENT_ID,
                    startActivityIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );
        }

        public static void showUpdatedData(Context context, CurrentWeatherBusinessModel cwBusinessModel) {
            setNotification(context, "Weather Updated",
                    "Current weather has changed. Open your app for details.", R.drawable.art_clear);
        }
    }

}

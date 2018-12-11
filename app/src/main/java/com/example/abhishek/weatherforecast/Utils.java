package com.example.abhishek.weatherforecast;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import com.example.abhishek.weatherforecast.DBUtils.WeatherDBContract;
import com.example.abhishek.weatherforecast.DBUtils.WeatherDBDao;
import com.example.abhishek.weatherforecast.DBUtils.WeatherDBHelper;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherInfoBusinessModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherMainBusinessModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCloudsDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherCoordDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherInfoDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherMainDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherSysDBModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherWindDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;
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

    public static ContentValues[] convertIntoContentValues(WeatherDBModel weather) {

        ContentValues[] weatherContentValues = new ContentValues[weather.getWeatherListDBModel().size()];

        for (int i = 0; i < weather.getWeatherListDBModel().size(); i++) {

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

    public static ContentValues convertIntoContentValues(CurrentWeatherDBModel currentWeatherDBModel) {
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

        for (WeatherListApiModel apiModel : weatherListApiModelList) {
            if (sdf.format(tomorrow).equals(sdf.format(new Date(apiModel.getDt() * 1000L)))) {
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
        double celcius = temperature - 273.15;

        return String.format(context.getString(temperatureFormatResourceId), celcius);
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

    public static String getDateString(Context mContext, long dateInMillis) {
        Date dt = new Date(dateInMillis * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        //GET CURRENT DATE
        Date currentDate = new Date();
        String curDate = sdf.format(currentDate);

        if (curDate.equals(sdf.format(dt))) {
            return mContext.getString(R.string.today);
        } else if (sdf.format(getNextDate(currentDate)).equals(sdf.format(dt))) {
            return mContext.getString(R.string.tomorrow);
        }

        return new SimpleDateFormat("EEEE").format(new Date(dateInMillis * 1000L));
    }

    private static Date getNextDate(Date curDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
    }

    public static List<IWeatherDetails> checkCurrentDataForCity(String location, Context ctx) {

        List<IWeatherDetails> weatherInfo = new ArrayList<>();

        CurrentWeatherDBModel currentWeatherDBModel =
                getAvailableCurrentWeatherForLocationFromDB(location, ctx);

        //CHECK ANY DATA AVAILABLE IN DB
        if (currentWeatherDBModel != null) {

            //GET TODAY'S DATE
            Date currentDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            //CHECK CURRENTWEATHERDATA IS FROM CURRENT DAY OR NOT
            boolean isCurrentDataIsFromCurrentDay = sdf.format(currentDate)
                    .equals(sdf.format(new Date(currentWeatherDBModel.getDt() * 1000L)));

            if (isCurrentDataIsFromCurrentDay) {

                weatherInfo.add(new CurrentWeatherBusinessModel(currentWeatherDBModel));
                List<WeatherListBusinessModel> weatherForecastList =
                        getAvailAbleForecastWeatherExcludingToday(currentWeatherDBModel.getId(),
                                currentWeatherDBModel.getDt(), ctx);
                for (WeatherListBusinessModel businessModel : weatherForecastList) {
                    weatherInfo.add(businessModel);
                }

            } else {
                List<WeatherListBusinessModel> weatherForecastList =
                        getAvailAbleForecastWeatherIncludingToday(currentWeatherDBModel.getId(), ctx);

                //GET TODAY'S DATE
                Date today = new Date();
                SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

                if(weatherForecastList.size()>0) {

                    WeatherListBusinessModel mListBusinessModel = weatherForecastList.get(0);
                    if (mSimpleDateFormat.format(today).equals(mSimpleDateFormat.format(new Date(mListBusinessModel.getDt() * 1000L)))) {
                        CurrentWeatherBusinessModel mCurrentWeatherBusinessModel = setForecastWeatherToCurrentWeather(mListBusinessModel);
                        weatherForecastList.remove(0);
                        weatherInfo.add(0, mCurrentWeatherBusinessModel);
                    }
                    for (WeatherListBusinessModel businessModel : weatherForecastList) {
                        weatherInfo.add(businessModel);
                    }
                }

            }
        }
        return weatherInfo;
    }

    private static List<WeatherListBusinessModel> getAvailAbleForecastWeatherIncludingToday(int cityid, Context ctx) {
        List<WeatherListBusinessModel> listOfSingleWeatherInfoPerDay = new ArrayList<>();
        List<WeatherListDBModel> listOfDbModels = WeatherDBDao.getForecastWeather(cityid, ctx);
        List<WeatherListBusinessModel> businessModels = new ArrayList<>();
        for (WeatherListDBModel dbModel : listOfDbModels) {
            businessModels.add(new WeatherListBusinessModel(dbModel));
        }

        //GET TODAY'S DATE
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        for (WeatherListBusinessModel businessModel : businessModels) {
            if (sdf.format(currentDate).equals(sdf.format(new Date(businessModel.getDt() * 1000L)))) {
                listOfSingleWeatherInfoPerDay.add(businessModel);
                currentDate = getNextDate(currentDate);
            }
        }

        return listOfSingleWeatherInfoPerDay;
    }

    private static CurrentWeatherBusinessModel setForecastWeatherToCurrentWeather(WeatherListBusinessModel listBusinessModel) {
        CurrentWeatherBusinessModel mCurrentWeatherBusinessModel = new CurrentWeatherBusinessModel();
        mCurrentWeatherBusinessModel.setDt((int) listBusinessModel.getDt());

        String description = listBusinessModel.getWeatherInfoBusinessModel().get(0).getDescription();
        int weatherId = listBusinessModel.getWeatherInfoBusinessModel().get(0).getId();
        CurrentWeatherInfoBusinessModel mCurrentWeatherInfoBusinessModel = new CurrentWeatherInfoBusinessModel();
        mCurrentWeatherInfoBusinessModel.setDescription(description);
        mCurrentWeatherInfoBusinessModel.setWeatherId(weatherId);
        List<CurrentWeatherInfoBusinessModel> mCurrentWeatherInfoBusinessModelList = new ArrayList<>();
        mCurrentWeatherInfoBusinessModelList.add(mCurrentWeatherInfoBusinessModel);
        mCurrentWeatherBusinessModel.setCurrentWeatherInfoBusinessModel(mCurrentWeatherInfoBusinessModelList);

        CurrentWeatherMainBusinessModel mCurrentWeatherMainBusinessModel = new CurrentWeatherMainBusinessModel();
        mCurrentWeatherMainBusinessModel.setTempMax(listBusinessModel.getMainBusinessModel().getTempMax());
        mCurrentWeatherMainBusinessModel.setTempMin(listBusinessModel.getMainBusinessModel().getTempMin());
        mCurrentWeatherBusinessModel.setCurrentWeatherMainBusinessModel(mCurrentWeatherMainBusinessModel);
        return mCurrentWeatherBusinessModel;
    }

    public static CurrentWeatherBusinessModel getCurrentWeatherForLocationFromDB(String location, Context ctx) {
        CurrentWeatherDBModel mCurrentWeatherDBModel = getAvailableCurrentWeatherForLocationFromDB(location, ctx);
        CurrentWeatherBusinessModel mCurrentWeatherBusinessModel = new CurrentWeatherBusinessModel(mCurrentWeatherDBModel);
        return  mCurrentWeatherBusinessModel;
    }

    //HELPER METHOD TO GET CURRENTWEATHER FOR THE LOCATION FROM DB
    private static CurrentWeatherDBModel getAvailableCurrentWeatherForLocationFromDB(String location, Context ctx) {
        String[] cityWithCountry = location.split(",");
        String city = Utils.formantCity(cityWithCountry[0].trim());
        CurrentWeatherDBModel mCurrentWeatherDBModel = WeatherDBDao.getCurrentWeather(city, ctx);
        return mCurrentWeatherDBModel;
    }

    public static List<WeatherListBusinessModel> getForecastWeatherForLocationFromDB(int cityid, long dateTime, Context ctx) {
        List<WeatherListBusinessModel> mWeatherListBusinessModelList =
                getAvailAbleForecastWeatherExcludingToday(cityid, dateTime, ctx);
        return mWeatherListBusinessModelList;
    }

    //HELPER METHOD TO GET FORECASTWEATHER FOR THE LOCATION FROM DB AND
    //SEARCH WEATHERINFO FROM TODAY ONWARDS ONLY
    private static List<WeatherListBusinessModel> getAvailAbleForecastWeatherExcludingToday(int cityid, long dateTime, Context ctx){
        List<WeatherListBusinessModel> listOfSingleWeatherInfoPerDay = new ArrayList<>();
        List<WeatherListDBModel> listOfDbModels = WeatherDBDao.getForecastWeather(cityid, dateTime, ctx);
        List<WeatherListBusinessModel> businessModels = new ArrayList<>();
        for (WeatherListDBModel dbModel : listOfDbModels) {
            businessModels.add(new WeatherListBusinessModel(dbModel));
        }

        //GET TODAY'S DATE
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        //GET TOMORROW'S DATE
        Date tomorrow = getNextDate(currentDate);

        for (WeatherListBusinessModel businessModel : businessModels) {
            if (sdf.format(tomorrow).equals(sdf.format(new Date(businessModel.getDt() * 1000L)))) {
                listOfSingleWeatherInfoPerDay.add(businessModel);
                tomorrow = getNextDate(tomorrow);
            }
        }

        return listOfSingleWeatherInfoPerDay;
    }

    public static String formantCity(String city) {
        StringBuilder citySb = new StringBuilder();
        citySb.append(city.substring(0, 1).toUpperCase());
        citySb.append(city.substring(1, city.length()).toLowerCase());
        return String.valueOf(citySb);
    }

    public static void restartSyncWeatherData(Context context) {
        syncCurrentWeatherData(context);
    }

    public static void syncCurrentWeatherData(Context context) {
        setAlarm(context);
    }

    private static void setAlarm(Context context) {
        long interval = 30 * 60 * 1000;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, CurrentWeatherSyncService.class);
        PendingIntent alarmIntent = PendingIntent.getService(context, REQUEST_CODE, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC,
                System.currentTimeMillis()+ interval,
                interval,
                alarmIntent);
    }

    public static boolean isAlreadyCurrentWeatherInfoPresentInDB(CurrentWeatherBusinessModel cwBusinessModel, Context context) {
        CurrentWeatherDBModel mCurrentWeatherDBModel = WeatherDBDao.getCurrentWeather(formantCity(cwBusinessModel.getName() ), context);
        if(mCurrentWeatherDBModel != null){
            if(mCurrentWeatherDBModel.getDt() == cwBusinessModel.getDt()){
                return true;
            }
        }
        return false;
    }

    public static WeatherDBModel convertBusinessModelToDBModel(WeatherBusinessModel weather) {

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

        return weatherDBModel;
    }

    public static CurrentWeatherDBModel convertCurrentWeatherBusinessModelToCurrentWeatherDBModel(CurrentWeatherBusinessModel
                                                                                                          currentWeatherBusinessModel) {
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

        return currentWeatherDBModel;
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
                    "Current weather has changed. Open app for detailed weather information.", R.drawable.art_clear);
        }

        public static void clearNotifications(Context context) {
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();

        }
    }

    public static class SettingsUtils {

        public static String getPreferredLocation(Context context) {
            SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(context);

            String keyForLocation = context.getString(R.string.pref_location_key);
            String defaultLocation = context.getString(R.string.pref_location_default);

            String country = getPreferredCountry(context);

            String loc = sp.getString(keyForLocation, defaultLocation);

            StringBuilder locationWithCountryCode = new StringBuilder();
            locationWithCountryCode.append(loc);
            locationWithCountryCode.append(",");
            locationWithCountryCode.append(country);


            return locationWithCountryCode.toString();

        }

        private static String getPreferredCountry(Context context) {
            SharedPreferences sp = android.preference.PreferenceManager.getDefaultSharedPreferences(context);

            String keyForCountry = context.getString(R.string.pref_country_key);
            String defaultCountry = context.getString(R.string.pref_country_default);

            return sp.getString(keyForCountry, defaultCountry);

        }

        public static boolean isMetric(Context context) {

            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(context);
            String keyForUnits = context.getString(R.string.pref_units_key);
            String defaultUnits = context.getString(R.string.pref_units_metric);
            String preferredUnits = prefs.getString(keyForUnits, defaultUnits);
            String metric = context.getString(R.string.pref_units_metric);
            boolean userPrefersMetric;
            if (metric.equals(preferredUnits)) {
                userPrefersMetric = true;
            } else {
                userPrefersMetric = false;
            }
            return userPrefersMetric;
        }

        public static boolean areNotificationsEnabled(Context context) {

            String displayNotificationsKey = context.getString(R.string.pref_enable_notifications_key);
            boolean shouldDisplayNotificationsByDefault = context
                    .getResources()
                    .getBoolean(R.bool.show_notifications_by_default);
            SharedPreferences sp = android.preference.PreferenceManager
                    .getDefaultSharedPreferences(context);
            boolean shouldDisplayNotifications = sp
                    .getBoolean(displayNotificationsKey, shouldDisplayNotificationsByDefault);

            return shouldDisplayNotifications;
        }

    }

    public static class DBUtils{

        public static List<String> getAlreadyPresentDatesFromDB(String cityId,
                                                                WeatherDBHelper weatherDBHelper) {
            List<String> dates = new ArrayList<>();
            SQLiteDatabase database = weatherDBHelper.getReadableDatabase();
            String qry = "SELECT " + WEATHER_FORECAST_TABLE_COLUMN_DATE + " FROM " +
                    WeatherDBContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME + " where "
                    + WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID + " = " + cityId + "";
            Cursor cursor = database.rawQuery(qry, null);
            if (cursor.getCount() <= 0) {
                cursor.close();
                return dates;
            }
            if (cursor.moveToFirst()) {
                do {
                    dates.add(String.valueOf(cursor.getString(cursor.getColumnIndex(WEATHER_FORECAST_TABLE_COLUMN_DATE))));
                } while (cursor.moveToNext());
            }
            cursor.close();
            return dates;
        }
    }

}

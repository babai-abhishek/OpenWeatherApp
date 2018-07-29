package com.example.abhishek.weatherforecast.DBUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.abhishek.weatherforecast.R;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb.CurrentWeatherDBModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb.WeatherDBModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CITY_NAME;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_COUNTRY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DATE;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_DESCRIPTION;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_HUMIDITY;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_ICON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LAT;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_LON;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MAX_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_MIN_TEMP;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_CONDITION_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WEATHER_OF_CITY_ID;
import static com.example.abhishek.weatherforecast.DBUtils.WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_COLUMN_WIND_SPEED;

/**
 * Created by abhishek on 19/7/18.
 */

public class WeatherUtils {

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
                WeatherContract.WeatherForecastEntry.WEATHER_FORECAST_TABLE_NAME+" where "
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
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CITY_NAME, currentWeatherDBModel.getName());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DATE, currentWeatherDBModel.getDt());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WEATHER_OF_CITY_ID, currentWeatherDBModel.getId());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_CLOUDS_IN_PERCENTAGE, currentWeatherDBModel.getCurrentWeatherCloudsDBModel().getAll());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_COUNTRY, currentWeatherDBModel.getCurrentWeatherSysDBModel().getCountry());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LAT, currentWeatherDBModel.getCurrentWeatherCoordDBModel().getLat());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_LON, currentWeatherDBModel.getCurrentWeatherCoordDBModel().getLon());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_HUMIDITY, currentWeatherDBModel.getCurrentWeatherMainDBModel().getHumidity());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MAX_TEMP, currentWeatherDBModel.getCurrentWeatherMainDBModel().getTempMax());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_MIN_TEMP, currentWeatherDBModel.getCurrentWeatherMainDBModel().getTempMin());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_WIND_SPEED, currentWeatherDBModel.getCurrentWeatherWindDBModel().getSpeed());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_DESCRIPTION, currentWeatherDBModel.getCurrentWeatherInfoDBModel().get(0).getDescription());
        cv.put(WeatherContract.CurrentWeatherEntry.CURRENT_WEATHER_TABLE_COLUMN_ICON, currentWeatherDBModel.getCurrentWeatherInfoDBModel().get(0).getIcon());
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

    private static Date getNextDate(Date curDate) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(curDate);
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        return calendar.getTime();
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




/*    private static long getEpochForTodayMidnight(){
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

package com.example.abhishek.weatherforecast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;

import static com.example.abhishek.weatherforecast.WeatherListFragment.ACTION_PERIODIC_CURRENT_WEATHER_API_SUCCESS;
import static com.example.abhishek.weatherforecast.WeatherListFragment.KEY_CURRENT_WEATHER;

/**
 * Created by abhishek on 25/8/18.
 */

public class CurrentWeatherUpdateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(ACTION_PERIODIC_CURRENT_WEATHER_API_SUCCESS)){

            CurrentWeatherApiModel cwApiModel = intent.getParcelableExtra(KEY_CURRENT_WEATHER);
            CurrentWeatherBusinessModel cwBusinessModel = new CurrentWeatherBusinessModel(cwApiModel);

            //CHECK WHETHER THE CURRENT WEATHER DETAILS ALREADY AVAILABALE IN DB OR NOT
            boolean isDataAlreadyInDB = Utils.isAlreadyCurrentWeatherInfoPresentInDB(cwBusinessModel, context);
            Log.d("#","isDataAlreadyInDB : "+isDataAlreadyInDB);
            //IF DATA UN-AVAILABALE ,  SHOW NOTIFICATION
            if(!isDataAlreadyInDB){
                Utils.NotificationUtils.showUpdatedData(context, cwBusinessModel);
            }
        }
    }
}

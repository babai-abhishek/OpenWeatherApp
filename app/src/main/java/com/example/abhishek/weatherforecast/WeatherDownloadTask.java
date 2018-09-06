package com.example.abhishek.weatherforecast;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.networkutils.ApiClient;
import com.example.abhishek.weatherforecast.networkutils.WeatherInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.abhishek.weatherforecast.WeatherListFragment.ACTION_CURRENT_WEATHER_API_FAILURE;
import static com.example.abhishek.weatherforecast.WeatherListFragment.ACTION_CURRENT_WEATHER_API_SUCCESS;
import static com.example.abhishek.weatherforecast.WeatherListFragment.ACTION_PERIODIC_CURRENT_WEATHER_API_SUCCESS;
import static com.example.abhishek.weatherforecast.WeatherListFragment.ACTION_WEATHER_FORECAST_API_FAILURE;
import static com.example.abhishek.weatherforecast.WeatherListFragment.ACTION_WEATHER_FORECAST_API_SUCCESS;
import static com.example.abhishek.weatherforecast.WeatherListFragment.KEY_CURRENT_WEATHER;
import static com.example.abhishek.weatherforecast.WeatherListFragment.KEY_WEATHER_FORECAST;
import static com.example.abhishek.weatherforecast.WeatherListFragment.OWM_API_KEY;

/**
 * Created by abhishek on 31/7/18.
 */

public class WeatherDownloadTask {

    private static WeatherInterface weatherInterface = ApiClient.getClient().create(WeatherInterface.class);

    public synchronized static void loadCurrentWeather(final Context ctx, final boolean showNotification) {
        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(ctx);
        Call<CurrentWeatherApiModel> currentWeatherApiModelCall = weatherInterface.getCurrentWeather(Utils.SettingsUtils.getPreferredLocation(ctx), OWM_API_KEY);
        currentWeatherApiModelCall.enqueue(new Callback<CurrentWeatherApiModel>() {
            @Override
            public void onResponse(Call<CurrentWeatherApiModel> call, Response<CurrentWeatherApiModel> response) {
                CurrentWeatherApiModel currentWeatherApiModel = null;
                if(response.isSuccessful()) {
                    currentWeatherApiModel = response.body();
                    Intent intent = null;

                    if (!showNotification) {
                        //register intent for broadcast manager
                        intent = new Intent(ACTION_CURRENT_WEATHER_API_SUCCESS);
                        intent.putExtra(KEY_CURRENT_WEATHER, currentWeatherApiModel);
                        //send broadcast
                        broadcastManager.sendBroadcast(intent);

                    } else {
                        intent = new Intent(ACTION_PERIODIC_CURRENT_WEATHER_API_SUCCESS);
                        intent.putExtra(KEY_CURRENT_WEATHER, currentWeatherApiModel);
                        //send broadcast
                        ctx.sendBroadcast(intent);
                    }
                }

            }

            @Override
            public void onFailure(Call<CurrentWeatherApiModel> call, Throwable t) {
                Intent intent = new Intent(ACTION_CURRENT_WEATHER_API_FAILURE);
                broadcastManager.sendBroadcast(intent);
            }
        });

    }

    public synchronized static void loadWeatherForecast(Context ctx) {
        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(ctx);
        Call<WeatherApiModel> call = weatherInterface.getListOfWeatherForecast(Utils.SettingsUtils.getPreferredLocation(ctx),OWM_API_KEY);
        call.enqueue(new Callback<WeatherApiModel>() {
            @Override
            public void onResponse(Call<WeatherApiModel> call, Response<WeatherApiModel> response) {
                WeatherApiModel weather = null;

                //if response is sucessful
                if(response.isSuccessful()){
                    //get data from response
                    weather = response.body();
                    //register intent for broadcast manager
                    Intent intent = new Intent(ACTION_WEATHER_FORECAST_API_SUCCESS);
                    intent.putExtra(KEY_WEATHER_FORECAST,weather);

                    //send broadcast
                    broadcastManager.sendBroadcast(intent);
                }

            }

            @Override
            public void onFailure(Call<WeatherApiModel> call, Throwable t) {
                Intent intent = new Intent(ACTION_WEATHER_FORECAST_API_FAILURE);
                broadcastManager.sendBroadcast(intent);
            }
        });

    }


}

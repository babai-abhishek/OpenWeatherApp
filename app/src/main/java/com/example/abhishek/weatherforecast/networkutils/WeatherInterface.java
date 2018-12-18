package com.example.abhishek.weatherforecast.networkutils;

import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abhishek on 16/7/18.
 */

public interface WeatherInterface {

    @GET("forecast")
    Call<WeatherApiModel> getListOfWeatherForecast(@Query("q") String location,
                                                   @Query("appid") String key);

    @GET("weather")
    Call<CurrentWeatherApiModel> getCurrentWeather(@Query("q") String location,
                                                   @Query("appid") String key);
}

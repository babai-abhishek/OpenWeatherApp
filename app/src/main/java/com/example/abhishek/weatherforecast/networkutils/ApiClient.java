package com.example.abhishek.weatherforecast.networkutils;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abhishek on 16/7/18.
 */

public class ApiClient {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
//    public static final String BASE_URL = "http://api.themoviedb.org/3/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        Log.d("#",retrofit.baseUrl().url().toString());
        return retrofit;
    }
}

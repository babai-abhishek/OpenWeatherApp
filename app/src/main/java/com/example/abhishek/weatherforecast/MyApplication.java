package com.example.abhishek.weatherforecast;

import android.app.Application;

/**
 * Created by abhishek on 30/7/18.
 */

public class MyApplication extends Application{

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkConnectivityManager.ConnectivityReceiverListener listener) {
        NetworkConnectivityManager.connectivityReceiverListener = listener;
    }
}

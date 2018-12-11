package com.example.abhishek.weatherforecast;

import android.app.Application;


public class MyApplication extends Application {

    private static MyApplication mInstance;

    public Boolean lastState = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkConnectivityReceiver.ConnectivityReceiverListener listener) {
        NetworkConnectivityReceiver.connectivityReceiverListener = listener;
    }
}

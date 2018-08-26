package com.example.abhishek.weatherforecast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by abhishek on 31/7/18.
 */

public class CurrentWeatherSyncService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
                if(!NetworkConnectivityReceiver.isConnected()){
                    Utils.NotificationUtils.showNotificationBecauseNetworkUnAvailAble(getApplicationContext());
                }else {
                    WeatherDownloadTask.loadCurrentWeather(getApplicationContext(),true);
                }

        return super.onStartCommand(intent, flags, startId);
    }

}

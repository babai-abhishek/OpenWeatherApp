package com.example.abhishek.weatherforecast;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by abhishek on 31/7/18.
 */

public class WeatherNotificationUtils {

    private static int WEATHER_REMINDER_PENDING_INTENT_ID = 1001;

    private static PendingIntent contentIntent(Context context){

        Intent startMainActivity = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(
                context,
                WEATHER_REMINDER_PENDING_INTENT_ID,
                startMainActivity,
                PendingIntent.FLAG_UPDATE_CURRENT);

    }
}

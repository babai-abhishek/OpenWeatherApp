package com.example.abhishek.weatherforecast;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT_WEATHER_LIST = "weatherListFragment";
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.flFragmentContainer, new WeatherListFragment(),TAG_FRAGMENT_WEATHER_LIST);
        transaction.commit();
    }
}

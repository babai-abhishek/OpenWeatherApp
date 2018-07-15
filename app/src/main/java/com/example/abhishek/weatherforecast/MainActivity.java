package com.example.abhishek.weatherforecast;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

import com.example.abhishek.weatherforecast.settings.SettingsActivity;
import com.example.abhishek.weatherforecast.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity
            implements WeatherListFragment.SettingsOptionClickListener {

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

    @Override
    public void onSettingOptiionClicked(Fragment fragment) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}

package com.example.abhishek.weatherforecast.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishek.weatherforecast.R;

/**
 * Created by abhishek on 15/7/18.
 */

public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener{


    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen()
                .getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        getPreferenceScreen()
                .getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.user_pref);

        SharedPreferences sharedPreferences = getPreferenceScreen()
                .getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();

        for(int i=0; i<preferenceScreen.getPreferenceCount(); i++){
            Preference preference = preferenceScreen.getPreference(i);
            if(!(preference instanceof CheckBoxPreference)){
                String value = sharedPreferences.getString(preference
                        .getKey(),"");
                setPreferenceSummary(preference, value);
            }

        }
    }

    private void setPreferenceSummary(Preference preference, Object value) {
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(value.toString());
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        }else {
            preference.setSummary(value.toString());
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}

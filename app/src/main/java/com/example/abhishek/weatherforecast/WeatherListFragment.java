package com.example.abhishek.weatherforecast;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.example.abhishek.weatherforecast.model.api.WeatherApiModel;
import com.example.abhishek.weatherforecast.networkutils.ApiClient;
import com.example.abhishek.weatherforecast.networkutils.WeatherInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherListFragment extends Fragment
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String OWM_API_KEY = "71ecdcdd6d04f99f1c06210c95011f10";

    SettingsOptionClickListener clickListener;

    List<WeatherApiModel> weatherDetails = new ArrayList<>();
    WeatherInterface weatherInterface = ApiClient.getClient().create(WeatherInterface.class);
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof SettingsOptionClickListener){
            clickListener= (SettingsOptionClickListener) context;
        } else{
            throw new RuntimeException(context.getClass().getSimpleName()+" must implement WeatherListFragment.SettingsOptionClickListener");
        }
    }

    public WeatherListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        loadWeather();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.settings, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                clickListener.onSettingOptiionClicked(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    public interface SettingsOptionClickListener{
        void onSettingOptiionClicked(Fragment fragment);
    }

    private void loadWeather() {
        Call<WeatherApiModel> call = weatherInterface.getListOfWeatherForecast("Kolkata,in",OWM_API_KEY);
         call.enqueue(new Callback<WeatherApiModel>() {
            @Override
            public void onResponse(Call<WeatherApiModel> call, Response<WeatherApiModel> response) {
                Log.d("#","response "+response.body().toString());
            }

            @Override
            public void onFailure(Call<WeatherApiModel> call, Throwable t) {

            }
        });

    }

}

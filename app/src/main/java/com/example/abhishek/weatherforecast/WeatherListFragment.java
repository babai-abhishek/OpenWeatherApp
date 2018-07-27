package com.example.abhishek.weatherforecast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.abhishek.weatherforecast.DBUtils.WeatherForecastDBDao;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.networkutils.ApiClient;
import com.example.abhishek.weatherforecast.networkutils.WeatherInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherListFragment extends Fragment
        implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final String TEST_LOCATION = "Mysore,in";

    private static final String OWM_API_KEY = "71ecdcdd6d04f99f1c06210c95011f10";
    private static final String ACTION_WEATHER_FORECAST_API_SUCCESS = "com.example.abhishek.weatherforecast.weatherlistfragment.api.weatherforecast.result.success";
    private static final String ACTION_WEATHER_FORECAST_API_FAILURE = "com.example.abhishek.weatherforecast.weatherlistfragment.api.weatherforecast.result.fail";
    private static final String KEY_WEATHER_FORECAST = "weather_forecast";

    SettingsOptionClickListener clickListener;
    private LocalBroadcastManager broadcastManager = null;
    WeatherInterface weatherInterface = ApiClient.getClient().create(WeatherInterface.class);

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case ACTION_WEATHER_FORECAST_API_SUCCESS:
                    Toast.makeText(getActivity(), "Api Success", Toast.LENGTH_SHORT).show();
                    WeatherApiModel weather = intent.getParcelableExtra(KEY_WEATHER_FORECAST);

                    //call utility method to save data into local database(SQLite/Realm/Room)
                    WeatherForecastDBDao.insertData(new WeatherBusinessModel(weather), getActivity());

                    //call utility method to retrieve data from DB to show in the list
                 //   WeatherForecastDBDao.retrieveWeatherForecastInfo(TEST_LOCATION);

                    //show into the list


                    break;


                case ACTION_WEATHER_FORECAST_API_FAILURE:
                    Toast.makeText(getActivity(), "Api Failure", Toast.LENGTH_SHORT).show();
                    break;
            }

        }
    };

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
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        setHasOptionsMenu(true);
        loadWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_WEATHER_FORECAST_API_SUCCESS);
        filter.addAction(ACTION_WEATHER_FORECAST_API_FAILURE);
        broadcastManager.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        broadcastManager.unregisterReceiver(broadcastReceiver);
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
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    public interface SettingsOptionClickListener{
        void onSettingOptiionClicked(Fragment fragment);
    }

    private void loadWeather() {
        Call<WeatherApiModel> call = weatherInterface.getListOfWeatherForecast(TEST_LOCATION,OWM_API_KEY);
         call.enqueue(new Callback<WeatherApiModel>() {
            @Override
            public void onResponse(Call<WeatherApiModel> call, Response<WeatherApiModel> response) {
                WeatherApiModel weather = null;

               //if response is sucessful
                if(response.isSuccessful()){
                    //get data from response
                    weather = response.body();
                }

               //register intent for broadcast manager
                Intent intent = new Intent(ACTION_WEATHER_FORECAST_API_SUCCESS);
                intent.putExtra(KEY_WEATHER_FORECAST,weather);

                //send broadcast
                broadcastManager.sendBroadcast(intent);

            }

            @Override
            public void onFailure(Call<WeatherApiModel> call, Throwable t) {
                Intent intent = new Intent(ACTION_WEATHER_FORECAST_API_FAILURE);
                broadcastManager.sendBroadcast(intent);
            }
        });

    }

}

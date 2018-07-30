package com.example.abhishek.weatherforecast;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.abhishek.weatherforecast.DBUtils.WeatherDBDao;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherBusinessModel;
import com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;
import com.example.abhishek.weatherforecast.networkutils.ApiClient;
import com.example.abhishek.weatherforecast.networkutils.WeatherInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherListFragment extends Fragment
        implements SharedPreferences.OnSharedPreferenceChangeListener, NetworkConnectivityManager.ConnectivityReceiverListener{

    private static final String TEST_LOCATION = "Kolkata,in";

    private static final String OWM_API_KEY = "71ecdcdd6d04f99f1c06210c95011f10";
    private static final String ACTION_WEATHER_FORECAST_API_SUCCESS = "com.example.abhishek.weatherforecast.weatherlistfragment.api.weatherforecast.result.success";
    private static final String ACTION_WEATHER_FORECAST_API_FAILURE = "com.example.abhishek.weatherforecast.weatherlistfragment.api.weatherforecast.result.fail";
    private static final String ACTION_CURRENT_WEATHER_API_SUCCESS = "com.example.abhishek.weatherforecast.weatherlistfragment.api.currentweather.result.success";
    private static final String ACTION_CURRENT_WEATHER_API_FAILURE = "com.example.abhishek.weatherforecast.weatherlistfragment.api.currentweather.result.fail";
    private static final String KEY_WEATHER_FORECAST = "weather_forecast";
    private static final String KEY_CURRENT_WEATHER = "currentWeather";

    SettingsOptionClickListener clickListener;
    private LocalBroadcastManager broadcastManager = null;
    WeatherInterface weatherInterface = ApiClient.getClient().create(WeatherInterface.class);

    //LIST FOR STORING INFORMATION ABOUT WEATHER FORECAST EXCLUDING TODAY
    List<WeatherListApiModel> weatherListFromTomorrow = new ArrayList<>();
    List<IWeatherDetails> iWeatherDetailsList = new ArrayList<>();


    //VARIABLES FOR SHOWING LIST
    WeatherAdapter adapter;
    private RecyclerView recyclerView;
    private FrameLayout list_layout;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case ACTION_WEATHER_FORECAST_API_SUCCESS:
                    Toast.makeText(getActivity(), "Weather forecast Api Success", Toast.LENGTH_SHORT).show();
                    WeatherApiModel weather = intent.getParcelableExtra(KEY_WEATHER_FORECAST);

                    //RETRIEVE FORECAST DATA EXCLUDING TODAY'S WEATHER
                    weatherListFromTomorrow = Utils.getWeatherForecastListFromTomorrow(weather);

                    //CONVERT WEATHERLISTAPIMODEL TO WEATHERLISTBUSINESSMODEL
                    List<WeatherListBusinessModel> listBusinessModels = new ArrayList<>();
                    for(WeatherListApiModel apiModel: weatherListFromTomorrow){
                        listBusinessModels.add(new WeatherListBusinessModel(apiModel));
                    }

                    //ADD WEATHERLISTBUSINESSMODELS TO LIST FOR ADAPTER
                    for(WeatherListBusinessModel weatherListBusinessModel: listBusinessModels){
                        iWeatherDetailsList.add(weatherListBusinessModel);
                    }
                    adapter.setWeatherList(iWeatherDetailsList);

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                    for(IWeatherDetails details: iWeatherDetailsList){
                        if(details instanceof WeatherListBusinessModel){
                            WeatherListBusinessModel wb = (WeatherListBusinessModel) details;
                            Date dt = new Date(wb.getDt()*1000L);
                            Log.d("#",weather.getCityApiModel().getName()+" on : "+sdf.format(dt)+" max temp "+String.format(context.getString(R.string.format_temperature_celsius), wb.getMainBusinessModel().getTempMax()-273.0)+" min temp "+
                                    String.format(context.getString(R.string.format_temperature_celsius), wb.getMainBusinessModel().getTempMin()-273.0));
                        }
                        else if(details instanceof CurrentWeatherBusinessModel){
                            CurrentWeatherBusinessModel cb = (CurrentWeatherBusinessModel) details;
                            Log.d("#",cb.getName()+ " Today : "+sdf.format(cb.getDt() *1000L)+" max temp "+String.format(context.getString(R.string.format_temperature_celsius), cb.getCurrentWeatherMainBusinessModel().getTempMax()-273.0)+" min temp "+
                                    String.format(context.getString(R.string.format_temperature_celsius), cb.getCurrentWeatherMainBusinessModel().getTempMin()-273.0));
                        }

                    }

                    //INSERT WEATHER FORECAST INTO DB
                    WeatherDBDao.insertForecastData(new WeatherBusinessModel(weather), getActivity());
                    break;

                case ACTION_CURRENT_WEATHER_API_SUCCESS:
                    Toast.makeText(getActivity(), "Current weather Api Success", Toast.LENGTH_SHORT).show();
                    CurrentWeatherApiModel currentWeatherApiModel = intent.getParcelableExtra(KEY_CURRENT_WEATHER);
                    CurrentWeatherBusinessModel currentWeatherBusinessModel = new CurrentWeatherBusinessModel(currentWeatherApiModel);

                    //ADD TO LIST FOR ADAPTER
                    iWeatherDetailsList.add(0,currentWeatherBusinessModel);

                    //SHOW CURRENT WEATHER IN THE LIST
                    adapter.setWeatherList(iWeatherDetailsList);


                    //INSERT CURRENT WEATHER INTO DATABASE
                    WeatherDBDao.insertCurrentWeatherIntoDB(currentWeatherBusinessModel, getActivity());
                    break;

                case ACTION_WEATHER_FORECAST_API_FAILURE:
                    Toast.makeText(getActivity(), "Weather forecast Api Failure", Toast.LENGTH_SHORT).show();
                    break;

                case ACTION_CURRENT_WEATHER_API_FAILURE:
                    Toast.makeText(getActivity(), "Current weather Api Failure", Toast.LENGTH_SHORT).show();
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
        adapter = new WeatherAdapter(getActivity(),iWeatherDetailsList);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);
        list_layout = view.findViewById(R.id.list_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_forecast);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_CURRENT_WEATHER_API_SUCCESS);
        filter.addAction(ACTION_CURRENT_WEATHER_API_FAILURE);
        filter.addAction(ACTION_WEATHER_FORECAST_API_SUCCESS);
        filter.addAction(ACTION_WEATHER_FORECAST_API_FAILURE);
        broadcastManager.registerReceiver(broadcastReceiver, filter);

        boolean isInternetAvailAble = false;

        //CHECK INTERNET CONNECTION AVAILABLE (YES/NO ?)
        isInternetAvailAble = NetworkConnectivityManager.isConnected();

        showSnack(isInternetAvailAble);

        //IF YES : (MEANING ONLINE) GET DATA FROM WEB , SHOW IT ON SCREEN , SAVE INTO DB
        if (isInternetAvailAble) {

            //LOAD FORECAST INFO
            loadWeatherForecast();

            //LOAD CURRENT INFO
            loadCurrentWeather();
        } else {

            //CHECK DATA AVAILABLE IN DB
            List<IWeatherDetails> availableData = Utils.checkCurrentDataForCity(TEST_LOCATION, getActivity());
            if(!availableData.isEmpty()){
                //IF DATA AVAILABLE SHOW ON SCREEN
                adapter.setWeatherList(availableData);

            }
            //IF NO DATA AVAILABLE SHOW ERROR


        }
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

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    public interface SettingsOptionClickListener{
        void onSettingOptiionClicked(Fragment fragment);
    }

    private void loadWeatherForecast() {
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

    private void loadCurrentWeather() {
        Call<CurrentWeatherApiModel> currentWeatherApiModelCall = weatherInterface.getCurrentWeather(TEST_LOCATION, OWM_API_KEY);
        currentWeatherApiModelCall.enqueue(new Callback<CurrentWeatherApiModel>() {
            @Override
            public void onResponse(Call<CurrentWeatherApiModel> call, Response<CurrentWeatherApiModel> response) {
                CurrentWeatherApiModel currentWeatherApiModel = null;
                if(response.isSuccessful()){
                    currentWeatherApiModel = response.body();
                }
                //register intent for broadcast manager
                Intent intent = new Intent(ACTION_CURRENT_WEATHER_API_SUCCESS);
                intent.putExtra(KEY_CURRENT_WEATHER,currentWeatherApiModel);

                //send broadcast
                broadcastManager.sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<CurrentWeatherApiModel> call, Throwable t) {
                Intent intent = new Intent(ACTION_CURRENT_WEATHER_API_FAILURE);
                broadcastManager.sendBroadcast(intent);
            }
        });

    }

    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Connected to Internet";
        } else {
            message = "Sorry! Not connected to internet";
        }

        Snackbar snackbar = Snackbar
                .make(list_layout, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

}

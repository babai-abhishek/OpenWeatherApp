package com.example.abhishek.weatherforecast;


import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.example.abhishek.weatherforecast.Model.dbUtils.WeatherDatabase;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi.CurrentWeatherApiModel;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherBusiness.CurrentWeatherBusinessModel;
import com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherRoomDBEntity.CurrentWeather;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness.WeatherListBusinessModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherRoomDBEntity.ForecastWeather;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherListFragment extends Fragment
        implements NetworkConnectivityReceiver.ConnectivityReceiverListener {

    public static final String OWM_API_KEY = "71ecdcdd6d04f99f1c06210c95011f10";
    public static final String ACTION_WEATHER_FORECAST_API_SUCCESS = "com.example.abhishek.weatherforecast.weatherlistfragment.api.weatherforecast.result.success";
    public static final String ACTION_WEATHER_FORECAST_API_FAILURE = "com.example.abhishek.weatherforecast.weatherlistfragment.api.weatherforecast.result.fail";
    public static final String ACTION_CURRENT_WEATHER_API_SUCCESS = "com.example.abhishek.weatherforecast.weatherlistfragment.api.currentweather.result.success";
    public static final String ACTION_PERIODIC_CURRENT_WEATHER_API_SUCCESS = "com.example.abhishek.weatherforecast.weatherlistfragment.api.periodic.currentweather.result.success";
    public static final String ACTION_CURRENT_WEATHER_API_FAILURE = "com.example.abhishek.weatherforecast.weatherlistfragment.api.currentweather.result.fail";
    public static final String KEY_WEATHER_FORECAST = "weather_forecast";
    public static final String KEY_CURRENT_WEATHER = "currentWeather";
    public static String LOCATION;
    SettingsOptionClickListener clickListener;
    boolean alarmAlreadyStarted = false;
    ProgressDialog mProgressDialog;

    //LIST FOR STORING INFORMATION ABOUT WEATHER FORECAST EXCLUDING TODAY
    List<WeatherListApiModel> weatherListFromTomorrow = new ArrayList<>();
    List<IWeatherDetails> iWeatherDetailsList = new ArrayList<>();

    //VARIABLES FOR SHOWING LIST
    WeatherAdapter adapter;
    private LocalBroadcastManager broadcastManager = null;
    private boolean isCurrentWeatherLoaded = false,
            isWeatherForecastLoaded = false;
    private RecyclerView recyclerView;
    private FrameLayout list_layout;

    //  NetworkConnectivityReceiver connectivityReceiverListener;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {

            switch (intent.getAction()) {

                case ACTION_WEATHER_FORECAST_API_SUCCESS:
                    Toast.makeText(getActivity(), "Weather forecast Api Success", Toast.LENGTH_SHORT).show();
                    final WeatherApiModel weather = intent.getParcelableExtra(KEY_WEATHER_FORECAST);


                    new AsyncTask<Void, Void, List<WeatherListBusinessModel>>() {
                        List<WeatherListBusinessModel> mWeatherListBusinessModelList;

                        @Override
                        protected List<WeatherListBusinessModel> doInBackground(Void... voids) {

                            if (weather != null) {
                                //INSERT WEATHER FORECAST INTO DB
                                List<ForecastWeather> mForecastWeatherList = Utils.
                                        getListOfForecastWeatherFromDownloadedDataToStoreIntoDB(weather);

                                WeatherDatabase mWeatherDatabase = WeatherDatabase.getInstance(getActivity());
                                mWeatherDatabase
                                        .getForecastWeatherDao()
                                        .insert(mForecastWeatherList);

                                //GET DATA FROM DB
                                List<ForecastWeather> mForecastWeathers = mWeatherDatabase
                                        .getForecastWeatherDao()
                                        .getAllforecastWeathersById(weather.getCityApiModel().getId());

                                mWeatherListBusinessModelList = Utils
                                        .convertForecastWeatherDBModelsListToForecastWeatherBusinessModelsList(mForecastWeathers);
                            }
                            return mWeatherListBusinessModelList;
                        }

                        @Override
                        protected void onPostExecute(List<WeatherListBusinessModel> weatherListBusinessModels) {
                            super.onPostExecute(weatherListBusinessModels);

                            if (weatherListBusinessModels != null) {
                                //ADD WEATHERLISTBUSINESSMODELS TO LIST FOR ADAPTER
                                for (WeatherListBusinessModel weatherListBusinessModel : weatherListBusinessModels) {
                                    iWeatherDetailsList.add(weatherListBusinessModel);
                                }
                            }
                            adapter.setWeatherList(iWeatherDetailsList);
                            isWeatherForecastLoaded = true;
                            postLoad();

                        }
                    }.execute();

                    break;

                case ACTION_CURRENT_WEATHER_API_SUCCESS:
                    Toast.makeText(getActivity(), "Current weather Api Success", Toast.LENGTH_SHORT).show();
                    final CurrentWeatherApiModel currentWeatherApiModel = intent.getParcelableExtra(KEY_CURRENT_WEATHER);

                    new AsyncTask<Void, Void, CurrentWeatherBusinessModel>() {
                        CurrentWeatherBusinessModel mCurrentWeatherBusinessModel;

                        @Override
                        protected CurrentWeatherBusinessModel doInBackground(Void... voids) {
                            if (currentWeatherApiModel != null) {

                                WeatherDatabase mWeatherDatabase = WeatherDatabase.getInstance(getActivity());

                                //INSERT CURRENT WEATHER INTO DATABASE
                                CurrentWeather mCurrentWeather = Utils.
                                        getCurrentWeatherFromDownloadedDataToStoreIntoDB(currentWeatherApiModel);
                                mWeatherDatabase
                                        .getCurrentWeatherDao()
                                        .insert(mCurrentWeather);

                                //GET DATA FROM DB
                                List<CurrentWeather> mCurrentWeathers = mWeatherDatabase
                                        .getCurrentWeatherDao()
                                        .getAllCurrentWeathers();
                                for (int i = 0; i < mCurrentWeathers.size(); i++) {
                                    if (mCurrentWeathers.get(i).cityId == currentWeatherApiModel.getId()) {
                                        //show on list
                                        CurrentWeather mWeather = mCurrentWeathers.get(i);
                                        mCurrentWeatherBusinessModel = Utils.
                                                convertCurrentWeatherDbToCurrentWeatherBusinessModel(mWeather);
                                    }
                                }

                            }

                            return mCurrentWeatherBusinessModel;
                        }


                        @Override
                        protected void onPostExecute(CurrentWeatherBusinessModel currentWeatherBusinessModel) {
                            super.onPostExecute(currentWeatherBusinessModel);

                            if (currentWeatherBusinessModel != null) {
                                //ADD TO LIST FOR ADAPTER
                                if (!iWeatherDetailsList.isEmpty()) {
                                    iWeatherDetailsList.set(0, currentWeatherBusinessModel);
                                } else {
                                    iWeatherDetailsList.add(0, currentWeatherBusinessModel);
                                }
                            }
                            //SHOW CURRENT WEATHER IN THE LIST
                            adapter.setWeatherList(iWeatherDetailsList);

                            isCurrentWeatherLoaded = true;
                            postLoad();
                        }
                    }.execute();


                    break;

                case ACTION_WEATHER_FORECAST_API_FAILURE:
                    Toast.makeText(getActivity(), "Weather forecast Api Failure", Toast.LENGTH_SHORT).show();
                    isWeatherForecastLoaded = true;
                    postLoad();
                    break;

                case ACTION_CURRENT_WEATHER_API_FAILURE:
                    Toast.makeText(getActivity(), "Current weather Api Failure", Toast.LENGTH_SHORT).show();
                    isCurrentWeatherLoaded = true;
                    postLoad();
                    break;

            }

        }
    };

    public WeatherListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof SettingsOptionClickListener) {
            clickListener = (SettingsOptionClickListener) context;
        } else {
            throw new RuntimeException(context.getClass().getSimpleName() + " must implement WeatherListFragment.SettingsOptionClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().setConnectivityListener(this);
        //  connectivityReceiverListener = new NetworkConnectivityReceiver(this);
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        adapter = new WeatherAdapter(getActivity(), iWeatherDetailsList);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);
        list_layout = view.findViewById(R.id.list_layout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_forecast);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        LOCATION = Utils.SettingsUtils.getPreferredLocation(getContext());
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_CURRENT_WEATHER_API_SUCCESS);
        filter.addAction(ACTION_CURRENT_WEATHER_API_FAILURE);
        filter.addAction(ACTION_WEATHER_FORECAST_API_SUCCESS);
        filter.addAction(ACTION_WEATHER_FORECAST_API_FAILURE);
        broadcastManager.registerReceiver(broadcastReceiver, filter);

        showLoading();

        //CHECK INTERNET CONNECTION AVAILABLE (YES/NO ?)
        boolean isInternetAvailAble = NetworkConnectivityReceiver.isConnected();

        //SHOW AVAILABLE/UNAVAILABLE
        showSnack(isInternetAvailAble);

        //IF YES : (MEANING ONLINE) GET DATA FROM WEB , SHOW IT ON SCREEN , SAVE INTO DB
        if (isInternetAvailAble) {

            if (iWeatherDetailsList.size() > 0)
                iWeatherDetailsList.clear();
            //LOAD FORECAST INFO
            WeatherDownloadTask.loadWeatherForecast(getContext());

            //LOAD CURRENT INFO
            WeatherDownloadTask.loadCurrentWeather(getContext(), false);

        } else {

            new AsyncTask<Void, Void, List<IWeatherDetails>>() {
                @Override
                protected List<IWeatherDetails> doInBackground(Void... voids) {

                    //check currentWeather table
                    WeatherDatabase mWeatherDatabase = WeatherDatabase.getInstance(getActivity());

                    List<CurrentWeather> mCurrentWeathers = mWeatherDatabase
                            .getCurrentWeatherDao()
                            .getAllCurrentWeathers();
                    for (int i = 0; i < mCurrentWeathers.size(); i++) {
                        if(Utils.convertUtcToDate(mCurrentWeathers.get(i).date) == Utils.getCurrentDate()){
                            if (mCurrentWeathers.get(i).cityName.equalsIgnoreCase(LOCATION)) {
                                //show on list
                                CurrentWeather mWeather = mCurrentWeathers.get(i);
                                mCurrentWeatherBusinessModel = Utils.
                                        convertCurrentWeatherDbToCurrentWeatherBusinessModel(mWeather);
                            }
                        }else{
                            //delete old data
                        }

                    }


                    List<IWeatherDetails> availableData = Utils.checkCurrentDataForCity(LOCATION, getActivity());
                    return availableData;
                }

                @Override
                protected void onPostExecute(List<IWeatherDetails> weatherDetails) {
                    super.onPostExecute(weatherDetails);

                    //CHECK DATA AVAILABLE IN DB
                    adapter.setWeatherList(weatherDetails);
                    isCurrentWeatherLoaded = true;
                    isWeatherForecastLoaded = true;
                    postLoad();
                }
            }.execute();


        }

        //START CURRENT WEATHER SYNCRONIZATION PERIODICALLY
        if (!alarmAlreadyStarted) {
            Utils.syncCurrentWeatherData(getContext());
            alarmAlreadyStarted = true;
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        isWeatherForecastLoaded = false;
        isCurrentWeatherLoaded = false;
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
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            //CLEAR ALL NOTIFICATIONS
            Utils.NotificationUtils.clearNotifications(getContext());

            if (iWeatherDetailsList.size() > 0)
                iWeatherDetailsList.clear();
            showLoading();
            WeatherDownloadTask.loadCurrentWeather(getContext(), false);
            WeatherDownloadTask.loadWeatherForecast(getContext());
        }
        showSnack(isConnected);
    }

    private void showSnack(boolean isConnected) {
        String message;
        if (isConnected) {
            message = "Connected to Internet";
        } else {
            message = "Sorry! Not connected to internet";
        }

        Snackbar snackbar = Snackbar
                .make(list_layout, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    private void postLoad() {
        if (isCurrentWeatherLoaded && isWeatherForecastLoaded)
            hideLoading();
    }

    private void showLoading() {
        adapter.setLoading(true);
        if (mProgressDialog.isShowing())
            return;
        mProgressDialog.setMessage("Loading.......");
        mProgressDialog.show();
    }

    private void hideLoading() {
        adapter.setLoading(false);
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }

    public interface SettingsOptionClickListener {
        void onSettingOptiionClicked(Fragment fragment);
    }

}


package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.abhishek.weatherforecast.IWeatherDetails;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherInfoApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.WeatherListApiModel;
import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherRoomDBEntity.ForecastWeather;

import java.util.ArrayList;

public class WeatherListBusinessModel implements Parcelable {

    private long dt;
    private MainBusinessModel mainBusinessModel;
    private java.util.List<WeatherInfoBusinessModel> weatherInfoBusinessModel = new ArrayList<WeatherInfoBusinessModel>();


    /**
     * No args constructor for use in serialization
     *
     */
    public WeatherListBusinessModel() {
    }

    /**
     *
     * @param cloudsBusinessModel
     * @param dt
     * @param windBusinessModel
     * @param sysBusinessModel
     * @param weatherInfoBusinessModel
     * @param mainBusinessModel
     */
    public WeatherListBusinessModel(long dt, MainBusinessModel mainBusinessModel, java.util.List<WeatherInfoBusinessModel> weatherInfoBusinessModel, CloudsBusinessModel cloudsBusinessModel, WindBusinessModel windBusinessModel, SysBusinessModel sysBusinessModel) {
        this.dt = dt;
        this.mainBusinessModel = mainBusinessModel;
        this.weatherInfoBusinessModel = weatherInfoBusinessModel;

    }

    public WeatherListBusinessModel(WeatherListApiModel weatherListApiModel) {
        this.dt = weatherListApiModel.getDt();
        this.mainBusinessModel = new MainBusinessModel(weatherListApiModel.getMainApiModel());
        for(WeatherInfoApiModel weatherInfoApiModel: weatherListApiModel.getWeatherInfoApiModel()){
            this.weatherInfoBusinessModel.add(new WeatherInfoBusinessModel(weatherInfoApiModel));
        }

    }

    public WeatherListBusinessModel(ForecastWeather forecastWeather) {
        this.dt = (long) forecastWeather.date;
        this.mainBusinessModel = new MainBusinessModel(forecastWeather.minTemp, forecastWeather.maxTemp);
        this.weatherInfoBusinessModel.add(new WeatherInfoBusinessModel(forecastWeather.weatherId, forecastWeather.weatherDescription, forecastWeather.iconId));

    }

    protected WeatherListBusinessModel(Parcel in) {
        dt = in.readLong();
    }

    public static final Creator<WeatherListBusinessModel> CREATOR = new Creator<WeatherListBusinessModel>() {
        @Override
        public WeatherListBusinessModel createFromParcel(Parcel in) {
            return new WeatherListBusinessModel(in);
        }

        @Override
        public WeatherListBusinessModel[] newArray(int size) {
            return new WeatherListBusinessModel[size];
        }
    };

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public MainBusinessModel getMainBusinessModel() {
        return mainBusinessModel;
    }

    public void setMainBusinessModel(MainBusinessModel mainBusinessModel) {
        this.mainBusinessModel = mainBusinessModel;
    }

    public java.util.List<WeatherInfoBusinessModel> getWeatherInfoBusinessModel() {
        return weatherInfoBusinessModel;
    }

    public void setWeatherInfoBusinessModel(java.util.List<WeatherInfoBusinessModel> weatherInfoBusinessModel) {
        this.weatherInfoBusinessModel = weatherInfoBusinessModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dt);
    }
}


package com.example.abhishek.weatherforecast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherApiModel implements Parcelable {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("list")
    @Expose
    private java.util.List<WeatherListApiModel> weatherListApiModel = new ArrayList<WeatherListApiModel>();
    @SerializedName("city")
    @Expose
    private CityApiModel cityApiModel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherApiModel() {
    }

    /**
     * 
     * @param cod
     * @param list
     * @param city
     */
    public WeatherApiModel(String cod, java.util.List<WeatherListApiModel> weatherListApiModel, CityApiModel cityApiModel) {
        super();
        this.cod = cod;
        this.weatherListApiModel = weatherListApiModel;
        this.cityApiModel = cityApiModel;
    }

    protected WeatherApiModel(Parcel in) {
        cod = in.readString();
    }

    public static final Creator<WeatherApiModel> CREATOR = new Creator<WeatherApiModel>() {
        @Override
        public WeatherApiModel createFromParcel(Parcel in) {
            return new WeatherApiModel(in);
        }

        @Override
        public WeatherApiModel[] newArray(int size) {
            return new WeatherApiModel[size];
        }
    };

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public java.util.List<WeatherListApiModel> getWeatherListApiModel() {
        return weatherListApiModel;
    }

    public void setWeatherListApiModel(java.util.List<WeatherListApiModel> weatherListApiModel) {
        this.weatherListApiModel = weatherListApiModel;
    }

    public CityApiModel getCityApiModel() {
        return cityApiModel;
    }

    public void setCityApiModel(CityApiModel cityApiModel) {
        this.cityApiModel = cityApiModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cod);
    }
}

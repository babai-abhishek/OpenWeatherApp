
package com.example.abhishek.weatherforecast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherListApiModel implements Parcelable {

    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("main")
    @Expose
    private MainApiModel mainApiModel;
    @SerializedName("weather")
    @Expose
    private java.util.List<WeatherInfoApiModel> weatherInfoApiModel = new ArrayList<WeatherInfoApiModel>();
    @SerializedName("clouds")
    @Expose
    private CloudsApiModel cloudsApiModel;
    @SerializedName("wind")
    @Expose
    private WindApiModel windApiModel;
    @SerializedName("sys")
    @Expose
    private SysApiModel sysApiModel;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherListApiModel() {
    }

    /**
     * 
     * @param clouds
     * @param dt
     * @param wind
     * @param sys
     * @param weather
     * @param main
     */
    public WeatherListApiModel(long dt, MainApiModel mainApiModel, java.util.List<WeatherInfoApiModel> weatherInfoApiModel, CloudsApiModel cloudsApiModel, WindApiModel windApiModel, SysApiModel sysApiModel) {
        super();
        this.dt = dt;
        this.mainApiModel = mainApiModel;
        this.weatherInfoApiModel = weatherInfoApiModel;
        this.cloudsApiModel = cloudsApiModel;
        this.windApiModel = windApiModel;
        this.sysApiModel = sysApiModel;
    }

    protected WeatherListApiModel(Parcel in) {
        dt = in.readLong();
        mainApiModel = in.readParcelable(MainApiModel.class.getClassLoader());
        cloudsApiModel = in.readParcelable(CloudsApiModel.class.getClassLoader());
    }

    public static final Creator<WeatherListApiModel> CREATOR = new Creator<WeatherListApiModel>() {
        @Override
        public WeatherListApiModel createFromParcel(Parcel in) {
            return new WeatherListApiModel(in);
        }

        @Override
        public WeatherListApiModel[] newArray(int size) {
            return new WeatherListApiModel[size];
        }
    };

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public MainApiModel getMainApiModel() {
        return mainApiModel;
    }

    public void setMainApiModel(MainApiModel mainApiModel) {
        this.mainApiModel = mainApiModel;
    }

    public java.util.List<WeatherInfoApiModel> getWeatherInfoApiModel() {
        return weatherInfoApiModel;
    }

    public void setWeatherInfoApiModel(java.util.List<WeatherInfoApiModel> weatherInfoApiModel) {
        this.weatherInfoApiModel = weatherInfoApiModel;
    }

    public CloudsApiModel getCloudsApiModel() {
        return cloudsApiModel;
    }

    public void setCloudsApiModel(CloudsApiModel cloudsApiModel) {
        this.cloudsApiModel = cloudsApiModel;
    }

    public WindApiModel getWindApiModel() {
        return windApiModel;
    }

    public void setWindApiModel(WindApiModel windApiModel) {
        this.windApiModel = windApiModel;
    }

    public SysApiModel getSysApiModel() {
        return sysApiModel;
    }

    public void setSysApiModel(SysApiModel sysApiModel) {
        this.sysApiModel = sysApiModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dt);
        dest.writeParcelable(mainApiModel, flags);
        dest.writeParcelable(cloudsApiModel, flags);
    }
}

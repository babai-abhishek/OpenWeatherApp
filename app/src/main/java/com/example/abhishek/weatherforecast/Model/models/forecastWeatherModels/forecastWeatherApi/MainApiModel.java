
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainApiModel implements Parcelable{


    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MainApiModel() {
    }

    /**
     * 
     * @param humidity
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public MainApiModel(double temp, double tempMin, double tempMax, long humidity) {
        super();
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    protected MainApiModel(Parcel in) {

        tempMin = in.readDouble();
        tempMax = in.readDouble();
    }

    public static final Creator<MainApiModel> CREATOR = new Creator<MainApiModel>() {
        @Override
        public MainApiModel createFromParcel(Parcel in) {
            return new MainApiModel(in);
        }

        @Override
        public MainApiModel[] newArray(int size) {
            return new MainApiModel[size];
        }
    };


    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(tempMin);
        dest.writeDouble(tempMax);
    }
}

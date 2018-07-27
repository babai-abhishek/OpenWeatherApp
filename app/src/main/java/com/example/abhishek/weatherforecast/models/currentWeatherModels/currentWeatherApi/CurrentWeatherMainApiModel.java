
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherMainApiModel implements Parcelable
{

    @SerializedName("humidity")
    @Expose
    private int humidity;
    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;
    public final static Creator<CurrentWeatherMainApiModel> CREATOR = new Creator<CurrentWeatherMainApiModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeatherMainApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherMainApiModel(in);
        }

        public CurrentWeatherMainApiModel[] newArray(int size) {
            return (new CurrentWeatherMainApiModel[size]);
        }

    }
    ;

    protected CurrentWeatherMainApiModel(Parcel in) {
        this.humidity = ((int) in.readValue((int.class.getClassLoader())));
        this.tempMin = ((double) in.readValue((double.class.getClassLoader())));
        this.tempMax = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherMainApiModel() {
    }

    /**
     * 
     * @param humidity
     * @param tempMax
     * @param tempMin
     */
    public CurrentWeatherMainApiModel(int humidity, double tempMin, double tempMax) {
        super();
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(humidity);
        dest.writeValue(tempMin);
        dest.writeValue(tempMax);
    }

    public int describeContents() {
        return  0;
    }

}


package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherWindApiModel implements Parcelable
{

    @SerializedName("speed")
    @Expose
    private double speed;
    public final static Creator<CurrentWeatherWindApiModel> CREATOR = new Creator<CurrentWeatherWindApiModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeatherWindApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherWindApiModel(in);
        }

        public CurrentWeatherWindApiModel[] newArray(int size) {
            return (new CurrentWeatherWindApiModel[size]);
        }

    }
    ;

    protected CurrentWeatherWindApiModel(Parcel in) {
        this.speed = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherWindApiModel() {
    }

    /**
     * 
     * @param speed
     */
    public CurrentWeatherWindApiModel(double speed) {
        super();
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(speed);
    }

    public int describeContents() {
        return  0;
    }

}

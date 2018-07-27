
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherCoordApiModel implements Parcelable
{

    @SerializedName("lon")
    @Expose
    private double lon;
    @SerializedName("lat")
    @Expose
    private double lat;
    public final static Creator<CurrentWeatherCoordApiModel> CREATOR = new Creator<CurrentWeatherCoordApiModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeatherCoordApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherCoordApiModel(in);
        }

        public CurrentWeatherCoordApiModel[] newArray(int size) {
            return (new CurrentWeatherCoordApiModel[size]);
        }

    }
    ;

    protected CurrentWeatherCoordApiModel(Parcel in) {
        this.lon = ((double) in.readValue((double.class.getClassLoader())));
        this.lat = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherCoordApiModel() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public CurrentWeatherCoordApiModel(double lon, double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(lon);
        dest.writeValue(lat);
    }

    public int describeContents() {
        return  0;
    }

}


package com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordApiModel implements Parcelable{

    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lon")
    @Expose
    private double lon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoordApiModel() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public CoordApiModel(double lat, double lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    protected CoordApiModel(Parcel in) {
        lat = in.readDouble();
        lon = in.readDouble();
    }

    public static final Creator<CoordApiModel> CREATOR = new Creator<CoordApiModel>() {
        @Override
        public CoordApiModel createFromParcel(Parcel in) {
            return new CoordApiModel(in);
        }

        @Override
        public CoordApiModel[] newArray(int size) {
            return new CoordApiModel[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
    }
}

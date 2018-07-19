
package com.example.abhishek.weatherforecast.models.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindApiModel implements Parcelable {

    @SerializedName("speed")
    @Expose
    private double speed;
    @SerializedName("deg")
    @Expose
    private double deg;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WindApiModel() {
    }

    /**
     * 
     * @param speed
     * @param deg
     */
    public WindApiModel(double speed, double deg) {
        super();
        this.speed = speed;
        this.deg = deg;
    }

    protected WindApiModel(Parcel in) {
        speed = in.readDouble();
        deg = in.readDouble();
    }

    public static final Creator<WindApiModel> CREATOR = new Creator<WindApiModel>() {
        @Override
        public WindApiModel createFromParcel(Parcel in) {
            return new WindApiModel(in);
        }

        @Override
        public WindApiModel[] newArray(int size) {
            return new WindApiModel[size];
        }
    };

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(speed);
        dest.writeDouble(deg);
    }
}

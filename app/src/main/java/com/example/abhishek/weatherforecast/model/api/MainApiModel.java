
package com.example.abhishek.weatherforecast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainApiModel implements Parcelable{

    @SerializedName("temp")
    @Expose
    private double temp;
    @SerializedName("temp_min")
    @Expose
    private double tempMin;
    @SerializedName("temp_max")
    @Expose
    private double tempMax;
    @SerializedName("humidity")
    @Expose
    private long humidity;

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
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
    }

    protected MainApiModel(Parcel in) {
        temp = in.readDouble();
        tempMin = in.readDouble();
        tempMax = in.readDouble();
        humidity = in.readLong();
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

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
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

    public long getHumidity() {
        return humidity;
    }

    public void setHumidity(long humidity) {
        this.humidity = humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp);
        dest.writeDouble(tempMin);
        dest.writeDouble(tempMax);
        dest.writeLong(humidity);
    }
}

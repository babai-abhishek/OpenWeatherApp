
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloudsApiModel implements Parcelable {

    @SerializedName("all")
    @Expose
    private long all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CloudsApiModel() {
    }

    /**
     * 
     * @param all
     */
    public CloudsApiModel(long all) {
        super();
        this.all = all;
    }

    protected CloudsApiModel(Parcel in) {
        all = in.readLong();
    }

    public static final Creator<CloudsApiModel> CREATOR = new Creator<CloudsApiModel>() {
        @Override
        public CloudsApiModel createFromParcel(Parcel in) {
            return new CloudsApiModel(in);
        }

        @Override
        public CloudsApiModel[] newArray(int size) {
            return new CloudsApiModel[size];
        }
    };

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(all);
    }
}

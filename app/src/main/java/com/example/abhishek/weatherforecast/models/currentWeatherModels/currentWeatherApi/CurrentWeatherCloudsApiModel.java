
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherCloudsApiModel implements Parcelable
{

    @SerializedName("all")
    @Expose
    private int all;
    public final static Creator<CurrentWeatherCloudsApiModel> CREATOR = new Creator<CurrentWeatherCloudsApiModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeatherCloudsApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherCloudsApiModel(in);
        }

        public CurrentWeatherCloudsApiModel[] newArray(int size) {
            return (new CurrentWeatherCloudsApiModel[size]);
        }

    }
    ;

    protected CurrentWeatherCloudsApiModel(Parcel in) {
        this.all = ((int) in.readValue((int.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherCloudsApiModel() {
    }

    /**
     * 
     * @param all
     */
    public CurrentWeatherCloudsApiModel(int all) {
        super();
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(all);
    }

    public int describeContents() {
        return  0;
    }

}

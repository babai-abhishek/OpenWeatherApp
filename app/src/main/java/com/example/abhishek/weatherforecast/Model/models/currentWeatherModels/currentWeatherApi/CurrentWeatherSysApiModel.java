
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherSysApiModel implements Parcelable
{

    @SerializedName("country")
    @Expose
    private String country;
    public final static Creator<CurrentWeatherSysApiModel> CREATOR = new Creator<CurrentWeatherSysApiModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeatherSysApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherSysApiModel(in);
        }

        public CurrentWeatherSysApiModel[] newArray(int size) {
            return (new CurrentWeatherSysApiModel[size]);
        }

    }
    ;

    protected CurrentWeatherSysApiModel(Parcel in) {
        this.country = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherSysApiModel() {
    }

    /**
     * 
     * @param country
     */
    public CurrentWeatherSysApiModel(String country) {
        super();
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(country);
    }

    public int describeContents() {
        return  0;
    }

}

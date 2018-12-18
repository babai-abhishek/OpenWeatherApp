
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherInfoApiModel implements Parcelable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private int weatherId;

    protected CurrentWeatherInfoApiModel(Parcel in) {
        description = in.readString();
        icon = in.readString();
        weatherId = in.readInt();
    }

    public static final Creator<CurrentWeatherInfoApiModel> CREATOR = new Creator<CurrentWeatherInfoApiModel>() {
        @Override
        public CurrentWeatherInfoApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherInfoApiModel(in);
        }

        @Override
        public CurrentWeatherInfoApiModel[] newArray(int size) {
            return new CurrentWeatherInfoApiModel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(icon);
        dest.writeInt(weatherId);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public CurrentWeatherInfoApiModel() {
    }

    /**
     * 
     * @param icon
     * @param description
     * @param main
     */
    public CurrentWeatherInfoApiModel(String main, String description, String icon) {
        super();
        this.description = description;
        this.icon = icon;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


}

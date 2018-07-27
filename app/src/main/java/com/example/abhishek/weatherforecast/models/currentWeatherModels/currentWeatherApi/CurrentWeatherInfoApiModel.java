
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi;

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
    public final static Creator<CurrentWeatherInfoApiModel> CREATOR = new Creator<CurrentWeatherInfoApiModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeatherInfoApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherInfoApiModel(in);
        }

        public CurrentWeatherInfoApiModel[] newArray(int size) {
            return (new CurrentWeatherInfoApiModel[size]);
        }

    }
    ;

    protected CurrentWeatherInfoApiModel(Parcel in) {
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(description);
        dest.writeValue(icon);
    }

    public int describeContents() {
        return  0;
    }

}

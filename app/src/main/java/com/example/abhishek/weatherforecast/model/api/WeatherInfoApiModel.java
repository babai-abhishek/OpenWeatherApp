
package com.example.abhishek.weatherforecast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherInfoApiModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("icon")
    @Expose
    private String icon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherInfoApiModel() {
    }

    /**
     * 
     * @param id
     * @param icon
     * @param description
     * @param main
     */
    public WeatherInfoApiModel(long id, String main, String description, String icon) {
        super();
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    protected WeatherInfoApiModel(Parcel in) {
        id = in.readLong();
        main = in.readString();
        description = in.readString();
        icon = in.readString();
    }

    public static final Creator<WeatherInfoApiModel> CREATOR = new Creator<WeatherInfoApiModel>() {
        @Override
        public WeatherInfoApiModel createFromParcel(Parcel in) {
            return new WeatherInfoApiModel(in);
        }

        @Override
        public WeatherInfoApiModel[] newArray(int size) {
            return new WeatherInfoApiModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(main);
        dest.writeString(description);
        dest.writeString(icon);
    }
}

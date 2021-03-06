
package com.example.abhishek.weatherforecast.Model.models.currentWeatherModels.currentWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherApiModel implements Parcelable
{


    @SerializedName("weather")
    @Expose
    private List<CurrentWeatherInfoApiModel> currentWeatherInfoApiModel = new ArrayList<CurrentWeatherInfoApiModel>();
    @SerializedName("main")
    @Expose
    private CurrentWeatherMainApiModel currentWeatherMainApiModel;
    @SerializedName("wind")
    @Expose
    private CurrentWeatherWindApiModel currentWeatherWindApiModel;
    @SerializedName("clouds")
    @Expose
    private CurrentWeatherCloudsApiModel currentWeatherCloudsApiModel;
    @SerializedName("dt")
    @Expose
    private long dt;
    @SerializedName("sys")
    @Expose
    private CurrentWeatherSysApiModel currentWeatherSysApiModel;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;


    public CurrentWeatherApiModel() {
    }


    protected CurrentWeatherApiModel(Parcel in) {
//        currentWeatherCoordApiModel = in.readParcelable(CurrentWeatherCoordApiModel.class.getClassLoader());
        currentWeatherInfoApiModel = in.createTypedArrayList(CurrentWeatherInfoApiModel.CREATOR);
        currentWeatherMainApiModel = in.readParcelable(CurrentWeatherMainApiModel.class.getClassLoader());
        currentWeatherWindApiModel = in.readParcelable(CurrentWeatherWindApiModel.class.getClassLoader());
        currentWeatherCloudsApiModel = in.readParcelable(CurrentWeatherCloudsApiModel.class.getClassLoader());
        dt = in.readLong();
        currentWeatherSysApiModel = in.readParcelable(CurrentWeatherSysApiModel.class.getClassLoader());
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<CurrentWeatherApiModel> CREATOR = new Creator<CurrentWeatherApiModel>() {
        @Override
        public CurrentWeatherApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherApiModel(in);
        }

        @Override
        public CurrentWeatherApiModel[] newArray(int size) {
            return new CurrentWeatherApiModel[size];
        }
    };


   /* public void setCurrentWeatherCoordApiModel(CurrentWeatherCoordApiModel currentWeatherCoordApiModel) {
        this.currentWeatherCoordApiModel = currentWeatherCoordApiModel;
    }*/

    public List<CurrentWeatherInfoApiModel> getCurrentWeatherInfoApiModel() {
        return currentWeatherInfoApiModel;
    }

    public void setCurrentWeatherInfoApiModel(List<CurrentWeatherInfoApiModel> currentWeatherInfoApiModel) {
        this.currentWeatherInfoApiModel = currentWeatherInfoApiModel;
    }

    public CurrentWeatherMainApiModel getCurrentWeatherMainApiModel() {
        return currentWeatherMainApiModel;
    }

    public void setCurrentWeatherMainApiModel(CurrentWeatherMainApiModel currentWeatherMainApiModel) {
        this.currentWeatherMainApiModel = currentWeatherMainApiModel;
    }

    public CurrentWeatherWindApiModel getCurrentWeatherWindApiModel() {
        return currentWeatherWindApiModel;
    }

    public void setCurrentWeatherWindApiModel(CurrentWeatherWindApiModel currentWeatherWindApiModel) {
        this.currentWeatherWindApiModel = currentWeatherWindApiModel;
    }

    public CurrentWeatherCloudsApiModel getCurrentWeatherCloudsApiModel() {
        return currentWeatherCloudsApiModel;
    }

    public void setCurrentWeatherCloudsApiModel(CurrentWeatherCloudsApiModel currentWeatherCloudsApiModel) {
        this.currentWeatherCloudsApiModel = currentWeatherCloudsApiModel;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public CurrentWeatherSysApiModel getCurrentWeatherSysApiModel() {
        return currentWeatherSysApiModel;
    }

    public void setCurrentWeatherSysApiModel(CurrentWeatherSysApiModel currentWeatherSysApiModel) {
        this.currentWeatherSysApiModel = currentWeatherSysApiModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeParcelable(currentWeatherCoordApiModel, flags);
        dest.writeTypedList(currentWeatherInfoApiModel);
        dest.writeParcelable(currentWeatherMainApiModel, flags);
        dest.writeParcelable(currentWeatherWindApiModel, flags);
        dest.writeParcelable(currentWeatherCloudsApiModel, flags);
        dest.writeLong(dt);
        dest.writeParcelable(currentWeatherSysApiModel, flags);
        dest.writeInt(id);
        dest.writeString(name);
    }
}

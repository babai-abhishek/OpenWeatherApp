
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherApiModel implements Parcelable
{

    @SerializedName("coord")
    @Expose
    private CurrentWeatherCoordApiModel currentWeatherCoordApiModel;
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
    private int dt;
    @SerializedName("sys")
    @Expose
    private CurrentWeatherSysApiModel currentWeatherSysApiModel;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Creator<CurrentWeatherApiModel> CREATOR = new Creator<CurrentWeatherApiModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CurrentWeatherApiModel createFromParcel(Parcel in) {
            return new CurrentWeatherApiModel(in);
        }

        public CurrentWeatherApiModel[] newArray(int size) {
            return (new CurrentWeatherApiModel[size]);
        }

    }
    ;

    protected CurrentWeatherApiModel(Parcel in) {
        this.currentWeatherCoordApiModel = ((CurrentWeatherCoordApiModel) in.readValue((CurrentWeatherCoordApiModel.class.getClassLoader())));
        in.readList(this.currentWeatherInfoApiModel, (CurrentWeatherInfoApiModel.class.getClassLoader()));
        this.currentWeatherMainApiModel = ((CurrentWeatherMainApiModel) in.readValue((CurrentWeatherMainApiModel.class.getClassLoader())));
        this.currentWeatherWindApiModel = ((CurrentWeatherWindApiModel) in.readValue((CurrentWeatherWindApiModel.class.getClassLoader())));
        this.currentWeatherCloudsApiModel = ((CurrentWeatherCloudsApiModel) in.readValue((CurrentWeatherCloudsApiModel.class.getClassLoader())));
        this.dt = ((int) in.readValue((int.class.getClassLoader())));
        this.currentWeatherSysApiModel = ((CurrentWeatherSysApiModel) in.readValue((CurrentWeatherSysApiModel.class.getClassLoader())));
        this.id = ((int) in.readValue((int.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherApiModel() {
    }

    /**
     * 
     * @param id
     * @param dt
     * @param clouds
     * @param currentWeatherCoordApiModel
     * @param currentWeatherWindApiModel
     * @param currentWeatherSysApiModel
     * @param name
     * @param currentWeatherInfoApiModel
     * @param currentWeatherMainApiModel
     */
    public CurrentWeatherApiModel(CurrentWeatherCoordApiModel currentWeatherCoordApiModel, List<CurrentWeatherInfoApiModel> currentWeatherInfoApiModel, CurrentWeatherMainApiModel currentWeatherMainApiModel, CurrentWeatherWindApiModel currentWeatherWindApiModel, CurrentWeatherCloudsApiModel currentWeatherCloudsApiModel, int dt, CurrentWeatherSysApiModel currentWeatherSysApiModel, int id, String name) {
        super();
        this.currentWeatherCoordApiModel = currentWeatherCoordApiModel;
        this.currentWeatherInfoApiModel = currentWeatherInfoApiModel;
        this.currentWeatherMainApiModel = currentWeatherMainApiModel;
        this.currentWeatherWindApiModel = currentWeatherWindApiModel;
        this.currentWeatherCloudsApiModel = currentWeatherCloudsApiModel;
        this.dt = dt;
        this.currentWeatherSysApiModel = currentWeatherSysApiModel;
        this.id = id;
        this.name = name;
    }

    public CurrentWeatherCoordApiModel getCurrentWeatherCoordApiModel() {
        return currentWeatherCoordApiModel;
    }

    public void setCurrentWeatherCoordApiModel(CurrentWeatherCoordApiModel currentWeatherCoordApiModel) {
        this.currentWeatherCoordApiModel = currentWeatherCoordApiModel;
    }

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

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(currentWeatherCoordApiModel);
        dest.writeList(currentWeatherInfoApiModel);
        dest.writeValue(currentWeatherMainApiModel);
        dest.writeValue(currentWeatherWindApiModel);
        dest.writeValue(currentWeatherCloudsApiModel);
        dest.writeValue(dt);
        dest.writeValue(currentWeatherSysApiModel);
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return  0;
    }

}

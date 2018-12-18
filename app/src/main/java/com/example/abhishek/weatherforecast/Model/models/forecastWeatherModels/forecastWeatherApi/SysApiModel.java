
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SysApiModel implements Parcelable{

    @SerializedName("pod")
    @Expose
    private String pod;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SysApiModel() {
    }

    /**
     * 
     * @param pod
     */
    public SysApiModel(String pod) {
        super();
        this.pod = pod;
    }

    protected SysApiModel(Parcel in) {
        pod = in.readString();
    }

    public static final Creator<SysApiModel> CREATOR = new Creator<SysApiModel>() {
        @Override
        public SysApiModel createFromParcel(Parcel in) {
            return new SysApiModel(in);
        }

        @Override
        public SysApiModel[] newArray(int size) {
            return new SysApiModel[size];
        }
    };

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pod);
    }
}

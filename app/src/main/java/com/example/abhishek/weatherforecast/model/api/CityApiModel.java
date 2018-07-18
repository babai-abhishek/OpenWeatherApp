
package com.example.abhishek.weatherforecast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityApiModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private CoordApiModel coordApiModel;
    @SerializedName("country")
    @Expose
    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CityApiModel() {
    }

    /**
     * 
     * @param coord
     * @param id
     * @param name
     * @param country
     */
    public CityApiModel(long id, String name, CoordApiModel coordApiModel, String country) {
        super();
        this.id = id;
        this.name = name;
        this.coordApiModel = coordApiModel;
        this.country = country;
    }

    protected CityApiModel(Parcel in) {
        id = in.readLong();
        name = in.readString();
        country = in.readString();
    }

    public static final Creator<CityApiModel> CREATOR = new Creator<CityApiModel>() {
        @Override
        public CityApiModel createFromParcel(Parcel in) {
            return new CityApiModel(in);
        }

        @Override
        public CityApiModel[] newArray(int size) {
            return new CityApiModel[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordApiModel getCoordApiModel() {
        return coordApiModel;
    }

    public void setCoordApiModel(CoordApiModel coordApiModel) {
        this.coordApiModel = coordApiModel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(country);
    }
}

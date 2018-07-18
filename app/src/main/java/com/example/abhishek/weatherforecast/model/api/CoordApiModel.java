
package com.example.abhishek.weatherforecast.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoordApiModel {

    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lon")
    @Expose
    private double lon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoordApiModel() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public CoordApiModel(double lat, double lon) {
        super();
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

}

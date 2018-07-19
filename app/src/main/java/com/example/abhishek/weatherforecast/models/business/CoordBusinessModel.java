
package com.example.abhishek.weatherforecast.models.business;


import com.example.abhishek.weatherforecast.models.api.CoordApiModel;

public class CoordBusinessModel {

    private double lat;
    private double lon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoordBusinessModel() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public CoordBusinessModel(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public CoordBusinessModel(CoordApiModel coordApiModel) {
        this.lat = coordApiModel.getLat();
        this.lon = coordApiModel.getLon();
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

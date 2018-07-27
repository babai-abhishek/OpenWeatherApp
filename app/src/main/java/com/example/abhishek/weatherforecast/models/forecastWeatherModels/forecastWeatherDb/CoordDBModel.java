
package com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb;

public class CoordDBModel {


    private double lat;

    private double lon;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CoordDBModel() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public CoordDBModel(double lat, double lon) {
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

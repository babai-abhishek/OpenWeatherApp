
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb;


public class CurrentWeatherCoordDBModel {

    private double lon;
    private double lat;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherCoordDBModel() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public CurrentWeatherCoordDBModel(double lon, double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}

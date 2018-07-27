
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherBusiness;


import com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherApi.CurrentWeatherCoordApiModel;

public class CurrentWeatherCoordBusinessModel {

    private double lon;
    private double lat;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherCoordBusinessModel() {
    }

    /**
     * 
     * @param lon
     * @param lat
     */
    public CurrentWeatherCoordBusinessModel(double lon, double lat) {
        super();
        this.lon = lon;
        this.lat = lat;
    }

    public CurrentWeatherCoordBusinessModel(CurrentWeatherCoordApiModel currentWeatherCoordApiModel) {
        this.lat = currentWeatherCoordApiModel.getLat();
        this.lon = currentWeatherCoordApiModel.getLon();
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

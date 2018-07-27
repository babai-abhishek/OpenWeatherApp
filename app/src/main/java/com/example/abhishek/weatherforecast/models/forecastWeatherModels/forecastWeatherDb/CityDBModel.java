
package com.example.abhishek.weatherforecast.models.forecastWeatherModels.forecastWeatherDb;

public class CityDBModel {


    private long id;

    private String name;

    private CoordDBModel coordDBModel;

    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CityDBModel() {
    }

    /**
     * 
     * @param coord
     * @param id
     * @param name
     * @param country
     */
    public CityDBModel(int id, String name, CoordDBModel coordDBModel, String country) {
        super();
        this.id = id;
        this.name = name;
        this.coordDBModel = coordDBModel;
        this.country = country;
    }

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

    public CoordDBModel getCoordDBModel() {
        return coordDBModel;
    }

    public void setCoordDBModel(CoordDBModel coordDBModel) {
        this.coordDBModel = coordDBModel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

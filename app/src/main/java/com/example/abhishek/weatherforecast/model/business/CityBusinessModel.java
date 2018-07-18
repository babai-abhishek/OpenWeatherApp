
package com.example.abhishek.weatherforecast.model.business;


import com.example.abhishek.weatherforecast.model.api.CityApiModel;

public class CityBusinessModel {

    private long id;
    private String name;
    private CoordBusinessModel coordBusinessModel;
    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CityBusinessModel() {
    }

    /**
     * 
     * @param coordBusinessModel
     * @param id
     * @param name
     * @param country
     */
    public CityBusinessModel(long id, String name, CoordBusinessModel coordBusinessModel, String country) {
        this.id = id;
        this.name = name;
        this.coordBusinessModel = coordBusinessModel;
        this.country = country;
    }

    public CityBusinessModel(CityApiModel cityApiModel) {
        this.id = cityApiModel.getId();
        this.name = cityApiModel.getName();
        this.coordBusinessModel = new CoordBusinessModel(cityApiModel.getCoordApiModel());
        this.country = cityApiModel.getCountry();
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

    public CoordBusinessModel getCoordBusinessModel() {
        return coordBusinessModel;
    }

    public void setCoordBusinessModel(CoordBusinessModel coordBusinessModel) {
        this.coordBusinessModel = coordBusinessModel;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

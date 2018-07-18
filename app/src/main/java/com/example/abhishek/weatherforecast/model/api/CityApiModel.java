
package com.example.abhishek.weatherforecast.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityApiModel {

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

}

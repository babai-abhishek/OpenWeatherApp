
package com.example.abhishek.weatherforecast.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloudsApiModel {

    @SerializedName("all")
    @Expose
    private long all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CloudsApiModel() {
    }

    /**
     * 
     * @param all
     */
    public CloudsApiModel(long all) {
        super();
        this.all = all;
    }

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }

}

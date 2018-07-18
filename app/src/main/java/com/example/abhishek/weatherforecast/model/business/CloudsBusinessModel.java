
package com.example.abhishek.weatherforecast.model.business;


import com.example.abhishek.weatherforecast.model.api.CloudsApiModel;

public class CloudsBusinessModel {

    private long all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CloudsBusinessModel() {
    }

    /**
     * 
     * @param all
     */
    public CloudsBusinessModel(long all) {
        this.all = all;
    }

    public CloudsBusinessModel(CloudsApiModel cloudsApiModel) {
        this.all = cloudsApiModel.getAll();
    }

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }

}

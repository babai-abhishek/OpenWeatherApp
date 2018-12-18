
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.CloudsApiModel;

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

//    public CloudsBusinessModel(CloudsDBModel cloudsDBModel) {
//        this.all = cloudsDBModel.getAll();
//    }

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }

}

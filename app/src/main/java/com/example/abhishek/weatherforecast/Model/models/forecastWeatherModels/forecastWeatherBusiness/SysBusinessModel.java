
package com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherBusiness;


import com.example.abhishek.weatherforecast.Model.models.forecastWeatherModels.forecastWeatherApi.SysApiModel;

public class SysBusinessModel {

    private String pod;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SysBusinessModel() {
    }

    /**
     * 
     * @param pod
     */
    public SysBusinessModel(String pod) {
        super();
        this.pod = pod;
    }

    public SysBusinessModel(SysApiModel sysApiModel) {
        this.pod = sysApiModel.getPod();
    }

/*
    public SysBusinessModel(SysDBModel sysDBModel) {
        this.pod = sysDBModel.getPod();
    }
*/

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}

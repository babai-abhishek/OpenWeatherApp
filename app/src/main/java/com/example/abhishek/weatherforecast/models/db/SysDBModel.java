
package com.example.abhishek.weatherforecast.models.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SysDBModel {


    private String pod;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SysDBModel() {
    }

    /**
     * 
     * @param pod
     */
    public SysDBModel(String pod) {
        super();
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

}

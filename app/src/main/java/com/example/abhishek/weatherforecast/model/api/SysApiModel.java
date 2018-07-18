
package com.example.abhishek.weatherforecast.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SysApiModel {

    @SerializedName("pod")
    @Expose
    private String pod;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SysApiModel() {
    }

    /**
     * 
     * @param pod
     */
    public SysApiModel(String pod) {
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

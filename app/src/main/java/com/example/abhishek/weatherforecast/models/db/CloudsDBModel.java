
package com.example.abhishek.weatherforecast.models.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CloudsDBModel {


    private long all;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CloudsDBModel() {
    }

    /**
     * 
     * @param all
     */
    public CloudsDBModel(int all) {
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

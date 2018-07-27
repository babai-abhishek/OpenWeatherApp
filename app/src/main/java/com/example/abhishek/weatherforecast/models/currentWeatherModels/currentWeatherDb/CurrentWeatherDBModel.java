
package com.example.abhishek.weatherforecast.models.currentWeatherModels.currentWeatherDb;

import java.util.ArrayList;
import java.util.List;

public class CurrentWeatherDBModel {

    private CurrentWeatherCoordDBModel currentWeatherCoordDBModel;
    private List<CurrentWeatherInfoDBModel> currentWeatherInfoDBModel = new ArrayList<CurrentWeatherInfoDBModel>();
    private CurrentWeatherMainDBModel currentWeatherMainDBModel;
    private CurrentWeatherWindDBModel currentWeatherWindDBModel;
    private CurrentWeatherCloudsDBModel currentWeatherCloudsDBModel;
    private long dt;
    private CurrentWeatherSysDBModel currentWeatherSysDBModel;
    private int id;
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CurrentWeatherDBModel() {
    }

    /**
     * 
     * @param id
     * @param dt
     * @param currentWeatherCloudsDBModel
     * @param currentWeatherCoordDBModel
     * @param currentWeatherWindDBModel
     * @param currentWeatherSysDBModel
     * @param name
     * @param currentWeatherInfoDBModel
     * @param currentWeatherMainDBModel
     */
    public CurrentWeatherDBModel(CurrentWeatherCoordDBModel currentWeatherCoordDBModel, List<CurrentWeatherInfoDBModel> currentWeatherInfoDBModel, CurrentWeatherMainDBModel currentWeatherMainDBModel, CurrentWeatherWindDBModel currentWeatherWindDBModel, CurrentWeatherCloudsDBModel currentWeatherCloudsDBModel, long dt, CurrentWeatherSysDBModel currentWeatherSysDBModel, int id, String name) {
        super();
        this.currentWeatherCoordDBModel = currentWeatherCoordDBModel;
        this.currentWeatherInfoDBModel = currentWeatherInfoDBModel;
        this.currentWeatherMainDBModel = currentWeatherMainDBModel;
        this.currentWeatherWindDBModel = currentWeatherWindDBModel;
        this.currentWeatherCloudsDBModel = currentWeatherCloudsDBModel;
        this.dt = dt;
        this.currentWeatherSysDBModel = currentWeatherSysDBModel;
        this.id = id;
        this.name = name;
    }

    public CurrentWeatherCoordDBModel getCurrentWeatherCoordDBModel() {
        return currentWeatherCoordDBModel;
    }

    public void setCurrentWeatherCoordDBModel(CurrentWeatherCoordDBModel currentWeatherCoordDBModel) {
        this.currentWeatherCoordDBModel = currentWeatherCoordDBModel;
    }

    public List<CurrentWeatherInfoDBModel> getCurrentWeatherInfoDBModel() {
        return currentWeatherInfoDBModel;
    }

    public void setCurrentWeatherInfoDBModel(List<CurrentWeatherInfoDBModel> currentWeatherInfoDBModel) {
        this.currentWeatherInfoDBModel = currentWeatherInfoDBModel;
    }

    public CurrentWeatherMainDBModel getCurrentWeatherMainDBModel() {
        return currentWeatherMainDBModel;
    }

    public void setCurrentWeatherMainDBModel(CurrentWeatherMainDBModel currentWeatherMainDBModel) {
        this.currentWeatherMainDBModel = currentWeatherMainDBModel;
    }

    public CurrentWeatherWindDBModel getCurrentWeatherWindDBModel() {
        return currentWeatherWindDBModel;
    }

    public void setCurrentWeatherWindDBModel(CurrentWeatherWindDBModel currentWeatherWindDBModel) {
        this.currentWeatherWindDBModel = currentWeatherWindDBModel;
    }

    public CurrentWeatherCloudsDBModel getCurrentWeatherCloudsDBModel() {
        return currentWeatherCloudsDBModel;
    }

    public void setCurrentWeatherCloudsDBModel(CurrentWeatherCloudsDBModel currentWeatherCloudsDBModel) {
        this.currentWeatherCloudsDBModel = currentWeatherCloudsDBModel;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public CurrentWeatherSysDBModel getCurrentWeatherSysDBModel() {
        return currentWeatherSysDBModel;
    }

    public void setCurrentWeatherSysDBModel(CurrentWeatherSysDBModel currentWeatherSysDBModel) {
        this.currentWeatherSysDBModel = currentWeatherSysDBModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

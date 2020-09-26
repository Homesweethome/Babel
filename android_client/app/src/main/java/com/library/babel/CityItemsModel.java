package com.library.babel;

public class CityItemsModel {

    public CityItemsModel(String cityName) {
        this.cityName = cityName;
    }

    private  String cityName;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}

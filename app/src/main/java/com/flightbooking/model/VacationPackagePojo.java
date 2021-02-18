package com.flightbooking.model;

import com.google.gson.annotations.SerializedName;

public class VacationPackagePojo {
    @SerializedName("image")
    public String image;

    @SerializedName("agencyname")
    public String agencyname;

    @SerializedName("cityname")
    public String cityname;

    @SerializedName("cost")
    public String cost;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAgencyname() {
        return agencyname;
    }

    public void setAgencyname(String agencyname) {
        this.agencyname = agencyname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public VacationPackagePojo(String image, String agencyname, String cityname, String cost) {
        this.image = image;
        this.agencyname = agencyname;
        this.cityname = cityname;
        this.cost = cost;
    }
}

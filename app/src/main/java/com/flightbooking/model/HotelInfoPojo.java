package com.flightbooking.model;

import com.google.gson.annotations.SerializedName;

public class HotelInfoPojo {

    @SerializedName("city")
    public String city;

    @SerializedName("country")
    public String country;

    @SerializedName("hid")
    public String hid;

    @SerializedName("name")
    public String name;

    @SerializedName("pcode")
    public String pcode;

    @SerializedName("photo")
    public String photo;

    @SerializedName("price")
    public String price;

    @SerializedName("province")
    public String province;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

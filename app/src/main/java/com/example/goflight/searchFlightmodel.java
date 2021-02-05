package com.example.goflight;

public class searchFlightmodel {
    public String placeName;
    public String countryName;

    public searchFlightmodel(String placeName, String countryName) {
        this.placeName = placeName;
        this.countryName = countryName;
    }

    public searchFlightmodel() {
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

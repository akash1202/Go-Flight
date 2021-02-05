package com.example.goflight;

public class hotelmodel {
    public  String hotelid;
    public  String hotelname;
    public  String hotelcountry;
    public  String hotelprovince;
    public  String hotelcity;
    public  String hotelpostalcode;
    public  String hotelprice;
    public String imageURL;
    public hotelmodel(){}

    public hotelmodel(String hotelid, String hotelname, String hotelcountry, String hotelprovince, String hotelcity, String hotelpostalcode, String hotelprice, String imageURL) {
        this.hotelid = hotelid;
        this.hotelname = hotelname;
        this.hotelcountry = hotelcountry;
        this.hotelprovince = hotelprovince;
        this.hotelcity = hotelcity;
        this.hotelpostalcode = hotelpostalcode;
        this.hotelprice = hotelprice;
        this.imageURL = imageURL;
    }



    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getHotelcountry() {
        return hotelcountry;
    }

    public void setHotelcountry(String hotelcountry) {
        this.hotelcountry = hotelcountry;
    }

    public String getHotelprovince() {
        return hotelprovince;
    }

    public void setHotelprovince(String hotelprovince) {
        this.hotelprovince = hotelprovince;
    }

    public String getHotelcity() {
        return hotelcity;
    }

    public void setHotelcity(String hotelcity) {
        this.hotelcity = hotelcity;
    }

    public String getHotelpostalcode() {
        return hotelpostalcode;
    }

    public void setHotelpostalcode(String hotelpostalcode) {
        this.hotelpostalcode = hotelpostalcode;
    }

    public String getHotelprice() {
        return hotelprice;
    }

    public void setHotelprice(String hotelprice) {
        this.hotelprice = hotelprice;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
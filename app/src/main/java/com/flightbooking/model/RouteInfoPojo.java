package com.flightbooking.model;

import com.google.gson.annotations.SerializedName;

public class RouteInfoPojo {
    @SerializedName("airport")
    public String airport;

    @SerializedName("airways")
    public String airways;

    @SerializedName("destination")
    public String destination;

    @SerializedName("frmtim")
    public String frmtim;

    @SerializedName("price")
    public String price;

    @SerializedName("rid")
    public String rid;

    @SerializedName("source")
    public String source;

    @SerializedName("tdays")
    public String tdays;

    @SerializedName("totim")
    public String totim;

    @SerializedName("type")
    public String type;

    @SerializedName("layour")
    private String layour;

    @SerializedName("stops")
    private String stops;



    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getAirways() {
        return airways;
    }

    public void setAirways(String airways) {
        this.airways = airways;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFrmtim() {
        return frmtim;
    }

    public void setFrmtim(String frmtim) {
        this.frmtim = frmtim;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTdays() {
        return tdays;
    }

    public void setTdays(String tdays) {
        this.tdays = tdays;
    }

    public String getTotim() {
        return totim;
    }

    public void setTotim(String totim) {
        this.totim = totim;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLayour() {
        return layour;
    }

    public void setLayour(String layour) {
        this.layour = layour;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }
}

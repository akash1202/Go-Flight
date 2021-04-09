package com.flightbooking.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class BookingsPojo {
    @SerializedName("adult")//
    public String adult;

    @SerializedName("airways")
    public String airways;

    @SerializedName("destination")
    public String destination;

    @SerializedName("frmtim")
    public String frmtim;

    @SerializedName("layour")
    public String layour;

    @SerializedName("price")
    public String price;

   @SerializedName("source")
    public String source;

   @SerializedName("stops")
    public String stops;

    @SerializedName("tdays")
    public String tdays;

    @SerializedName("total")//
    public String total;

    @SerializedName("totim")
    public String totim;

    @SerializedName("type")
    public String type;

    @SerializedName("uname")
    public String uname;

    @SerializedName("bid")
    public String bid;

    @SerializedName("child")//
    public String child;

    @SerializedName("clas")//
    public String clas;

    @SerializedName("dat")//
    public String dat;

    @SerializedName("extra")//
    public String extra;

    @SerializedName("name")//
    public String name;

    @SerializedName("passcountry")//
    public String passcountry;

    @SerializedName("passno")//
    public String passno;

    @SerializedName("rid")//
    public String rid;

    @SerializedName("status")//
    public String status;

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
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

    public String getLayour() {
        return layour;
    }

    public void setLayour(String layour) {
        this.layour = layour;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStops() {
        return stops;
    }

    public void setStops(String stops) {
        this.stops = stops;
    }

    public String getTdays() {
        return tdays;
    }

    public void setTdays(String tdays) {
        this.tdays = tdays;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasscountry() {
        return passcountry;
    }

    public void setPasscountry(String passcountry) {
        this.passcountry = passcountry;
    }

    public String getPassno() {
        return passno;
    }

    public void setPassno(String passno) {
        this.passno = passno;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

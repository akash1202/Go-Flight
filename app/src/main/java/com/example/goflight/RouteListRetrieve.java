package com.example.goflight;

public class RouteListRetrieve {
    public String routeid;
    public String departureprovince;
    public String departurecity;
    public String departureairport;
    public String arrivalprovince;
    public String arrivalcity;
    public String arrivalairport;

    public RouteListRetrieve(String routeid, String departureprovince, String departurecity, String departureairport, String arrivalprovince, String arrivalcity, String arrivalairport) {
        this.routeid = routeid;
        this.departureprovince = departureprovince;
        this.departurecity = departurecity;
        this.departureairport = departureairport;
        this.arrivalprovince = arrivalprovince;
        this.arrivalcity = arrivalcity;
        this.arrivalairport = arrivalairport;
    }

    public RouteListRetrieve() {
    }


    public String getRouteid() {
        return routeid;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getDepartureprovince() {
        return departureprovince;
    }

    public void setDepartureprovince(String departureprovince) {
        this.departureprovince = departureprovince;
    }

    public String getDeparturecity() {
        return departurecity;
    }

    public void setDeparturecity(String departurecity) {
        this.departurecity = departurecity;
    }

    public String getDepartureairport() {
        return departureairport;
    }

    public void setDepartureairport(String departureairport) {
        this.departureairport = departureairport;
    }

    public String getArrivalprovince() {
        return arrivalprovince;
    }

    public void setArrivalprovince(String arrivalprovince) {
        this.arrivalprovince = arrivalprovince;
    }

    public String getArrivalcity() {
        return arrivalcity;
    }

    public void setArrivalcity(String arrivalcity) {
        this.arrivalcity = arrivalcity;
    }

    public String getArrivalairport() {
        return arrivalairport;
    }

    public void setArrivalairport(String arrivalairport) {
        this.arrivalairport = arrivalairport;
    }
}

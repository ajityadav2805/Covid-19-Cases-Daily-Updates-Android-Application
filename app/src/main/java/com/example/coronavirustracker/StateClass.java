package com.example.coronavirustracker;

public class StateClass {
    private long totalCases;
    private long confirmedIndian;
    private long confirmedForeign;
    private long deaths;
    private long discharge;
    private String location;
    private String date;

    public StateClass(long totalCases, long confirmedIndian, long confirmedForeign, long deaths, long discharge, String location,String date) {
        this.totalCases = totalCases;
        this.confirmedIndian = confirmedIndian;
        this.confirmedForeign = confirmedForeign;
        this.deaths = deaths;
        this.discharge = discharge;
        this.location = location;
        this.date=date;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(long totalCases) {
        this.totalCases = totalCases;
    }

    public long getConfirmedIndian() {
        return confirmedIndian;
    }

    public void setConfirmedIndian(long confirmedIndian) {
        this.confirmedIndian = confirmedIndian;
    }

    public long getConfirmedForeign() {
        return confirmedForeign;
    }

    public void setConfirmedForeign(long confirmedForeign) {
        this.confirmedForeign = confirmedForeign;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getDischarge() {
        return discharge;
    }

    public void setDischarge(long discharge) {
        this.discharge = discharge;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

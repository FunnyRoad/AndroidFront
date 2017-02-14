package com.project.application.funnyroad.newroadtrip.visualroadtrip.model;

/**
 * Created by oraberkane on 14/02/2017.
 */

public class Endroit {


    private String name;
    private String description;
    private double longitude;
    private double latitude;


    public Endroit(String name, String description, double longitude, double latitude) {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Endroit(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

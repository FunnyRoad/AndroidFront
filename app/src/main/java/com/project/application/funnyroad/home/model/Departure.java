package com.project.application.funnyroad.home.model;

import java.io.Serializable;

/**
 * Created by you on 02/03/2017.
 */

public class Departure implements Serializable {

    private int id;
    private double latitude;
    private double longitude;
    private String googleId;
    private RoadTrip roadtrip;

    public Departure(double latitude, double longitude, String googleId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.googleId = googleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public RoadTrip getRoadtrip() {
        return roadtrip;
    }

    public void setRoadtrip(RoadTrip roadtrip) {
        this.roadtrip = roadtrip;
    }
}

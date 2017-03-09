package com.project.application.funnyroad.detailroadtripnew.view;

/**
 * Created by sameur on 27/02/2017.
 */

public class AutoCompletePlace {

    private String id;
    private String description;
    private String descriptionBegin;
    private double latitude;
    private double longitude;

    public AutoCompletePlace( String id, String description) {
        this.id = id;
        this.description = description;
        this.descriptionBegin = descriptionBegin;
    }

    public AutoCompletePlace( double latitude, double longitude ) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getDescriptionBegin() {
        return descriptionBegin;
    }

    public void setDescriptionBegin(String descriptionBegin) {
        this.descriptionBegin = descriptionBegin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
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
}

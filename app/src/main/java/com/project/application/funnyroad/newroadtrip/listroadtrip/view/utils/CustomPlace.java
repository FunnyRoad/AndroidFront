package com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils;

/**
 * Created by sameur on 06/03/2017.
 */

public class CustomPlace {

    //ATTRIBUTES

    private String placeId;
    private String placeName;
    private String placeGrade;
    private String placeType;

    //CONSTRUCTOR

    public CustomPlace(String placeId, String placeName, String placeGrade, String placeType) {
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeGrade = placeGrade;
        this.placeType = placeType;
    }

    //METHODS

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceGrade() {
        return placeGrade;
    }

    public void setPlaceGrade(String placeGrade) {
        this.placeGrade = placeGrade;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
}

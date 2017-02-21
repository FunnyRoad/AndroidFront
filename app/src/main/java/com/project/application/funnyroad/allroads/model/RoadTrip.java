package com.project.application.funnyroad.allroads.model;

import java.io.Serializable;

/**
 * Created by you on 18/02/2017.
 */

public class RoadTrip implements Serializable {

    private String begin;
    private String destination;
    private String description;

    public  RoadTrip(String begin , String destination , String description){
        this.begin = begin;
        this.destination = destination;
        this.description = description;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

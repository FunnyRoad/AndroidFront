package com.project.application.funnyroad.home.model;

import android.graphics.Bitmap;

import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by you on 18/02/2017.
 */

public class RoadTrip implements Serializable {

    private String begin;
    private String destination;
    private String description;
    private ArrayList<Bitmap> listPhotos;
    private ArrayList<Place> listPlace;

    public RoadTrip(String begin , String destination , String description){
        this.begin = begin;
        this.destination = destination;
        this.description = description;
    }

    public RoadTrip(String begin, String destination, String description, ArrayList<Place> listPlace) {
        this.begin = begin;
        this.destination = destination;
        this.description = description;
        this.listPlace = listPlace;
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

    public ArrayList<Place> getListPlace() {
        return listPlace;
    }

    public void setListPlace(ArrayList<Place> listPlace) {
        this.listPlace = listPlace;
    }

    public ArrayList<Bitmap> getListPhotos() {
        return listPhotos;
    }

    public void setListPhotos(ArrayList<Bitmap> listPhotos) {
        this.listPhotos = listPhotos;
    }
}

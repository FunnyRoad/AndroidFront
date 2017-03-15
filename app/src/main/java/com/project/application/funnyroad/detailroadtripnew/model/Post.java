package com.project.application.funnyroad.detailroadtripnew.model;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.home.model.RoadTrip;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by oa on 13/03/2017.
 */

public class Post implements Serializable {

    private int id;
    private String text;
    private ArrayList<Integer> picturesId;
    private int roadtripId;

    public Post(int id, String text, ArrayList<Integer> pictures, int roadtripId) {
        this.id = id;
        this.text = text;
        this.picturesId = pictures;
        this.roadtripId = roadtripId;
    }

    public Post(String text, int roadtripId) {
        this.text = text;
        this.roadtripId = roadtripId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Integer> getPictures() {
        return picturesId;
    }

    public void setPictures(ArrayList<Integer> pictures) {
        this.picturesId = pictures;
    }

    public int getRoadtripId() {
        return roadtripId;
    }

    public void setRoadtripId(int roadtripId) {
        this.roadtripId = roadtripId;
    }
}

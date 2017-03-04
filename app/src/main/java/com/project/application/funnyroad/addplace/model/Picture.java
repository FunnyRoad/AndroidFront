package com.project.application.funnyroad.addplace.model;

/**
 * Created by Oa on 28/02/2017.
 */

public class Picture {

    private int id;
    private String type;
    private int place;

    public Picture(String type, int place) {
        this.type = type;
        this.place = place;
    }

    public Picture(int id, String type, int place) {
        this.id = id;
        this.type = type;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }
}

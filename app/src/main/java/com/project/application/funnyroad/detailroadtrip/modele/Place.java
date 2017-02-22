package com.project.application.funnyroad.detailroadtrip.modele;

import android.graphics.Bitmap;

/**
 * Created by oraberkane on 04/02/2017.
 */

public class Place {

    //ATTRIBUTES

    private Long id;

    private String name;

    private String description;

    private Double grade;

    private Bitmap photo;

    //METHODS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}

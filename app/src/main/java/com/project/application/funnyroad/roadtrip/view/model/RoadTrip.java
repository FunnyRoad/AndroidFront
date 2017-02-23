package com.project.application.funnyroad.roadtrip.view.model;

import android.graphics.Bitmap;

/**
 * Created by sameur on 20/02/2017.
 */

public class RoadTrip {

    //ATTRIBUTES

    private Long rid;

    private String rname;

    private Bitmap photo;

    //METHODS

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return rname;
    }

    public void setName(String rname) {
        this.rname = rname;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}

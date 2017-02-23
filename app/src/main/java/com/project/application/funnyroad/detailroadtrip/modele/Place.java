package com.project.application.funnyroad.detailroadtrip.modele;

import android.graphics.Bitmap;

/**
 * Created by oraberkane on 04/02/2017.
 */

public class Place {

    //ATTRIBUTES

    private Long pid;

    private String pname;

    private String pdescription;

    private Double pgrade;

    private Bitmap photo;

    //METHODS

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return pname;
    }

    public void setName(String pname) {
        this.pname = pname;
    }

    public String getDescription() {
        return pdescription;
    }

    public void setDescription(String pdescription) {
        this.pdescription = pdescription;
    }

    public Double getGrade() {
        return pgrade;
    }

    public void setGrade(Double pgrade) {
        this.pgrade = pgrade;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}

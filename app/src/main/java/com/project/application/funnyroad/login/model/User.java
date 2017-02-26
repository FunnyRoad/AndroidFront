package com.project.application.funnyroad.login.model;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by oraberkane on 02/02/2017.
 */

public class User {

    private int id;
    private String firebaseId;
    private String mail;
    private String firtName;
    private String lastName;
    private String username;
    private Date birthDate;
    private String password;
    private ArrayList<RoadTrip> listRoadTrip;

    public User(String firebaseId, String mail, String firtName, String lastName,
                String username, Date birthDate, String password, ArrayList<RoadTrip> listRoadTrip)
    {
        this.firebaseId = firebaseId;
        this.mail = mail;
        this.firtName = firtName;
        this.lastName = lastName;
        this.username = username;
        this.birthDate = birthDate;
        this.password = password;
        this.listRoadTrip = listRoadTrip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<RoadTrip> getListRoadTrip() {
        return listRoadTrip;
    }

    public void setListRoadTrip(ArrayList<RoadTrip> listRoadTrip) {
        this.listRoadTrip = listRoadTrip;
    }
}

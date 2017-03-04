package com.project.application.funnyroad.login.model;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by oraberkane on 02/02/2017.
 */

public class User implements Serializable{

    private int id;
    private String firebaseId;
    private String mail;
    private String firstName;
    private String lastName;
    private String username;
    private Date birthDate;
    private String city;
    private ArrayList<RoadTrip> listRoadTrip;

    public User(int id , String mail, String firebaseId, String firstName, String lastName,
                String username, Date birthDate,String city)
    {
        this.id = id;
        this.firebaseId = firebaseId;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.birthDate = birthDate;
        this.city = city;
    }

    public User(int id , String mail, String firebaseId, String firstName, String lastName,
                String username, Date birthDate)
    {
        this.id = id;
        this.firebaseId = firebaseId;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.birthDate = birthDate;
    }

    public User(String mail, String firebaseId, String firstName, String lastName,
                String username, Date birthDate,String city)
    {
        this.firebaseId = firebaseId;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.birthDate = birthDate;
        this.city = city;
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
        return firstName;
    }

    public void setFirtName(String firstName) {
        this.firstName = firstName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<RoadTrip> getListRoadTrip() {
        return listRoadTrip;
    }

    public void setListRoadTrip(ArrayList<RoadTrip> listRoadTrip) {
        this.listRoadTrip = listRoadTrip;
    }
}

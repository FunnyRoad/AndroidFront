package com.project.application.funnyroad.newroadtrip.visualroadtrip.model;

import android.annotation.SuppressLint;
/*<<<<<<< HEAD
import android.os.Parcel;
import android.os.Parcelable;*/

//=======
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.project.application.funnyroad.addplace.model.Picture;

import java.util.ArrayList;

//>>>>>>> ItinéraireMap
/**
 * Created by oraberkane on 14/02/2017.
 */

@SuppressLint("ParcelCreator")
public class Place implements Parcelable {

//<<<<<<< HEAD

//=======
    private int id;
//>>>>>>> ItinéraireMap*/
    private String name;
    private String description;
    private double longitude;
    private double latitude;
//<<<<<<< HEAD

//=======
    private double grade;
    private String type;
    private ArrayList<Picture> pictures;

    public Place(String name , double latitude, double longitude ,String description, double grade ,String type ){
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.grade = grade;
        this.type = type;
    }
//>>>>>>> ItinéraireMap

    public Place(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Place(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Place(Parcel in) {
        super();
        readFromParcel(in);
    }

//<<<<<<< HEAD
//=======
    public Place(){

    }

//>>>>>>> ItinéraireMap

    public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>() {
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }

    };

    public void readFromParcel(Parcel in) {
        name = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);

    }

//<<<<<<< HEAD
//=======
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

//>>>>>>> ItinéraireMap
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

//<<<<<<< HEAD
//=======

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

//>>>>>>> ItinéraireMap
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}

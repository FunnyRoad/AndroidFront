package com.project.application.funnyroad.newroadtrip.visualroadtrip.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by oraberkane on 14/02/2017.
 */

@SuppressLint("ParcelCreator")
public class Endroit implements Parcelable {


    private String name;
    private String description;
    private double longitude;
    private double latitude;


    public Endroit(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Endroit(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Endroit(Parcel in) {
        super();
        readFromParcel(in);
    }


    public static final Parcelable.Creator<Endroit> CREATOR = new Parcelable.Creator<Endroit>() {
        public Endroit createFromParcel(Parcel in) {
            return new Endroit(in);
        }

        public Endroit[] newArray(int size) {
            return new Endroit[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Endroit{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}

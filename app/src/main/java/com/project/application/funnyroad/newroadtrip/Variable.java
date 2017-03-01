package com.project.application.funnyroad.newroadtrip;

import android.app.Application;

import com.google.android.gms.location.places.Place;

/**
 * Created by sameur on 01/03/2017.
 */

public class Variable extends Application {

    public Place placeDeparture;
    public Place placeArrival;

    public Place getPlaceDeparture() {
        return placeDeparture;
    }

    public void setPlaceDeparture(Place placeDeparture) {
        this.placeDeparture = placeDeparture;
    }

    public Place getPlaceArrival() {
        return placeArrival;
    }

    public void setPlaceArrival(Place placeArrival) {
        this.placeArrival = placeArrival;
    }
}

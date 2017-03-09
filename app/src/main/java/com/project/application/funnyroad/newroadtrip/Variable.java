package com.project.application.funnyroad.newroadtrip;

import android.app.Activity;
import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils.CustomPlace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sameur on 01/03/2017.
 */

public class Variable extends Application {

    //ATTRIBUTES

    public static final String TYPE_AMUSEMENT_PARK = "amusement_park";
    public static final String TYPE_AQUARIUM = "aquarium";
    public static final String TYPE_ART_GALLERY = "art_gallery";
    public static final String TYPE_CAMPGROUND = "campground";
    public static final String TYPE_MUSEUM ="museum";
    public static final String TYPE_PARK = "park";
    public static final String TYPE_STADIUM = "stadium";
    public static final String TYPE_ZOO = "zoo";

    public HashMap<String,Boolean> mapTypes = new HashMap<String, Boolean>();

    public Place placeDeparture;
    public Place placeArrival;

    public List<CustomPlace> listPlacesFromQuery = new ArrayList<CustomPlace>();
    public List<CustomPlace> listPlacesWithFilter = new ArrayList<CustomPlace>();
    public List<String> idPlacesOnTrack = new ArrayList<String>();

    public List<LatLng> placesOnTrack = new ArrayList<LatLng>();

    public GoogleApiClient googleApiClient;

    public GoogleMap gMap;
    //METHODS

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

    public List<CustomPlace> getListPlacesFromQuery() {
        return listPlacesFromQuery;
    }

    public void setListPlacesFromQuery(List<CustomPlace> listPlacesFromQuery) {
        this.listPlacesFromQuery = listPlacesFromQuery;
    }

    public List<CustomPlace> getListPlacesWithFilter() {
        return listPlacesWithFilter;
    }

    public void setListPlacesWithFilter(List<CustomPlace> listPlacesWithFilter) {
        this.listPlacesWithFilter = listPlacesWithFilter;
    }

    public void removePlacesToPlacesWithFilter(String type) {
        for (int i=0; i < this.listPlacesWithFilter.size(); i++) {
            if (type.equals(this.listPlacesWithFilter.get(i).getPlaceType()))
                this.listPlacesWithFilter.remove(i);
        }
    }

    public void addPlacesToPlacesWithFilter(String type, List<CustomPlace> listPlaces) {
        for (int i=0; i < listPlaces.size(); i++) {
            if (type.equals(listPlaces.get(i).getPlaceType()) && !this.listPlacesWithFilter.contains(listPlaces.get(i))) {
                this.listPlacesWithFilter.add(listPlaces.get(i));
            }
        }
    }

    public List<String> getIdPlacesOnTrack() {
        return idPlacesOnTrack;
    }

    public void setIdPlacesOnTrack(List<String> idPlacesOnTrack) {
        this.idPlacesOnTrack = idPlacesOnTrack;
    }

    public List<LatLng> getPlacesOnTrack() {
        return placesOnTrack;
    }

    public void setPlacesOnTrack(List<LatLng> placesOnTrack) {
        this.placesOnTrack = placesOnTrack;
    }

    public void addPlaceOnTrack(LatLng latLng) {
        this.placesOnTrack.add(latLng);
    }

    public HashMap<String,Boolean> getMapTypes() {
        return mapTypes;
    }

    public void setListTypes() {
        this.mapTypes.put(TYPE_AMUSEMENT_PARK,true);
        this.mapTypes.put(TYPE_AQUARIUM,true);
        this.mapTypes.put(TYPE_ART_GALLERY,true);
        this.mapTypes.put(TYPE_CAMPGROUND,true);
        this.mapTypes.put(TYPE_MUSEUM,true);
        this.mapTypes.put(TYPE_PARK,true);
        this.mapTypes.put(TYPE_STADIUM,true);
        this.mapTypes.put(TYPE_ZOO,true);
    }

    public void removeType(String type) {
        this.mapTypes.remove(type);
    }

    public void addType(String type) {
        this.mapTypes.put(type,true);
    }

    public String parseType(int type) {
        switch(type) {
            case 3 : return "Parc d'attractions";
            case 4 : return "Aquarium";
            case 5 : return "Galerie d'art";
            case 16 : return "Camping";
            case 66 : return "Musée";
            case 69 : return "Parc";
            case 86 : return "Stade";
            case 96 : return "Zoo";
            default : return "";
        }
    }

    public boolean existType(String type) {
        if (mapTypes.get(type).booleanValue()) {
            return true;
        }
        else {
            return false;
        }
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
    }

    public GoogleMap getMap() {
        return this.gMap;
    }

    public void setMap(GoogleMap gMap) {
        this.gMap = gMap;
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
}

package com.project.application.funnyroad.newroadtrip.listroadtrip.view.utils;

import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sameur on 28/02/2017.
 */

public class PlaceJSONParser {

    /** Receives a JSONObject and returns a list */
    public List<HashMap<String,String>> parse(JSONObject jObject){

        JSONArray jPlaces = null;
        try {
            /** Retrieves all the elements in the 'places' array */
            jPlaces = jObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /** Invoking getPlaces with the array of json object
         * where each json object represent a place
         */
        return getPlaces(jPlaces);
    }

    private List<HashMap<String,String>> getPlaces(JSONArray jPlaces){
        int placesCount = jPlaces.length();
        List<HashMap<String,String>> placesList = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> place = null;

        /** Taking each place, parses and adds to list object */
        for(int i=0; i<placesCount;i++){
            try {
                /** Call getPlace with place JSON object to parse the place */
                place = getPlace((JSONObject)jPlaces.get(i));
                placesList.add(place);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placesList;
    }

    /** Parsing the CustomPlace JSON object */
    private HashMap<String,String> getPlace(JSONObject jPlace){
        HashMap<String, String> place = new HashMap<String,String>();
        String placeId = "";
        String placeName = "";
        String placeGrade = "";
        JSONArray placeTypes = new JSONArray();
        String placeType = "";

        try {
            placeId = jPlace.getString("place_id");
            placeName = jPlace.getString("name");
            placeGrade = String.valueOf(jPlace.getDouble("rating"));
            placeTypes = jPlace.getJSONArray("types");
            for (int i=0;i<placeTypes.length();i++){
                switch ((String) placeTypes.get(i)) {
                    case "amusement_park": placeType = (String) placeTypes.get(i);break;
                    case "aquarium": placeType = (String) placeTypes.get(i);break;
                    case "art_gallery": placeType = (String) placeTypes.get(i);break;
                    case "campground": placeType = (String) placeTypes.get(i);break;
                    case "museum": placeType = (String) placeTypes.get(i);break;
                    case "park": placeType = (String) placeTypes.get(i);break;
                    case "stadium": placeType = (String) placeTypes.get(i);break;
                    case "zoo": placeType = (String) placeTypes.get(i);break;
                }
            }

            place.put("place_id", placeId);
            place.put("place_name", placeName);
            place.put("place_grade", placeGrade);
            place.put("place_type", placeType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }
}

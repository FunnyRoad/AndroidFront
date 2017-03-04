package com.project.application.funnyroad.newroadtrip.listroadtrip.view.service;

import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by you on 25/02/2017.
 */

public interface IWebServiceListRoadTrip {

    /**
     * renvoie la liste des places entre une ville de depart et une ville d'arrivée
     * @param departure
     * @param arrival
     * @param listPlaces
     */
    @GET("/places/{departure}/{arrival}")
    public void listPlacesBetweenDepArr(@Path("departure") String departure , @Path("arrival") String arrival,
                                        Callback<ArrayList<Place>> listPlaces);

    /**
     * creation d'un nouveau road trip
     */

    @POST("/roadtrip")
    public void createRoadTrip(@Body RoadTrip roadTrip, Callback<RoadTrip> callback);
}
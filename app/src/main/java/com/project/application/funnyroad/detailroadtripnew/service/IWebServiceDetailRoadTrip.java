package com.project.application.funnyroad.detailroadtripnew.service;

import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by you on 25/02/2017.
 */

public interface IWebServiceDetailRoadTrip{

    @GET("/roadtrip/{roadtripId}/places")
    public void getPlacesByRoadTripId(@Path("roadtripId") int roadtripId , Callback<ArrayList<Place>> callback);
}

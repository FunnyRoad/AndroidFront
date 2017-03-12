package com.project.application.funnyroad.home.allroadtrip.service;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by you on 25/02/2017.
 */

public interface IWebServiceAllRoadTrip {

    @GET("/roadtrips")
    public void allRoadTrip(Callback<ArrayList<RoadTrip>> callback);

    @PUT("/follower/{userId}/roadtrip/{roadtripId}")
    public void addGuestToRoad(@Path("userId") int id , @Path("roadtripId") int roadtripId, Callback<Object> callback );


}

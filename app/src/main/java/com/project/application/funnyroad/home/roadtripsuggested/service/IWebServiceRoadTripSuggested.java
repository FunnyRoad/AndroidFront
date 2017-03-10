package com.project.application.funnyroad.home.roadtripsuggested.service;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by oa on 25/02/2017.
 */

public interface IWebServiceRoadTripSuggested{

    @GET("/roadtrip/user/{userId}/nearest/{latitude}/{longitude}/{distance}")
    public void allRoadTripSuggested(@Path("userId") int userId ,@Path("latitude") double latitude,
                                     @Path("longitude") double longitude,
                                     @Path("distance") double distance, Callback<ArrayList<RoadTrip>> callback);

    @PUT("/guest/{guestId}/roadTrip/{roadtripId}")
    public void addGuestToRoad(@Path("guestId") int id , @Path("roadtripId") int roadtripId, Callback<Object> callback );
}

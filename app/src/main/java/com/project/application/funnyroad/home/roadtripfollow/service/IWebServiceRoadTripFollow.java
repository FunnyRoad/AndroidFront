package com.project.application.funnyroad.home.roadtripfollow.service;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by you on 06/03/2017.
 */

public interface IWebServiceRoadTripFollow {

    @GET("/follower/{userId}/roadtrips")
    public void getRoadTripFollowed(@Path("userId") int userId, Callback<ArrayList<RoadTrip>> callback);

    @DELETE("/follower/{userId}/roadtrip/{roadtripId}")
    public void deleteRoadTripFollowed(@Path("userId") int userId , @Path("roadtripId") int roadtripId ,
                                       Callback<Object> callback);
}

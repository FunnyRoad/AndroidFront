package com.project.application.funnyroad.home.allroadtrip.service;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by you on 25/02/2017.
 */

public interface IWebServiceAllRoadTrip {

    @GET("/roadtrips")
    public void allRoadTrip(Callback<ArrayList<RoadTrip>> callback);
}

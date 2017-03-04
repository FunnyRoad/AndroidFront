package com.project.application.funnyroad.home.roadtripsuggested.service;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by you on 25/02/2017.
 */

public interface IWebServiceRoadTripSuggested{

    @GET("/roadtrips/{cityId}")
    public void allRoadTripSuggested(@Path("cityId") String cityId , Callback<ArrayList<RoadTrip>> callback);
}

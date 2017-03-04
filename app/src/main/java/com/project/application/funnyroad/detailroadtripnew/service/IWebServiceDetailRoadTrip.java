package com.project.application.funnyroad.detailroadtripnew.service;

import android.graphics.Bitmap;

import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by you on 25/02/2017.
 */

public interface IWebServiceDetailRoadTrip{

    @GET("/roadtrip/{roadtripId}/places")
    public void getPlacesByRoadTripId(@Path("roadtripId") int roadtripId , Callback<ArrayList<Place>> callback);

    @GET("/roadtrip/{roadtripId}/places")
    public void getPhotosByRoadTripId(@Path("roadtripId") int roadtripId , Callback<ArrayList<Bitmap>> callback);

    @PUT("/roadtrip")
    public void updateRoadTrip(@Body RoadTrip newRoadTrip , Callback<RoadTrip> callback);

    @DELETE("roadtrip/{id}")
    public void deleteRoadTrip(@Path("id") int id , Callback<String> callback);
}

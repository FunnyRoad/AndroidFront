package com.project.application.funnyroad.addplace.service;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by you on 28/02/2017.
 */

public interface IWebServiceAddPlace {

    @POST("/place")
    public void addPlace(@Body Place place , Callback<Place> callback);

    @POST("/roadtrip/{roadId}/place/{placeId}")
    public void addPlaceToRoadtrip(@Path("roadId") int roadId , @Path("placeId") int placeId , Callback<RoadTrip> callback);

    @POST("/place/{placeId}/picture")
    public void addImageToPlace(@Path("placeId") int placeId , Callback<Picture> callback);
}

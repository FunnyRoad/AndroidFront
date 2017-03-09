package com.project.application.funnyroad.detailEndroit.service;

import android.graphics.Bitmap;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by you on 04/03/2017.
 */

public interface IWebServiceDetailPlace {

    @GET("/place/{placeId}/pictures")
    public void getPictures(@Path("placeId") int placeId , Callback<ArrayList<Picture>> callback);

    @GET("/picture/{pictureId}")
    public void getPicture(@Path("pictureId") int pictureId , Callback<String> callback);

    @PUT("/place")
    public void uploadPlace(@Body Place place , Callback<Place> callback);

    @DELETE("/roadtrip/{roadtripId}/place/{placeId}")
    public void deletePlace(@Path("roadtripId") int roadtripId , @Path("placeId") int id , Callback<Void> callback);
}

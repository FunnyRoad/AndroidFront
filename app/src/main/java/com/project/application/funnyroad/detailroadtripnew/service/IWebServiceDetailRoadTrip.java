package com.project.application.funnyroad.detailroadtripnew.service;

import android.graphics.Bitmap;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.detailroadtripnew.model.Post;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

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

    @DELETE("/roadtrip/{id}")
    public void deleteRoadTrip(@Path("id") int id , Callback<Object> callback);

    @GET("/roadtrip/{id}")
    public void getRoadTripById(@Path("id") int id , Callback<RoadTrip> callback);

    @POST("/post")
    public void sendPost(@Body Post post , Callback<Post> callback);

    @GET("/roadtrip/{roadtrip}/posts")
    public void getRoadTripPosts(@Path("roadtrip") int roadtripId , Callback<ArrayList<Post>> callback);

    @Multipart
    @POST("/post/{postId}/picture")
    public void addPictureToPost(@Path("postId") int postId , @Part("picture") TypedFile image, Callback<Picture> callback);

    @GET("/post/{post}/pictures")
    public void getAllPictures(@Path("post") int postId , Callback<ArrayList<Picture>> callback);

    @GET("/posts")
    public void getAllPosts(Callback<ArrayList<Post>> callback);
}

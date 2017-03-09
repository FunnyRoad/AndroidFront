package com.project.application.funnyroad.login.service;

import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.login.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by aberkane on 03/02/17.
 */

public interface IWebServiceLogin {

    @POST("/user")
    public void userLogin(@Body User user , Callback<User> callback);

    @POST("/roadtrip")
    public void createRoadTrip(@Body RoadTrip roadTrip, Callback<RoadTrip> callback);

    @DELETE("/roadtrip/{roadtripId}")
    public void delete(@Path("roadtripId") int roadtripId , Callback<String> callback);

    @GET("/users")
    public void getUsers(Callback<ArrayList<User>> callback);

    @DELETE("/user/{id}")
    public void deleteteUser(@Path("id") int id , Callback<Object> callback);
}

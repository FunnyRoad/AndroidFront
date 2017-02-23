package com.project.application.funnyroad.roadtrip.view.service;

import com.project.application.funnyroad.roadtrip.view.model.RoadTrip;

import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by sameur on 23/02/2017.
 */

public interface RoadTripService {

    //ATTRIBUTES

    public static final String ENDPOINT = "vps376653.ovh.net";

    //METHODS

    @GET("{api-base-path}/roadtrip")
    List<RoadTrip> getAllRoadTrips(Callback<List<RoadTrip>> callback);

    @GET("{api_base-path}/roadtrip/{roadtrip_id}")
    RoadTrip getRoadTrip(@Path("rid") Long rid, Callback<RoadTrip> callback);

    @POST("{api-base-path}/roadtrip")
    RoadTrip createRoadTrip(@Body RoadTrip roadTrip, Callback<List<RoadTrip>> callback);

    @PUT("{api-base-path}/roadtrip")
    RoadTrip updateRoadTrip(@Body RoadTrip roadTrip, Callback<RoadTrip> callback);

    @DELETE("{api-base-path}/roadtrip/{roadtrip_id}")
    void deleteRoadTrip(@Path("rid") Long roadTripId, Callback<Response> callback);

    @DELETE("{api-base-path}/roadtrip/{roadtrip_id}/place/{place_id}")
    void deletePlaceFromRoadTrip(@Path("rid") Long roadTripId, @Path("pid") Long placeId, Callback<Response> callback);
}

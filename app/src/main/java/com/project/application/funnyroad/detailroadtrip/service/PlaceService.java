package com.project.application.funnyroad.detailroadtrip.service;

import android.telecom.Call;

import com.project.application.funnyroad.detailroadtrip.modele.Place;

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
 * Created by sameur on 22/02/2017.
 */

public interface PlaceService {

    //ATTRIBUTES

    public static final String ENDPOINT = "vps376653.ovh.net";

    //METHODS

    @GET("{api-base-path}/place")
    List<Place> getAllPlaces(Callback<List<Place>> callback);

    @GET("{api_base-path}/place/{place_id}")
    Place getPlace(@Path("pid") Long placeId, Callback<Place> callback);

    @POST("{api-base-path}/place")
    Place createPlace(@Body Place place, Callback<Place> callback);

    @PUT("{api-base-path}/place")
    Place updatePlace(@Body Place place, Callback<Place> callback);

    @DELETE("{api-base-path}/place/{place_id}")
    void deletePlace(@Path("pid") Long placeId, Callback<Response> callback);
}

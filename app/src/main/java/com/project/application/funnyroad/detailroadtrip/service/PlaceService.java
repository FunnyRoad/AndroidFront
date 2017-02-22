package com.project.application.funnyroad.detailroadtrip.service;

import com.project.application.funnyroad.detailroadtrip.modele.Place;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by sameur on 22/02/2017.
 */

public interface PlaceService {

    public static final String ENDPOINT = "vps376653.ovh.net";

    @GET("{api-base-path}/place")
    List<Place> listPlaces(Callback<List<Place>> callback);

    @GET("{api_base-path}/place/{place_id}")
    Place getPlace(@Path("place") Long id);

    @POST("{api-base-path}/place")
    Place createPlace(@Field("q") String query);
}

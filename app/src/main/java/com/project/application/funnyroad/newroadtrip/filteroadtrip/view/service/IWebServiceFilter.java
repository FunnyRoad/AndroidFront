package com.project.application.funnyroad.newroadtrip.filteroadtrip.view.service;

import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by you on 25/02/2017.
 */

public interface IWebServiceFilter {

    @FormUrlEncoded
    @POST("")
    public void getPlacesByType(@Field("listType")ArrayList<String> listType , Callback<ArrayList<Place>> callback);

}

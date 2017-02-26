package com.project.application.funnyroad.detailroadtripnew.presenter;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.detailroadtripnew.service.IWebServiceDetailRoadTrip;
import com.project.application.funnyroad.detailroadtripnew.view.IServiceDetailRoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 26/02/2017.
 */

public class PresenterDetailRoadTrip {

    IWebServiceDetailRoadTrip iWebServiceDetailRoadTrip = ConnexionWebService.restAdapter
            .create(IWebServiceDetailRoadTrip.class);

    IServiceDetailRoadTrip mIServiceDetailRoadTrip;

    public PresenterDetailRoadTrip(IServiceDetailRoadTrip iServiceDetailRoadTrip){
        mIServiceDetailRoadTrip = iServiceDetailRoadTrip;
    }

    public void getAllPlacesByRoadId(int roadTripId){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.getPlacesByRoadTripId(roadTripId, new Callback<ArrayList<Place>>() {
            @Override
            public void success(ArrayList<Place> places, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}

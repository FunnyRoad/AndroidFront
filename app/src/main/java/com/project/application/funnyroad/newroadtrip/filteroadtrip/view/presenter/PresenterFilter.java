package com.project.application.funnyroad.newroadtrip.filteroadtrip.view.presenter;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.newroadtrip.filteroadtrip.view.service.IWebServiceFilter;
import com.project.application.funnyroad.newroadtrip.filteroadtrip.view.view.IServiceFiltreRoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 25/02/2017.
 */

public class PresenterFilter {

    IWebServiceFilter iWebServiceFilter = ConnexionWebService.restAdapter
            .create(IWebServiceFilter.class);

    IServiceFiltreRoadTrip mIServiceFiltreRoadTrip;

    public PresenterFilter(IServiceFiltreRoadTrip iServiceFiltreRoadTrip){
        mIServiceFiltreRoadTrip = iServiceFiltreRoadTrip;
    }

    public void getPlacesByFilters(ArrayList<String> listType){
        mIServiceFiltreRoadTrip.showLoading(true);
        iWebServiceFilter.getPlacesByType(listType, new Callback<ArrayList<Place>>() {
            @Override
            public void success(ArrayList<Place> places, Response response) {
                mIServiceFiltreRoadTrip.gotToListRoadTripWithNewListPlaces(places);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceFiltreRoadTrip.errorSend(error.getMessage());
            }
        });
    }
}

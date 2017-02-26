package com.project.application.funnyroad.home.roadtripsuggested.presenter;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.roadtripsuggested.service.IWebServiceRoadTripSuggested;
import com.project.application.funnyroad.home.roadtripsuggested.view.IServiceRoadTripSuggested;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 25/02/2017.
 */

public class PresenterRoadTripSuggested{

    IWebServiceRoadTripSuggested iWebServiceRoadTripSuggested = ConnexionWebService.restAdapter
                                                                    .create(IWebServiceRoadTripSuggested.class);

    IServiceRoadTripSuggested mIServiceRoadTripSuggested;
    public PresenterRoadTripSuggested( IServiceRoadTripSuggested iServiceRoadTripSuggested){
        mIServiceRoadTripSuggested = iServiceRoadTripSuggested;
    }

    public void getAllRoadsTripByCity(){
        mIServiceRoadTripSuggested.showLoading(true);
        iWebServiceRoadTripSuggested.allRoadTripSuggested("", new Callback<ArrayList<RoadTrip>>() {
            @Override
            public void success(ArrayList<RoadTrip> roadTrips, Response response) {
                mIServiceRoadTripSuggested.showLoading(false);
                mIServiceRoadTripSuggested.getAllRoadTripSuggested(roadTrips);
                if(roadTrips.size() == 0){
                    mIServiceRoadTripSuggested.listRoadTripEmpty();
                }
            }
            @Override
            public void failure(RetrofitError error) {
                mIServiceRoadTripSuggested.loadingListError(error.getMessage());
            }
        });

    }
}

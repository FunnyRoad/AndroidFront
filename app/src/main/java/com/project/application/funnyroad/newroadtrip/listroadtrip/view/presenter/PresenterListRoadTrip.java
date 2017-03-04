package com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.service.IWebServiceListRoadTrip;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.view.IServiceListRoad;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 25/02/2017.
 */

public class PresenterListRoadTrip {

    IWebServiceListRoadTrip iWebServiceListRoadTrip = ConnexionWebService.restAdapter.create(IWebServiceListRoadTrip.class);

    IServiceListRoad mIServiceListRoad;

    public PresenterListRoadTrip(IServiceListRoad iServiceListRoad){
        mIServiceListRoad = iServiceListRoad;
    }

    /**
     * la liste des endroit entre departure et arrival
     * @param departure
     * @param arrival
     */
    public void getListPlaces(String departure , String arrival){

        mIServiceListRoad.showLoading(true);
        iWebServiceListRoadTrip.listPlacesBetweenDepArr(departure, arrival, new Callback<ArrayList<Place>>() {
            @Override
            public void success(ArrayList<Place> places, Response response) {
                mIServiceListRoad.showLoading(false);
                mIServiceListRoad.loadingList(places);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceListRoad.showLoading(false);
                mIServiceListRoad.errorLoading(error.getMessage());
            }
        });
    }

    public void createRoadTrip(RoadTrip roadTrip)
    {
        mIServiceListRoad.showLoading(true);
        iWebServiceListRoadTrip.createRoadTrip(roadTrip, new Callback<RoadTrip>() {
            @Override
            public void success(RoadTrip roadTrip, Response response) {
                mIServiceListRoad.showLoading(false);
                mIServiceListRoad.gotToVisual();

            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceListRoad.showLoading(false);
                mIServiceListRoad.errorLoading(error.getMessage());
            }
        });

    }
}

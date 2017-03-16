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

    public void createRoadTrip(RoadTrip roadTrip)
    {
        mIServiceListRoad.showLoading(true);
        iWebServiceListRoadTrip.createRoadTrip(roadTrip, new Callback<RoadTrip>() {
            @Override
            public void success(RoadTrip roadTrip, Response response) {
                mIServiceListRoad.showLoading(false);
                mIServiceListRoad.createRoadTrip(roadTrip);

            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceListRoad.showLoading(false);
                mIServiceListRoad.errorLoading(error.getMessage());
            }
        });

    }
}

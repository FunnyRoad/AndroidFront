package com.project.application.funnyroad.home.allroadtrip.view;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

/**
 * Created by you on 24/02/2017.
 */

public interface IServiceAllRoadTrip {

    public void showLoading(boolean bool);
    public void getAllRoadTrip(ArrayList<RoadTrip> listRoadTrip);
    public void listRoadTripEmpty();
    public void loadingListError(String msg);
}
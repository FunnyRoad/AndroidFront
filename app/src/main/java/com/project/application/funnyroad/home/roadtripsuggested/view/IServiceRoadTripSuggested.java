package com.project.application.funnyroad.home.roadtripsuggested.view;

import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

/**
 * Created by you on 24/02/2017.
 */

public interface IServiceRoadTripSuggested {

    public void showLoading(boolean bool);
    public void getAllRoadTripSuggested(ArrayList<RoadTrip> listRoadTrip);
    public void listRoadTripEmpty();
    public void loadingListError(String msg);
}

package com.project.application.funnyroad.newroadtrip.listroadtrip.view.view;

import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

/**
 * Created by you on 24/02/2017.
 */

public interface IServiceListRoad {

    public void showLoading(boolean bool);
    //public void loadingList(ArrayList<Place> listPlaces);
    public void createRoadTrip(RoadTrip roadTrip);
    public void errorLoading(String msg);
}

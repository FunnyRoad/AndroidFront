package com.project.application.funnyroad.newroadtrip.filteroadtrip.view.view;

import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

/**
 * Created by you on 24/02/2017.
 */

public interface IServiceFiltreRoadTrip {

    public void showLoading(boolean bool);
    public void gotToListRoadTripWithNewListPlaces(ArrayList<Place> places);
    public void errorSend(String msg);
}

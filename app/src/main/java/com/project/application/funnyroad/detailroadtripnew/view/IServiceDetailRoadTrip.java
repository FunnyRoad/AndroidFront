package com.project.application.funnyroad.detailroadtripnew.view;

import android.graphics.Bitmap;

import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

/**
 * Created by you on 23/02/2017.
 */

public interface IServiceDetailRoadTrip {

    public void showLoading(boolean bool);
    public void getListPlacesSuccess(ArrayList<Place> places);
    //public void getListPhotosSuccess(ArrayList<Bitmap> photos);
    public void getListFailed(String msg);
    //public void listPhotosEmpty();
    public void listPlacesEmpty();
    public void goToListRoadTrip();
    public void deleteRoadTripFailed(String msg);
    public void getInformationRoadTrip(RoadTrip roadTrip);
}

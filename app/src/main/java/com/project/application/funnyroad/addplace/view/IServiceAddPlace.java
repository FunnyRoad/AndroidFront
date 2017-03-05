package com.project.application.funnyroad.addplace.view;

import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

/**
 * Created by you on 23/02/2017.
 */

public interface IServiceAddPlace {

    public void showLoading(boolean bool);
    public void addPlaceSuccess(Place place);
    public void addPlaceFailed(String msg);
    public void addPlaceToRoadTripSuccess();
    public void addImageToPlaceSuccess();

}

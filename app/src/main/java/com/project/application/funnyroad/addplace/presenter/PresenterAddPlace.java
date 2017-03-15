package com.project.application.funnyroad.addplace.presenter;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.addplace.service.IWebServiceAddPlace;
import com.project.application.funnyroad.addplace.view.IServiceAddPlace;
import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by oa on 28/02/2017.
 */

public class PresenterAddPlace {

    IWebServiceAddPlace iWebServiceListRoadTrip = ConnexionWebService.restAdapter.create(IWebServiceAddPlace.class);

    IServiceAddPlace mIServiceAddPlace;

    public PresenterAddPlace(IServiceAddPlace iServiceAddPlace){
        mIServiceAddPlace = iServiceAddPlace;
    }

    public void addPlace(Place place){
        mIServiceAddPlace.showLoading(true);
        iWebServiceListRoadTrip.addPlace(place, new Callback<Place>() {
            @Override
            public void success(Place place, Response response) {
                mIServiceAddPlace.showLoading(false);
                mIServiceAddPlace.addPlaceSuccess(place);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceAddPlace.showLoading(false);
                mIServiceAddPlace.addPlaceFailed(error.getMessage());
            }
        });
    }

    public void addPlaceToRoadTrip(int roadTripId , int placeId){
        mIServiceAddPlace.showLoading(true);
        iWebServiceListRoadTrip.addPlaceToRoadtrip(roadTripId, placeId, new Callback<Object>() {
            @Override
            public void success(Object msgResponse, Response response) {
                mIServiceAddPlace.showLoading(false);
                mIServiceAddPlace.addPlaceToRoadTripSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceAddPlace.showLoading(false);
                mIServiceAddPlace.addPlaceFailed(error.getMessage());
            }
        });
    }

    public void addImageToPlace(int idPlace, TypedFile typedFile){
        mIServiceAddPlace.showLoading(true);
        iWebServiceListRoadTrip.addImageToPlace(idPlace,typedFile, new Callback<Picture>() {
            @Override
            public void success(Picture picture, Response response) {
                mIServiceAddPlace.showLoading(false);
                mIServiceAddPlace.addImageToPlaceSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceAddPlace.showLoading(false);
                mIServiceAddPlace.addPlaceFailed(error.getMessage());
            }
        });
    }
}

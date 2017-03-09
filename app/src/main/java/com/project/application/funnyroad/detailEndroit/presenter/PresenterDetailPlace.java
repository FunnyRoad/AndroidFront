package com.project.application.funnyroad.detailEndroit.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.detailEndroit.service.IWebServiceDetailPlace;
import com.project.application.funnyroad.detailEndroit.view.IServiceDetailsPlace;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 04/03/2017.
 */

public class PresenterDetailPlace {

    IWebServiceDetailPlace iWebServiceListRoadTrip = ConnexionWebService.restAdapter.create(IWebServiceDetailPlace.class);

    IServiceDetailsPlace mIServiceDetailsPlace;

    public PresenterDetailPlace(IServiceDetailsPlace iServiceDetailsPlace){
        mIServiceDetailsPlace = iServiceDetailsPlace;
    }


    public void uploadPlace(Place place){
        mIServiceDetailsPlace.showLoading(true);
        iWebServiceListRoadTrip.uploadPlace(place, new Callback<Place>() {
            @Override
            public void success(Place place, Response response) {
                mIServiceDetailsPlace.showLoading(false);
                mIServiceDetailsPlace.uploadPlaceSuccess(place);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailsPlace.showLoading(false);
                mIServiceDetailsPlace.getInformationFailed(error.getMessage());
            }
        });
    }

    public void deletePlace(int roadtripId , int placeId){
        mIServiceDetailsPlace.showLoading(true);
        iWebServiceListRoadTrip.deletePlace(roadtripId, placeId, new Callback<Void>() {
            @Override
            public void success(Void s, Response response) {
                mIServiceDetailsPlace.showLoading(false);
                mIServiceDetailsPlace.deletePlaceSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailsPlace.showLoading(false);
                mIServiceDetailsPlace.getInformationFailed(error.getMessage());
            }
        });
    }

    public void getPictures(int idPlace){
        mIServiceDetailsPlace.showLoading(true);
        iWebServiceListRoadTrip.getPictures(idPlace, new Callback<ArrayList<Picture>>() {
            @Override
            public void success(ArrayList<Picture> pictures, Response response) {
                mIServiceDetailsPlace.showLoading(false);
                if(pictures.size() == 0){
                    mIServiceDetailsPlace.listPhotosEmpty();
                }
                mIServiceDetailsPlace.getListPictureModel(pictures);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailsPlace.showLoading(false);
                mIServiceDetailsPlace.getInformationFailed(error.getMessage());
            }
        });
    }
}

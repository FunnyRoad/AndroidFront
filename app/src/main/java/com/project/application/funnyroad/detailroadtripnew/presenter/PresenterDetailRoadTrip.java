package com.project.application.funnyroad.detailroadtripnew.presenter;

import android.graphics.Bitmap;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.detailroadtripnew.service.IWebServiceDetailRoadTrip;
import com.project.application.funnyroad.detailroadtripnew.view.IServiceDetailRoadTrip;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 26/02/2017.
 */

public class PresenterDetailRoadTrip {

    IWebServiceDetailRoadTrip iWebServiceDetailRoadTrip = ConnexionWebService.restAdapter
            .create(IWebServiceDetailRoadTrip.class);

    IServiceDetailRoadTrip mIServiceDetailRoadTrip;

    public PresenterDetailRoadTrip(IServiceDetailRoadTrip iServiceDetailRoadTrip){
        mIServiceDetailRoadTrip = iServiceDetailRoadTrip;
    }

    public void getAllPlacesByRoadId(int roadTripId){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.getPlacesByRoadTripId(roadTripId, new Callback<ArrayList<Place>>() {
            @Override
            public void success(ArrayList<Place> places, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                if(places.size() == 0 ){
                    mIServiceDetailRoadTrip.listPlacesEmpty();
                }
                mIServiceDetailRoadTrip.getListPlacesSuccess(places);

            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });
    }

    public void updateRoadTrip(RoadTrip roadTrip){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.updateRoadTrip(roadTrip, new Callback<RoadTrip>() {
            @Override
            public void success(RoadTrip roadTrip, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getInformationRoadTrip(roadTrip);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
            }
        });
    }

    public void deleteRoadTrip(int id){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.deleteRoadTrip(id, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                if (s.equals("heve been removed")){
                    mIServiceDetailRoadTrip.goToListRoadTrip();
                }
                else{
                    mIServiceDetailRoadTrip.deleteRoadTripFailed("le road trip n'a pas été supprimé");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });
    }

    /*public void getPhotosRoadTrip(int roadTripId){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.getPhotosByRoadTripId(roadTripId, new Callback<ArrayList<Bitmap>>() {
            @Override
            public void success(ArrayList<Bitmap> photos, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListPhotosSuccess(photos);
                if(photos.size() == 0){
                    mIServiceDetailRoadTrip.listPhotosEmpty();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });

    } */
}

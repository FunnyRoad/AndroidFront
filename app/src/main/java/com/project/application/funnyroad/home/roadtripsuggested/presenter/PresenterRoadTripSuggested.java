package com.project.application.funnyroad.home.roadtripsuggested.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.roadtripsuggested.service.IWebServiceRoadTripSuggested;
import com.project.application.funnyroad.home.roadtripsuggested.view.IServiceRoadTripSuggested;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 25/02/2017.
 */

public class PresenterRoadTripSuggested{

    IWebServiceRoadTripSuggested iWebServiceRoadTripSuggested = ConnexionWebService.restAdapter
                                                                    .create(IWebServiceRoadTripSuggested.class);

    IServiceRoadTripSuggested mIServiceRoadTripSuggested;
    Activity activity;

    public PresenterRoadTripSuggested( IServiceRoadTripSuggested iServiceRoadTripSuggested){
        mIServiceRoadTripSuggested = iServiceRoadTripSuggested;
    }

    public PresenterRoadTripSuggested(Activity activity){
        this.activity = activity;
    }

    public void getAllRoadsTripByCity(double latitude , double longitude, double distance){
        mIServiceRoadTripSuggested.showLoading(true);
        iWebServiceRoadTripSuggested.allRoadTripSuggested(latitude,longitude, distance,new Callback<ArrayList<RoadTrip>>() {
            @Override
            public void success(ArrayList<RoadTrip> roadTrips, Response response) {
                mIServiceRoadTripSuggested.showLoading(false);
                if(roadTrips.size() == 0){
                    mIServiceRoadTripSuggested.listRoadTripEmpty();
                }
                else{
                    mIServiceRoadTripSuggested.getAllRoadTripSuggested(roadTrips);
                }

            }
            @Override
            public void failure(RetrofitError error) {
                mIServiceRoadTripSuggested.loadingListError(error.getMessage());
            }
        });
    }

    public void addUserToGuestList(int idUser , int idRoadTrip){
        iWebServiceRoadTripSuggested.addGuestToRoad(idUser, idRoadTrip, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                Toast.makeText( activity, " Vous êtes bien ajouté aux followers du road trip !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText( activity, " Vous suivez déjà ce road trip !", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

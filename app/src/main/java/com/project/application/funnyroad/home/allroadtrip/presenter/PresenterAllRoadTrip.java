package com.project.application.funnyroad.home.allroadtrip.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.home.allroadtrip.service.IWebServiceAllRoadTrip;
import com.project.application.funnyroad.home.allroadtrip.view.IServiceAllRoadTrip;
import com.project.application.funnyroad.home.model.RoadTrip;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 25/02/2017.
 */

public class PresenterAllRoadTrip {

    IWebServiceAllRoadTrip iWebServiceAllRoadTrip = ConnexionWebService.restAdapter
            .create(IWebServiceAllRoadTrip.class);

    IServiceAllRoadTrip mIServiceAllRoadTrip;
    Activity activity;
    public PresenterAllRoadTrip(IServiceAllRoadTrip iServiceAllRoadTrip){
        mIServiceAllRoadTrip = iServiceAllRoadTrip;
    }

    public PresenterAllRoadTrip(Activity activity){
        this.activity = activity;
    }

    public void getAllRoadTrip(){
        mIServiceAllRoadTrip.showLoading(true);
        iWebServiceAllRoadTrip.allRoadTrip(new Callback<ArrayList<RoadTrip>>() {
            @Override
            public void success(ArrayList<RoadTrip> roadTrips, Response response) {
                mIServiceAllRoadTrip.showLoading(false);
                if(roadTrips.size() == 0){
                    mIServiceAllRoadTrip.listRoadTripEmpty();
                }
                mIServiceAllRoadTrip.getAllRoadTrip(roadTrips);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceAllRoadTrip.showLoading(false);
                mIServiceAllRoadTrip.loadingListError(error.getMessage());
            }
        });
    }

    public void addUserToGuestList(int idUser , int idRoadTrip){
        iWebServiceAllRoadTrip.addGuestToRoad(idUser, idRoadTrip, new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                Toast.makeText( activity, " Vous êtes bien ajouté aux followers du road trip", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText( activity, " Vous n'êtes pas ajouté aux followers du road trip", Toast.LENGTH_SHORT).show();

            }
        });
    }
}

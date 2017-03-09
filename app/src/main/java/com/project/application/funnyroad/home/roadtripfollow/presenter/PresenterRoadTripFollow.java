package com.project.application.funnyroad.home.roadtripfollow.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.roadtripfollow.service.IWebServiceRoadTripFollow;
import com.project.application.funnyroad.home.roadtripfollow.view.IServiceFollowRoadTrip;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 06/03/2017.
 */

public class PresenterRoadTripFollow {

    IWebServiceRoadTripFollow iWebServiceAllRoadTrip = ConnexionWebService.restAdapter
            .create(IWebServiceRoadTripFollow.class);

    IServiceFollowRoadTrip mIServiceFollowRoadTrip;
    Activity activity;

    public PresenterRoadTripFollow(IServiceFollowRoadTrip iServiceFollowRoadTrip){
        mIServiceFollowRoadTrip = iServiceFollowRoadTrip;
    }

    public PresenterRoadTripFollow(Activity activity , IServiceFollowRoadTrip iServiceFollowRoadTrip){
        this.activity = activity;
        mIServiceFollowRoadTrip = iServiceFollowRoadTrip;
    }

    public void getFollowedRoadTrip( int userId){
        mIServiceFollowRoadTrip.showLoading(true);
        iWebServiceAllRoadTrip.getRoadTripFollowed(userId, new Callback<ArrayList<RoadTrip>>() {
            @Override
            public void success(ArrayList<RoadTrip> roadTrips, Response response) {
                mIServiceFollowRoadTrip.showLoading(false);
                if(roadTrips.size() == 0){
                    mIServiceFollowRoadTrip.listUsersEmpty();
                }
                else{
                    mIServiceFollowRoadTrip.showAllRoadTripFollow(roadTrips);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceFollowRoadTrip.showLoading(false);
                mIServiceFollowRoadTrip.loadingListError(error.getMessage());

            }
        });
    }

    public void deleteFollowRoadTrip( final int userId , final int roadTripId ){
        mIServiceFollowRoadTrip.showLoading(true);
        iWebServiceAllRoadTrip.deleteRoadTripFollowed(userId, roadTripId, new Callback<Object>(){
            @Override
            public void success(Object o, Response response) {
                mIServiceFollowRoadTrip.showLoading(false);
                getFollowedRoadTrip(userId);
                //Toast.makeText(activity, " le roadtrip numéro: "+ roadTripId+" a bien été supprimé", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceFollowRoadTrip.showLoading(false);
                Toast.makeText(activity, " erreur lors de la suppression de roadtrip numéro: "+ roadTripId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

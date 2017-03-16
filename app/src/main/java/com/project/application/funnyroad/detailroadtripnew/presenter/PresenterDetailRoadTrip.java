package com.project.application.funnyroad.detailroadtripnew.presenter;

import android.graphics.Bitmap;

import com.project.application.funnyroad.addplace.model.Picture;
import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.detailroadtripnew.model.Post;
import com.project.application.funnyroad.detailroadtripnew.service.IWebServiceDetailRoadTrip;
import com.project.application.funnyroad.detailroadtripnew.view.IServiceDetailRoadTrip;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

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
        iWebServiceDetailRoadTrip.deleteRoadTrip(id, new Callback<Object>() {
            @Override
            public void success(Object s, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.goToListRoadTrip();

            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });
    }

    public void getRoadTripById(int id){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.getRoadTripById(id, new Callback<RoadTrip>() {
            @Override
            public void success(RoadTrip roadTrip, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.fillFragment(roadTrip);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });
    }

    public void sendPost(Post post){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.sendPost(post, new Callback<Post>() {
            @Override
            public void success(Post post, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.postAddSuccess(post);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });
    }

    public void addPictureToRoad(int postId , TypedFile image){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.addPictureToPost(postId, image, new Callback<Picture>() {
            @Override
            public void success(Picture picture, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.pictureAddedSuccess();
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });
    }

    public void getAllPosts(){
        mIServiceDetailRoadTrip.showLoading(true);
        iWebServiceDetailRoadTrip.getAllPosts(new Callback<ArrayList<Post>>() {
            @Override
            public void success(ArrayList<Post> posts, Response response) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.allPostSuccess(posts);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceDetailRoadTrip.showLoading(false);
                mIServiceDetailRoadTrip.getListFailed(error.getMessage());
            }
        });
    }
}

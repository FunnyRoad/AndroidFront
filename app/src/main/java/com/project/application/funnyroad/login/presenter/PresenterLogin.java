package com.project.application.funnyroad.login.presenter;

import android.app.Activity;
import android.widget.Toast;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.login.model.User;
import com.project.application.funnyroad.login.service.IWebServiceLogin;
import com.project.application.funnyroad.login.view.IServiceLogin;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by oraberkane on 02/02/2017.
 */



public class PresenterLogin {

    IWebServiceLogin iWebServiceLogin = ConnexionWebService.restAdapter
            .create(IWebServiceLogin.class);

    private IServiceLogin mIServiceLogin;


    public PresenterLogin(IServiceLogin iLoginService) {
        // mContext = context;
        mIServiceLogin = iLoginService;
    }



    public void connect(User user) {
        iWebServiceLogin.userLogin(user, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                mIServiceLogin.isLoginSuccess(user.getId());
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceLogin.isLoginFailed(error.getMessage());
            }
        });
    }


    public void createRoadTrip(RoadTrip roadTrip)
    {
        iWebServiceLogin.createRoadTrip(roadTrip, new Callback<RoadTrip>() {
            @Override
            public void success(RoadTrip roadTrip, Response response) {
                mIServiceLogin.createRoadTrip();
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceLogin.isLoginFailed(error.getMessage());
            }
        });
    }

    public void deleteRoad(int roadTrip)
    {
        iWebServiceLogin.delete(roadTrip, new Callback<String>() {
            @Override
            public void success(String msg, Response response) {
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceLogin.isLoginFailed(error.getMessage());
            }
        });
    }


}

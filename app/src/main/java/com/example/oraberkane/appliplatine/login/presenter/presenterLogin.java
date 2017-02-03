package com.example.oraberkane.appliplatine.login.presenter;

import android.content.Context;

import com.example.oraberkane.appliplatine.login.service.IWebServiceLogin;
import com.example.oraberkane.appliplatine.login.view.IServiceLogin;

import retrofit.RestAdapter;

/**
 * Created by oraberkane on 02/02/2017.
 */

public class presenterLogin {

    /**
     * Initialisation de l'appel web service
     */
    IWebServiceLogin iWebServiceLogin = new RestAdapter.Builder()
            .setEndpoint("lien du webService")
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build()
            .create(IWebServiceLogin.class);





    // l'interface représentant la vue.
    private IServiceLogin mIServiceLogin;

    // constructeur
    public presenterLogin(IServiceLogin iLoginService, Context context) {
       // mContext = context;
        mIServiceLogin = iLoginService;
    }

}

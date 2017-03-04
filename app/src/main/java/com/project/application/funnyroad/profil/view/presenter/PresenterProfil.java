package com.project.application.funnyroad.profil.view.presenter;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.login.model.User;
import com.project.application.funnyroad.profil.view.service.IWebServiceProfil;
import com.project.application.funnyroad.profil.view.view.IServiceProfil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by you on 28/02/2017.
 */

public class PresenterProfil {

    IWebServiceProfil iWebServiceProfil = ConnexionWebService.restAdapter
            .create(IWebServiceProfil.class);

    IServiceProfil mIServiceProfil;

    public PresenterProfil(IServiceProfil iServiceProfil){
        mIServiceProfil = iServiceProfil;
    }

    public void getInformationUser(int id){
        mIServiceProfil.showLoading(true);
        iWebServiceProfil.getUser(id, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                mIServiceProfil.showLoading(false);
                mIServiceProfil.showInformationsUser(user);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceProfil.showLoading(false);
                mIServiceProfil.errorGetInformations(error.getMessage());
            }
        });

    }

    public void updateInformationsUser(User user){
        mIServiceProfil.showLoading(true);
        iWebServiceProfil.updateUser(user, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                mIServiceProfil.showLoading(false);
                mIServiceProfil.showInformationsUser(user);
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceProfil.showLoading(false);
                mIServiceProfil.errorGetInformations(error.getMessage());
            }
        });

    }

    public void deleteUser(int userId)
    {
        iWebServiceProfil.deleteUser(userId, new Callback<String>() {
            @Override
            public void success(String msg, Response response) {
            }

            @Override
            public void failure(RetrofitError error) {
                mIServiceProfil.errorGetInformations(error.getMessage());
            }
        });
    }
}

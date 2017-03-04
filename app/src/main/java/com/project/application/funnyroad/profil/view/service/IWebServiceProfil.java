package com.project.application.funnyroad.profil.view.service;

import com.project.application.funnyroad.login.model.User;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by you on 28/02/2017.
 */

public interface IWebServiceProfil {

    @PUT("/user")
    public void updateUser(@Body User user, Callback<User> callback);

    @GET("/user/{id}")
    public void getUser(@Path("id") int idUser, Callback<User> callback);

    @DELETE("/user/{id}")
    public void deleteUser(@Path("id") int id , Callback<String> callback);
}

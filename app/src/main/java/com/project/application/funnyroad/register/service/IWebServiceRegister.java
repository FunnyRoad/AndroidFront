package com.project.application.funnyroad.register.service;

import com.project.application.funnyroad.login.model.User;

import java.util.Date;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by aberkane on 03/02/17.
 */

public interface IWebServiceRegister {

    @FormUrlEncoded
    @POST("/user")
    public void createUser(@Field("mail") String email , @Field("firebaseId") String firebaseId,
                           @Field("firstName") String firstName, @Field("lastName") String lastName,
                           @Field("username") String userName, @Field("birthDate") Date birthDate, Callback<User> callback);
}

package com.project.application.funnyroad.register.service;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by aberkane on 03/02/17.
 */

public interface IWebServiceRegister {

    @FormUrlEncoded
    @POST("/api/signup")
    public void createUser(@Field("email") String email , @Field("password") String password,
                           @Field("name") String name, @Field("city") String city,
                           @Field("sex") String sex,
                           @Field("birthday") String birthday, Callback<String> callback);
}

package com.project.application.funnyroad.user.service;

import android.telecom.Call;

import com.project.application.funnyroad.user.model.User;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by sameur on 23/02/2017.
 */

public interface UserService {

    //ATTRIBUTES

    public static final String ENDPOINT = "vps376653.ovh.net";

    //METHODS

    @GET("{api-base-path}/user/{user_id}")
    User getUser(@Path("uid") Long uid, Callback<User> callback);

    @POST("{api-base-path}/user")
    User createUser(@Body User user, Callback<User> callback);

    @PUT("{api-base-path}/user")
    User updateUser(@Body User user, Callback<User> callback);

    @DELETE("{api-base-path}/user/{user_id}")
    void deleteUser(@Path("uid") Long uid, Callback<Response> callback);
}

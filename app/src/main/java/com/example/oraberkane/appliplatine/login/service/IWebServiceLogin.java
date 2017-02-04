package com.example.oraberkane.appliplatine.login.service;

import java.util.UUID;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by aberkane on 03/02/17.
 */

public interface IWebServiceLogin {
    // appel asynchrone
    @GET("/api/login/{email}/{password}")
    public void userLogin(@Path("email") String email , @Path("password") String password , Callback<UUID> callback);

}

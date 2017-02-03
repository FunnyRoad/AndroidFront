package com.example.oraberkane.appliplatine.login.service;

import java.util.UUID;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by oraberkane on 02/02/2017.
 */

public interface IWebServiceLogin {

    //c'est un exemple
    @GET("chemin pour faire appel au web service/{la variable login}/{variable password}")
    public void userLogin(@Path("email") String email , @Path("password") String password , Callback<UUID> callback);


}

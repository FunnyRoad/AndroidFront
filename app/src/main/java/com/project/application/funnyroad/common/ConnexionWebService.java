package com.project.application.funnyroad.common;

import retrofit.RestAdapter;

/**
 * Created by you on 22/02/2017.
 */

public class ConnexionWebService {

    public static RestAdapter restAdapter = new RestAdapter.Builder()
                                        .setEndpoint("vps376653.ovh.net:8080")
                                        .setLogLevel(RestAdapter.LogLevel.FULL)
                                        .build();
}

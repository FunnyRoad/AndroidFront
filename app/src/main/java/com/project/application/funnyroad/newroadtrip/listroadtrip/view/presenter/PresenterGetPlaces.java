package com.project.application.funnyroad.newroadtrip.listroadtrip.view.presenter;

import com.project.application.funnyroad.common.ConnexionWebService;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.service.IWebServiceListRoadTrip;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.view.IServiceListRoad;

/**
 * Created by sameur on 10/03/2017.
 */

public class PresenterGetPlaces {

    IWebServiceListRoadTrip iWebServiceListRoadTrip = ConnexionWebService.restAdapter.create(IWebServiceListRoadTrip.class);

    IServiceListRoad mIServiceListRoad;
}

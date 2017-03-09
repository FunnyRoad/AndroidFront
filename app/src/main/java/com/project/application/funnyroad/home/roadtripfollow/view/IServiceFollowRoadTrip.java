package com.project.application.funnyroad.home.roadtripfollow.view;

import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.login.model.User;

import java.util.ArrayList;

/**
 * Created by you on 24/02/2017.
 */

public interface IServiceFollowRoadTrip {

    public void showLoading(boolean bool);
    public void showAllRoadTripFollow(ArrayList<RoadTrip> listRoadTrips);
    public void listUsersEmpty();
    public void loadingListError(String msg);
}

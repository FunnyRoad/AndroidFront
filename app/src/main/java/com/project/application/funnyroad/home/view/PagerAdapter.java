package com.project.application.funnyroad.home.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.project.application.funnyroad.home.allroadtrip.view.AllRoadTripFragment;
import com.project.application.funnyroad.home.friends.view.FriendsFragment;
import com.project.application.funnyroad.home.roadtripsuggested.view.RoadTripSuggestedFragment;


/**
 * Created by oraberkane on 03/02/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AllRoadTripFragment fragmentAllRoad = new AllRoadTripFragment();
                return fragmentAllRoad;
            case 1:
                RoadTripSuggestedFragment fragmentRoadTripSuggested = new RoadTripSuggestedFragment();
                return fragmentRoadTripSuggested;
            case 2:
                FriendsFragment fragmentFriends = new FriendsFragment();
                return fragmentFriends;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

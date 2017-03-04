package com.project.application.funnyroad.roadtrip.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Created by sameur on 12/02/2017.
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
                LesRoadTripsFragment fragmentLesRoadTrips = new LesRoadTripsFragment();
                return fragmentLesRoadTrips;
            case 1:
                LesRecommandesFragment fragmentLesRecommandes = new LesRecommandesFragment();
                return fragmentLesRecommandes;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

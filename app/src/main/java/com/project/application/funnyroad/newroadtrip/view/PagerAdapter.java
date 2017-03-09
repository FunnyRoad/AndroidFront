package com.project.application.funnyroad.newroadtrip.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.project.application.funnyroad.newroadtrip.filteroadtrip.view.FiltrePlacesFragment;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.view.ListPlacesFragment;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.view.VisualRoadTripFragment;

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
                ListPlacesFragment fragmentList = new ListPlacesFragment();
                return fragmentList;
            case 1:
                FiltrePlacesFragment fragmentFiltre = new FiltrePlacesFragment();
                return fragmentFiltre;
            case 2:
                VisualRoadTripFragment fragmentVisual = new VisualRoadTripFragment();
                return fragmentVisual;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

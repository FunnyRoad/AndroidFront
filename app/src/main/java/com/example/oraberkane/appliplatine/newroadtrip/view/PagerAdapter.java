package com.example.oraberkane.appliplatine.newroadtrip.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.oraberkane.appliplatine.newroadtrip.filtreroadtrip.view.FiltreRoadTripFragment;
import com.example.oraberkane.appliplatine.newroadtrip.listroad.view.ListRoadFragment;

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
                ListRoadFragment fragmentList = new ListRoadFragment();
                return fragmentList;
            case 1:
                FiltreRoadTripFragment fragmentFiltre = new FiltreRoadTripFragment();
                return fragmentFiltre;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

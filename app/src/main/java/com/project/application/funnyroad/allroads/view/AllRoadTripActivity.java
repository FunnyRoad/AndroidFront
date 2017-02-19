package com.project.application.funnyroad.allroads.view;

import android.os.Bundle;
import android.view.View;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by you on 18/02/2017.
 */

public class AllRoadTripActivity extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AllRoadTripFragment mAllRoadTripFragment = new AllRoadTripFragment();

        //desactiver la toolbar
        //mToolbar.setVisibility(View.GONE);
        mToolbar.setTitle("Les RoadTrips");
        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mAllRoadTripFragment)
                .commit();
    }
}

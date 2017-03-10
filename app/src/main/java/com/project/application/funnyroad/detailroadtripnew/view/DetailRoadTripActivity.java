package com.project.application.funnyroad.detailroadtripnew.view;

import android.os.Bundle;
import android.view.WindowManager;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by you on 18/02/2017.
 */

public class DetailRoadTripActivity extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailRoadTripFragment mDetailRoadTripFragment = new DetailRoadTripFragment();

        //desactiver la toolbar
        //mToolbar.setVisibility(View.GONE);
        mToolbar.setTitle("Détail du Roadtrip");
        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mDetailRoadTripFragment)
                .commit();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }
}

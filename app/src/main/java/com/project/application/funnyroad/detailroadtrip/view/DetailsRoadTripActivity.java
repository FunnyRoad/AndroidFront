package com.project.application.funnyroad.detailroadtrip.view;

import android.os.Bundle;
import android.view.View;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by oraberkane on 04/02/2017.
 */

public class DetailsRoadTripActivity extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailsRoadTripFragment mDetailsRoadTripFragment = new DetailsRoadTripFragment();

        //desactiver la toolbar
        mToolbar.setVisibility(View.GONE);

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mDetailsRoadTripFragment)
                .commit();
    }
}

package com.project.application.funnyroad.newroadtrip.view;

import android.graphics.Color;
import android.os.Bundle;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by sameur on 08/03/2017.
 */

public class ActivityCreateNewRoadTrip extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentCreateNewRoadTrip fragmentCreateNewRoadTrip = new FragmentCreateNewRoadTrip();

        //desactiver la toolbar
        //mToolbar.setVisibility(View.GONE);
        mToolbar.setTitle("Validation du Road Trip");
        mToolbar.setTitleTextColor(Color.rgb(255,255,255));
        mToolbar.setBackgroundColor(Color.rgb(1,108,80));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, fragmentCreateNewRoadTrip)
                .commit();
    }
}

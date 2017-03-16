package com.project.application.funnyroad.newroadtrip.view;

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

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, fragmentCreateNewRoadTrip)
                .commit();
    }
}

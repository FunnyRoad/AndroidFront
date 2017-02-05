package com.example.oraberkane.appliplatine.detailroadtrip.view;

import android.os.Bundle;
import android.view.View;

import com.example.oraberkane.appliplatine.R;
import com.example.oraberkane.appliplatine.common.LayoutCommonActivity;

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

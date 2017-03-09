package com.project.application.funnyroad.googlemap.view;

import android.os.Bundle;
import android.view.View;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;
import com.project.application.funnyroad.newroadtrip.Variable;


/**
 * Created by oraberkane on 04/02/2017.
 */

public class ActivityNewRoadTripRouteChoice extends LayoutCommonActivity {

        @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Variable lesVariables = ((Variable) getApplicationContext());

            //setContentView(R.layout.fragment_routechoice);
            FragmentRouteChoice mFragmentRouteChoice = new FragmentRouteChoice();


            //desactiver la toolbar
            mToolbar.setVisibility(View.GONE);

            getSupportFragmentManager()//appel fragment manager jva
                    .beginTransaction()
                    .add(R.id.container_fragment, mFragmentRouteChoice)
                    .commit();


        }
}

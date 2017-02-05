package com.example.oraberkane.appliplatine.googlemap.view;

import android.os.Bundle;
import android.view.View;

import com.example.oraberkane.appliplatine.R;
import com.example.oraberkane.appliplatine.common.LayoutCommonActivity;


/**
 * Created by oraberkane on 04/02/2017.
 */

public class ActivityNewRoaTripRouteChoice extends LayoutCommonActivity {

        @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
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

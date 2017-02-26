package com.project.application.funnyroad.addplace.view;

import android.os.Bundle;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by you on 18/02/2017.
 */

public class AddPlaceActivity extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AddPlaceFragment mAddPlaceFragment = new AddPlaceFragment();

        //desactiver la toolbar
        //mToolbar.setVisibility(View.GONE);

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mAddPlaceFragment)
                .commit();
    }
}

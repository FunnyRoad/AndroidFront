package com.project.application.funnyroad.addplace.view;

import android.graphics.Color;
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

        mToolbar.setTitle("Création d'un Lieu");
        mToolbar.setTitleTextColor(Color.rgb(255,255,255));
        mToolbar.setBackgroundColor(Color.rgb(1,108,80));

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mAddPlaceFragment)
                .commit();
    }
}

package com.project.application.funnyroad.login.view;

import android.os.Bundle;
import android.view.View;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by oraberkane on 01/02/2017.
 */

public class ActivityLogin extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentLogin mLoginFragment = new FragmentLogin();

        //desactiver la toolbar
        mToolbar.setVisibility(View.GONE);

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mLoginFragment)
                .commit();
    }





}

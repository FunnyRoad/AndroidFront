package com.example.oraberkane.appliplatine.login.view;

import android.os.Bundle;
import android.view.View;

import com.example.oraberkane.appliplatine.R;
import com.example.oraberkane.appliplatine.common.LayoutCommonActivity;

/**
 * Created by oraberkane on 01/02/2017.
 */

public class LoginActivity extends LayoutCommonActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginFragment mLoginFragment = new LoginFragment();

        //desactiver la toolbar
        mToolbar.setVisibility(View.GONE);

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                //.add(R.id.container_fragment, mLoginFragment)
                .commit();
    }





}

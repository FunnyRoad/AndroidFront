package com.example.oraberkane.appliplatine.register.view;

import android.os.Bundle;
import android.view.View;

import com.example.oraberkane.appliplatine.R;
import com.example.oraberkane.appliplatine.common.LayoutCommonActivity;

/**
 * Created by oraberkane on 02/02/2017.
 */

public class ActivityRegister extends LayoutCommonActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentRegister mFragmentRegister = new FragmentRegister();
        //desactiver la toolbar
        mToolbar.setVisibility(View.GONE);

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, mFragmentRegister)
                .commit();
    }







}
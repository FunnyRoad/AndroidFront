package com.project.application.funnyroad.detailEndroit.view;

import android.os.Bundle;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by you on 20/02/2017.
 */

public class DetailsEndroitActivity extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailEndroitFragment detailEndroitFragment = new DetailEndroitFragment();

        mToolbar.setTitle("Détail place");

        getSupportFragmentManager()//appel fragment manager jva
                .beginTransaction()
                .add(R.id.container_fragment, detailEndroitFragment)
                .commit();

    }
}

package com.project.application.funnyroad.home.view;

import android.os.Bundle;
import android.view.View;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

/**
 * Created by you on 23/02/2017.
 */

public class ActivityHome extends LayoutCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar.setVisibility(View.GONE);

        FragmentHome fragmentHome = new FragmentHome();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, fragmentHome)
                .commit();
    }

}

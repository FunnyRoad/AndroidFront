package com.project.application.funnyroad.newroadtrip.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;

import butterknife.BindView;

/**
 * Created by sameur on 08/03/2017.
 */

public class ActivityCreateNewRoadTrip extends LayoutCommonActivity {

   // @BindView(R.id.textv)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            FragmentCreateNewRoadTrip fragmentCreateNewRoadTrip = new FragmentCreateNewRoadTrip();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            //fragmentTransaction.add(new LinearLayout(getApplicationContext()).getId(),fragmentCreateNewRoadTrip, 1);
            //fragmentTransaction.commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}

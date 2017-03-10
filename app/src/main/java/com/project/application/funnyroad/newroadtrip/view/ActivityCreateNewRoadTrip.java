package com.project.application.funnyroad.newroadtrip.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

        FragmentCreateNewRoadTrip fragmentCreateNewRoadTrip = new FragmentCreateNewRoadTrip();

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

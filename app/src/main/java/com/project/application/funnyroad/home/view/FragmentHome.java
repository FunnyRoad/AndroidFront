package com.project.application.funnyroad.home.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.home.allroadtrip.view.AllRoadTripFragment;
import com.project.application.funnyroad.home.roadtripfollow.view.FollowRoadTripFragment;
import com.project.application.funnyroad.home.roadtripsuggested.view.RoadTripSuggestedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oa on 23/02/2017.
 */

public class FragmentHome extends Fragment {

    @BindView(R.id.pager)
    public ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);

        /********** les tab layout **************/
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        AllRoadTripFragment mAllRoadFragment = new AllRoadTripFragment();
        RoadTripSuggestedFragment mRoadTripSuggestedFragment = new RoadTripSuggestedFragment();
        FollowRoadTripFragment mFollowRoadTripFragment = new FollowRoadTripFragment();

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, mAllRoadFragment)
                .add(R.id.content_frame, mRoadTripSuggestedFragment)
                .add(R.id.content_frame, mFollowRoadTripFragment)
                .commit();

        tabLayout.addTab(tabLayout.newTab().setText("Les roads trips"));
        tabLayout.addTab(tabLayout.newTab().setText("Roads trips recommandés"));
        tabLayout.addTab(tabLayout.newTab().setText("Road trips suivis"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}

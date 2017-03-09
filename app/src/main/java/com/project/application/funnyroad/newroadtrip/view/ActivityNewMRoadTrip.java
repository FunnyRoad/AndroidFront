package com.project.application.funnyroad.newroadtrip.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.LayoutCommonActivity;
import com.project.application.funnyroad.newroadtrip.filteroadtrip.view.FiltrePlacesFragment;
import com.project.application.funnyroad.newroadtrip.listroadtrip.view.view.ListPlacesFragment;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.view.VisualRoadTripFragment;

import butterknife.BindView;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class ActivityNewMRoadTrip extends LayoutCommonActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        ListPlacesFragment mListRoadFragment = new ListPlacesFragment();
        FiltrePlacesFragment filtreRoadFragment = new FiltrePlacesFragment();
        VisualRoadTripFragment visualRoadTripFragment = new VisualRoadTripFragment();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            visualRoadTripFragment.setArguments(bundle);

        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, mListRoadFragment)
                .add(R.id.container_fragment, filtreRoadFragment)
                .add(R.id.container_fragment , visualRoadTripFragment )
                .commit();

        tabLayout.addTab(tabLayout.newTab().setText("Liste"));
        tabLayout.addTab(tabLayout.newTab().setText("Filtre"));
        tabLayout.addTab(tabLayout.newTab().setText("Visuel"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
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

        if(bundle != null){
            viewPager.setCurrentItem(3);
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

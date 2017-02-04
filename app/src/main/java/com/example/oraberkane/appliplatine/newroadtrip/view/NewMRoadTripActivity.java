package com.example.oraberkane.appliplatine.newroadtrip.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.oraberkane.appliplatine.R;
import com.example.oraberkane.appliplatine.common.LayoutCommonActivity;
import com.example.oraberkane.appliplatine.newroadtrip.filtreroadtrip.view.FiltreRoadTripFragment;
import com.example.oraberkane.appliplatine.newroadtrip.listroad.view.ListRoadFragment;

import butterknife.BindView;

/**
 * Created by oraberkane on 03/02/2017.
 */

public class NewMRoadTripActivity extends LayoutCommonActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        ListRoadFragment mListRoadFragment = new ListRoadFragment();
        FiltreRoadTripFragment filtreRoadFragment = new FiltreRoadTripFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, mListRoadFragment)
                .add(R.id.container_fragment, filtreRoadFragment)
                .commit();

        tabLayout.addTab(tabLayout.newTab().setText("Liste"));
        tabLayout.addTab(tabLayout.newTab().setText("Filtre"));
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

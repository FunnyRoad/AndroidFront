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
import com.project.application.funnyroad.home.friends.view.FriendsFragment;
import com.project.application.funnyroad.home.roadtripsuggested.view.RoadTripSuggestedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by you on 23/02/2017.
 */

public class FragmentHome extends Fragment {

    /*@BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_layout)
    CoordinatorLayout mainLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;*/

    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this, view);


        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this.getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float moveFactor = (navigationView.getWidth() * slideOffset);

                mainLayout.setTranslationX(moveFactor);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);*/

        /********** les tab layout **************/
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        AllRoadTripFragment mAllRoadFragment = new AllRoadTripFragment();
        RoadTripSuggestedFragment mRoadTripSuggestedFragment = new RoadTripSuggestedFragment();
        FriendsFragment mFriendsFragment = new FriendsFragment();

        getFragmentManager()
                .beginTransaction()
                .add(R.id.content_frame, mAllRoadFragment)
                .add(R.id.content_frame, mRoadTripSuggestedFragment)
                .add(R.id.content_frame, mFriendsFragment )
                .commit();

        tabLayout.addTab(tabLayout.newTab().setText("Les roads trips"));
        tabLayout.addTab(tabLayout.newTab().setText("Roads trips recommandés"));
        tabLayout.addTab(tabLayout.newTab().setText("Amis"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TAG", "onTabSelected: "+tab.getPosition());
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


    /*@SuppressWarnings("StatementWithEmptyBody")
    @Override
    // 3.4 and 3.8
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_road_trip) {
            fragment = new RoadTripFragment();
        }
        else if (id == R.id.nav_profil) {
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            fragment = new ProfilFragment();
        } else if (id == R.id.nav_urgence) {
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            fragment = new UrgenceFragment();
        } else if (id == R.id.nav_photos) {
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            fragment = new PhotosFragment();
        } else if (id == R.id.nav_lieux) {
            tabLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
            fragment = new LieuxFragment();
        }
        if (fragment != null) {
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/


}

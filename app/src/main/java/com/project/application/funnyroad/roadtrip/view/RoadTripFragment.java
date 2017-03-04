package com.project.application.funnyroad.roadtrip.view;

/**
 * Created by sameur on 02/02/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.googlemap.view.view.ActivityNewRoaTripRouteChoice;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoadTripFragment extends Fragment {

    @BindView(R.id.layoutRoadTrips)
    TabLayout tabLayout;
    @BindView(R.id.pagerRoadTrips)
    ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Retourne votre fichier layout
        // Changer R.layout.yourlayoutfilename pour vos fragments
        View v = inflater.inflate(R.layout.road_trip_fragment, container, false);
        ButterKnife.bind(this,v);

        return v;
    }


    @OnClick(R.id.fab)
    public void goToNewRoad(){
        Intent intent = new Intent(getActivity(), ActivityNewRoaTripRouteChoice.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*//<<<<<<< HEAD

        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        LesRoadTripsFragment lesRoadTripsFragment = new LesRoadTripsFragment();
        LesRecommandesFragment lesRecommandesFragment = new LesRecommandesFragment();

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container_fragment, lesRoadTripsFragment)
                .add(R.id.container_fragment, lesRecommandesFragment)
                .commit();

        tabLayout.addTab(tabLayout.newTab().setText("RTS"));
        tabLayout.addTab(tabLayout.newTab().setText("Recommandés"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
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

        getActivity().setTitle("Road Trips");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }*/
//=======
        // Vous pouvez changer le titre dans la toolbar de vos differents fragments
        getActivity().setTitle("Road Trips");
    }
//>>>>>>> ItinéraireMap*/
}
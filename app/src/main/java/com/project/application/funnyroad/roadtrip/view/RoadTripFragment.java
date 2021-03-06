package com.project.application.funnyroad.roadtrip.view;

/**
 * Created by sameur on 02/02/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.googlemap.view.ActivityNewRoaTripRouteChoice;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoadTripFragment extends Fragment {
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
        // Vous pouvez changer le titre dans la toolbar de vos differents fragments
        getActivity().setTitle("Road Trips");
    }
}


/*
=======
package com.example.oraberkane.appliplatine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RoadTripFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Retourne votre fichier layout
        // Changer R.layout.yourlayoutfilename pour vos fragments
        return inflater.inflate(R.layout.road_trip_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Vous pouvez changer le titre dans la toolbar de vos differents fragments
        getActivity().setTitle("Road Trips");
    }
}
*/
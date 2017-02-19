package com.project.application.funnyroad.allroads.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.allroads.model.RoadTrip;
import com.project.application.funnyroad.allroads.presenter.AllRoadTripAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by you on 18/02/2017.
 */

public class AllRoadTripFragment extends Fragment {

    @BindView(R.id.recycler_view_all_road_trip)
    RecyclerView recycler_view_all_road_trip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.all_road_trip_fragment,container,false);

        ButterKnife.bind(this, view );

        RoadTrip roadTrip1 = new RoadTrip("lille" , "lyon" , "découvrir et s'amuser");
        RoadTrip roadTrip2 = new RoadTrip("lens" , "marseille" , "découvrir et s'amuser");
        RoadTrip roadTrip3 = new RoadTrip("sochaux" , "montpellier" , "découvrir et s'amuser");
        ArrayList<RoadTrip> listRoadTrip = new ArrayList<>();
        listRoadTrip.add(roadTrip1);listRoadTrip.add(roadTrip2);listRoadTrip.add(roadTrip3);

        AllRoadTripAdapter mAdapter = new AllRoadTripAdapter(listRoadTrip);
        recycler_view_all_road_trip.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_all_road_trip.setItemAnimator(new DefaultItemAnimator());
        recycler_view_all_road_trip.setAdapter(mAdapter);

        return view;
    }
}

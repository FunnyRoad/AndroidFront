/*package com.project.application.funnyroad.roadtrip.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.application.funnyroad.R;
import com.project.application.funnyroad.roadtrip.view.model.RoadTrip;
import com.project.application.funnyroad.roadtrip.view.presenter.LesRoadTripsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;*/

/**
 * Created by sameur on 12/02/2017.
 */
/*
public class  LesRoadTripsFragment extends Fragment {

    @BindView(R.id.roadtrips_recyclerView)
    RecyclerView roadTripsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_the_road_trips, container, false);

        ButterKnife.bind(this, view);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(200, 100, conf); // this creates a MUTABLE bitmap

        List<RoadTrip> listPhoto = new ArrayList<>();
        RoadTrip e1 = new RoadTrip();
        RoadTrip e2 = new RoadTrip();
        RoadTrip e3 = new RoadTrip();
        RoadTrip e4 = new RoadTrip();
        e1.setPhoto(bmp);e2.setPhoto(bmp);e3.setPhoto(bmp);e4.setPhoto(bmp);
        listPhoto.add(e1); listPhoto.add(e2); listPhoto.add(e3); listPhoto.add(e4);

        LesRoadTripsAdapter mAdapter = new LesRoadTripsAdapter(listPhoto);
        roadTripsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        roadTripsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        roadTripsRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
*/
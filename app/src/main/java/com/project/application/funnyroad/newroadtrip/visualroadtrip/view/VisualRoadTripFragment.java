package com.project.application.funnyroad.newroadtrip.visualroadtrip.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by oraberkane on 11/02/2017.
 */

public class VisualRoadTripFragment extends Fragment implements OnMapReadyCallback {


    private SupportMapFragment gMap;
    ArrayList<Place> listPlaces;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_visual_road,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        Log.d("TAG", "onCreateView: retour au fragment visualRoadTrip ");
        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.visualMap));

        Bundle bundle = getArguments();
        if(bundle != null) {
            listPlaces = bundle.getParcelableArrayList("listEndroitChecked");
            gMap.getMapAsync(this);
        }


        //myTripsOrganizedPresenter = new MyTripsOrganizedPresenter(this , getContext());
        //myTripsOrganizedPresenter.getTripsById();
        Log.d("TAG", "onCreateView: après le if" );
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
            Log.d("onMapReady", "onMapReady: bundle rempli"+"premier element: "+ listPlaces.get(0)+" deuxieme element: " + listPlaces.get(1));
            new ItineraireTask(this.getContext(), googleMap, listPlaces).execute();

    }
}

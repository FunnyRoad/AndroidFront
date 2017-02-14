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
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Endroit;

import butterknife.ButterKnife;

/**
 * Created by oraberkane on 11/02/2017.
 */

public class VisualRoadTripFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_visual_road,container,false);

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.visualMap));
        gMap.getMapAsync(this);

        //myTripsOrganizedPresenter = new MyTripsOrganizedPresenter(this , getContext());
        //myTripsOrganizedPresenter.getTripsById();

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Object[] listCities = new Intent().getStringArrayExtra("listCities");
        String[] listCitiesConvert = new String[3];
        listCitiesConvert[0] = "Lens"; listCitiesConvert[1] = "Paris";listCitiesConvert[2] = "Amiens";

        Endroit e1 = new Endroit( -6.8325500 , 34.0132500);
        Endroit e2 = new Endroit( -9.5981500,30.4201800);
        Endroit[] listendroit = new Endroit[2];
        listendroit[0] = e1; listendroit[1] = e2;

        new ItineraireTask(this.getContext(), googleMap, listendroit).execute();
    }
}

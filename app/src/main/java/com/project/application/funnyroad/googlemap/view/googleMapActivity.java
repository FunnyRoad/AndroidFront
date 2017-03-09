package com.project.application.funnyroad.googlemap.view;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 04/02/2017.
 */

public class googleMapActivity  extends Activity implements OnMapReadyCallback {

        private MapFragment gMap;
        String editDepart = "";
        String editArrivee = "";

        /**
         * {@inheritDoc}
         */
        @Override
        protected void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_map);

            //On récupère les composants graphiques
            gMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map));
            gMap.getMapAsync(this);

            //On récupère le départ et l'arrivée
            editDepart = getIntent().getStringExtra("DEPART");
            editArrivee = getIntent().getStringExtra("ARRIVEE");

            ButterKnife.bind(this);
        }


    @Override
    public void onMapReady(GoogleMap map) {
        new ItineraireTask(this, map, editDepart, editArrivee).execute();
    }



    @OnClick(R.id.submit)
    public void goToRegister() {
        Intent intent = new Intent(this, ActivityNewMRoadTrip.class);
        startActivity(intent);
    }
}

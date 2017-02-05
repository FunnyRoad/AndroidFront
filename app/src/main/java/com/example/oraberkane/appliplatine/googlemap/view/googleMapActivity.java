package com.example.oraberkane.appliplatine.googlemap.view;

import android.app.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.oraberkane.appliplatine.R;
import com.example.oraberkane.appliplatine.newroadtrip.view.ActivityNewMRoadTrip;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import butterknife.BindView;
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

            //Appel de la méthode asynchrone
            //new ItineraireTask(this, gMap, editDepart, editArrivee).execute();
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

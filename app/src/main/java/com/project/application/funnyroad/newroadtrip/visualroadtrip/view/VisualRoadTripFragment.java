package com.project.application.funnyroad.newroadtrip.visualroadtrip.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Endroit;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by oraberkane on 11/02/2017.
 */

public class VisualRoadTripFragment extends Fragment implements OnMapReadyCallback {


    private SupportMapFragment gMap;
    static int i = 0;
    ArrayList<Endroit> listEndroits;
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
            listEndroits = bundle.getParcelableArrayList("listEndroitChecked");
            gMap.getMapAsync(this);
        }


        //myTripsOrganizedPresenter = new MyTripsOrganizedPresenter(this , getContext());
        //myTripsOrganizedPresenter.getTripsById();
        Log.d("TAG", "onCreateView: après le if" );
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*ArrayList<Endroit> l = new ArrayList<>();
        Endroit e1 = new Endroit("Tour effeil" ,"un truc de fou cet endroit" , 48.8584 , 2.2945);
        Endroit e2 = new Endroit("Palais beaux arts" ," le monde aux années 80" , 48.866667 , 2.333333);
        Endroit e3 = new Endroit("Parc asterix" ," joli endroit", 50.62925 , 3.057256000000052);

        l.add(e1);l.add(e2);
        new ItineraireTask(this.getContext(), googleMap, l).execute();
    */

            Log.d("onMapReady", "onMapReady: bundle rempli"+"premier element: "+listEndroits.get(0)+" deuxieme element: " + listEndroits.get(1));
            new ItineraireTask(this.getContext(), googleMap, listEndroits).execute();

    }
}

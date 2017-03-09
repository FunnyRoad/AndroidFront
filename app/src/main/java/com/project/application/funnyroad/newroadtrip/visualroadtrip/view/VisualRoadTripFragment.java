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
/*<<<<<<< HEAD
=======*/

import com.project.application.funnyroad.googlemap.view.view.ItineraireTask;
import com.project.application.funnyroad.newroadtrip.Variable;
//>>>>>>> workAPI
import com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by oraberkane on 11/02/2017.
 */

public class VisualRoadTripFragment extends Fragment implements OnMapReadyCallback {

    private Variable variables;
    private SupportMapFragment gMap;
/*<<<<<<< HEAD
    ArrayList<Place> listPlaces;
=======*/
    static int i = 0;

    ArrayList<Place> listPlacesChecked;
//>>>>>>> workAPI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_visual_road,container,false);

        variables = (Variable) getActivity().getApplicationContext();

        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this,view);

        Log.d("TAG", "onCreateView: retour au fragment visualRoadTrip ");
        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.visualMap));

        Bundle bundle = getArguments();
/*<<<<<<< HEAD
        if(bundle != null) {
            listPlaces = bundle.getParcelableArrayList("listEndroitChecked");
            gMap.getMapAsync(this);
        }
=======*/
        //if(bundle != null) {
        //    listPlacesChecked = bundle.getParcelableArrayList("listPlacesChecked");
        gMap.getMapAsync(this);
        //}
//>>>>>>> workAPI


        //myTripsOrganizedPresenter = new MyTripsOrganizedPresenter(this , getContext());
        //myTripsOrganizedPresenter.getTripsById();
        Log.d("TAG", "onCreateView: après le if" );

        //new ItineraireTask(this.getContext(), variables.getMap());

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
/*<<<<<<< HEAD
            Log.d("onMapReady", "onMapReady: bundle rempli"+"premier element: "+ listPlaces.get(0)+" deuxieme element: " + listPlaces.get(1));
            new ItineraireTask(this.getContext(), googleMap, listPlaces).execute();

=======*/
        /*ArrayList<CustomPlace> l = new ArrayList<>();
        CustomPlace e1 = new CustomPlace("Tour effeil" ,"un truc de fou cet endroit" , 48.8584 , 2.2945);
        CustomPlace e2 = new CustomPlace("Palais beaux arts" ," le monde aux années 80" , 48.866667 , 2.333333);
        CustomPlace e3 = new CustomPlace("Parc asterix" ," joli endroit", 50.62925 , 3.057256000000052);

        l.add(e1);l.add(e2);
        new ItineraireTask(this.getContext(), googleMap, l).execute();
    */
//        Log.d("onMapReady", "onMapReady: bundle rempli"+"premier element: "+listPlacesChecked.get(0)+" deuxieme element: " + listPlacesChecked.get(1));
        //gMap = variables.getMap();
        //gMap.
        //gMap.getMapAsync(this);
        //new ItineraireTask(getActivity(), googleMap, variables.getPlaceDeparture().getName().toString(),variables.getPlaceArrival().toString()).execute();
//>>>>>>> workAPI
    }
}

package com.project.application.funnyroad.googlemap.view.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//<<<<<<< HEAD:app/src/main/java/com/project/application/funnyroad/googlemap/view/FragmentRouteChoice.java
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
/*=======
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
>>>>>>> ItinéraireMap:app/src/main/java/com/project/application/funnyroad/googlemap/view/view/FragmentRouteChoice.java*/
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.googlemap.view.AutoCompleteAdapter;
import com.project.application.funnyroad.googlemap.view.AutoCompletePlace;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 04/02/2017.
 */

//<<<<<<< HEAD:app/src/main/java/com/project/application/funnyroad/googlemap/view/FragmentRouteChoice.java
public class FragmentRouteChoice extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
/*=======
public class FragmentRouteChoice extends Fragment implements OnMapReadyCallback {
>>>>>>> ItinéraireMap:app/src/main/java/com/project/application/funnyroad/googlemap/view/view/FragmentRouteChoice.java*/


   /* private EditText editDepart;
    private EditText editArrivee;
    private Button btnSearch;
*/

    /****************ATTRIBUTS*******************/
  //  @BindView(R.id.editDepart)
//<<<<<<< HEAD:app/src/main/java/com/project/application/funnyroad/googlemap/view/FragmentRouteChoice.java
    public AutoCompleteTextView meditDepart;

    @BindView(R.id.editArrivee)
    public AutoCompleteTextView meditArrivee;
/*=======
    public EditText meditDepart;
    @BindView(R.id.editArrivee)
    public EditText meditArrivee;
    @BindView(R.id.submit)
    Button buttonSubmit;*/

    @BindView(R.id.editTextNameRoadTrip)
    EditText editTextNameRoadTrip;
/*
    private SupportMapFragment gMap;
>>>>>>> ItinéraireMap:app/src/main/java/com/project/application/funnyroad/googlemap/view/view/FragmentRouteChoice.java*/

    @BindView(R.id.submit)
    Button buttonSubmit;

    private SupportMapFragment gMap;

    private GoogleApiClient mGoogleApiClient;

    private AutoCompleteAdapter autoCompleteAdapter;

    //On récupère les composants graphiques
 /*   editDepart = (EditText) findViewById(R.id.editDepart);
    editArrivee = (EditText) findViewById(R.id.editArrivee);
    btnRechercher = (Button) findViewById(R.id.btnSearch);*/



    /***********************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_routechoice, container, false);
        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

//<<<<<<< HEAD:app/src/main/java/com/project/application/funnyroad/googlemap/view/FragmentRouteChoice.java
        autoCompleteAdapter = new AutoCompleteAdapter(this.getActivity());

        meditDepart.setAdapter(autoCompleteAdapter);
        meditArrivee.setAdapter(autoCompleteAdapter);

        meditDepart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutoCompletePlace place = (AutoCompletePlace) parent.getItemAtPosition(position);
                meditDepart.setText(place.getDescription());
            }
        });

        meditArrivee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutoCompletePlace place = (AutoCompletePlace) parent.getItemAtPosition(position);
                meditArrivee.setText(place.getDescription());
            }
        });

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .enableAutoManage(this.getActivity(), 0, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

/*=======
>>>>>>> ItinéraireMap:app/src/main/java/com/project/application/funnyroad/googlemap/view/view/FragmentRouteChoice.java*/
        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.choiceMap));

        //mPresenterLogin = new PresenterLogin(this);


        return view;
    }


    /****************CLIC QUR LE BOUTON CONNEXION*******************/
    @OnClick(R.id.btnSearch)
   // btnRechercher.setOnClickListener(new OnClickListener() {

         //{@inheritDoc}

       // @Override
       // public void onClick(final View v) {
       public void goToMap() {
            if("".equals(meditDepart.getText().toString().trim())) {
                Toast.makeText(getActivity(), "Merci de saisir un lieu de départ", Toast.LENGTH_SHORT).show();
            }
            else if("".equals(meditArrivee.getText().toString().trim())) {
                Toast.makeText(getActivity(), "Merci de saisir un lieu d'arrivée", Toast.LENGTH_SHORT).show();
            }
            else if ("".equals(editTextNameRoadTrip.getText().toString().trim())) {
                Toast.makeText(getActivity(), "Merci de saisir un nom du roadtrip", Toast.LENGTH_SHORT).show();
            }
            else {
                buttonSubmit.setVisibility(View.VISIBLE);
                gMap.getMapAsync(this);
//<<<<<<< HEAD:app/src/main/java/com/project/application/funnyroad/googlemap/view/FragmentRouteChoice.java
                //On transmet les données à l'activité suivante
                /*final Intent intent = new Intent(getActivity(), googleMapActivity.class);
                intent.putExtra("DEPART", meditDepart.getText().toString().trim());
                intent.putExtra("ARRIVEE", meditArrivee.getText().toString().trim());

                getActivity().startActivity(intent);
                */
/*=======
>>>>>>> ItinéraireMap:app/src/main/java/com/project/application/funnyroad/googlemap/view/view/FragmentRouteChoice.java*/
            }
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        new ItineraireTask(getContext(), googleMap, meditDepart.getText().toString()
                , meditArrivee.getText().toString()).execute();

    }

    @OnClick(R.id.submit)
//<<<<<<< HEAD:app/src/main/java/com/project/application/funnyroad/googlemap/view/FragmentRouteChoice.java
    public void goToRegister() {
        Intent intent = new Intent(getActivity(), ActivityNewMRoadTrip.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {

            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        if(autoCompleteAdapter != null)
            autoCompleteAdapter.setGoogleApiClient(mGoogleApiClient);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onConnectionSuspended(int n) {
    }
/*=======
    public void goToNewRoadTrip() {
        Intent intent = new Intent(getActivity(), ActivityNewMRoadTrip.class);
        intent.putExtra("DEPARTURE", meditDepart.getText().toString());
        intent.putExtra("ARRIVAL", meditArrivee.getText().toString());
        intent.putExtra("NAMEROADTRIP", editTextNameRoadTrip.getText().toString());
        startActivity(intent);
    }
>>>>>>> ItinéraireMap:app/src/main/java/com/project/application/funnyroad/googlemap/view/view/FragmentRouteChoice.java*/
}

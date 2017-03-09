package com.project.application.funnyroad.googlemap.view.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.detailroadtripnew.view.AutoCompleteAdapter;
import com.project.application.funnyroad.detailroadtripnew.view.AutoCompletePlace;
import com.project.application.funnyroad.newroadtrip.Variable;
import com.project.application.funnyroad.newroadtrip.view.ActivityNewMRoadTrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by oraberkane on 04/02/2017.
 */

public class FragmentRouteChoice extends Fragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    //ATTRIBUTES

    @BindView(R.id.editDepart)
    public AutoCompleteTextView meditDepart;

    @BindView(R.id.editArrivee)
    public AutoCompleteTextView meditArrivee;

    @BindView(R.id.submit)
    Button buttonSubmit;

    private SupportMapFragment gMap;

    private GoogleApiClient mGoogleApiClient;

    private AutoCompleteAdapter autoCompleteAdapter;

    private Place placeDeparture;

    private Place placeArrival;

    private Variable variables;

    //METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // On récupère la view du fragment
        View view = inflater.inflate(R.layout.fragment_routechoice, container, false);
        // On bind la view du fragment pour l'utiliser avec ButterKnife.
        ButterKnife.bind(this, view);

        mGoogleApiClient = new GoogleApiClient
                .Builder(getActivity())
                .enableAutoManage(getActivity(), 0, this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        variables = (Variable) getActivity().getApplicationContext();

        variables.setGoogleApiClient(mGoogleApiClient);

        autoCompleteAdapter = new AutoCompleteAdapter(this.getActivity());

        meditDepart.setAdapter(autoCompleteAdapter);
        meditArrivee.setAdapter(autoCompleteAdapter);

        meditDepart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meditDepart.setText("");
                AutoCompletePlace place = (AutoCompletePlace) parent.getItemAtPosition(position);
                meditDepart.setText(place.getDescription());
                PendingResult<PlaceBuffer> placeTemp = Places.GeoDataApi.getPlaceById(mGoogleApiClient, place.getId());
                placeTemp.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        if (!places.getStatus().isSuccess()) {
                            return;
                        }
                        placeDeparture = places.get(0);
                        variables.setPlaceDeparture(placeDeparture);
                        //meditDepart.clearFocus();
                        variables.hideSoftKeyboard(getActivity());
                    }
                });
            }
        });

        meditArrivee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meditArrivee.setText("");
                AutoCompletePlace place = (AutoCompletePlace) parent.getItemAtPosition(position);
                meditArrivee.setText(place.getDescription());
                PendingResult<PlaceBuffer> placeTemp = Places.GeoDataApi.getPlaceById(mGoogleApiClient, place.getId());
                placeTemp.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        if (!places.getStatus().isSuccess()) {
                            return;
                        }
                        placeArrival = places.get(0);
                        variables.setPlaceArrival(placeArrival);
                        //meditArrivee.clearFocus();
                        variables.hideSoftKeyboard(getActivity());
                    }
                });
            }
        });

        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.choiceMap));

        return view;
    }


    /****************CLIC QUR LE BOUTON CONNEXION*******************/
    @OnClick(R.id.btnSearch)
    public void goToMap() {
        if("".equals(meditDepart.getText().toString().trim())) {
            Toast.makeText(getActivity(), "Merci de saisir un lieu de départ", Toast.LENGTH_SHORT).show();
        }
        else if("".equals(meditArrivee.getText().toString().trim())) {
            Toast.makeText(getActivity(), "Merci de saisir un lieu d'arrivée", Toast.LENGTH_SHORT).show();
        }
        else {
            buttonSubmit.setVisibility(View.VISIBLE);
            gMap.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        new ItineraireTask(getActivity(), googleMap, meditDepart.getText().toString()
                , meditArrivee.getText().toString()).execute();

    }

    @OnClick(R.id.submit)
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
}

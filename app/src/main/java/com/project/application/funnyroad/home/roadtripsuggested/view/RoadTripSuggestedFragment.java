package com.project.application.funnyroad.home.roadtripsuggested.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.Places;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.common.UtilityCheckPermissionGPS;
import com.project.application.funnyroad.home.allroadtrip.presenter.AllRoadTripAdapter;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.home.roadtripsuggested.presenter.PresenterRoadTripSuggested;
import com.project.application.funnyroad.home.roadtripsuggested.presenter.RoadTripSuggestedAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oa on 23/02/2017.
 */

public class RoadTripSuggestedFragment extends Fragment implements IServiceRoadTripSuggested ,GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks  {


    @BindView(R.id.recyclerViewRoadTripSuggested)
    RecyclerView recyclerViewRoadTripSuggested;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.textViewListEmpty)
    TextView textViewListEmpty;

    private GoogleApiClient mGoogleApiClient;
    private static final int PERMISSION_REQUEST_CODE = 100;

    private PresenterRoadTripSuggested presenterRoadTripSuggested;
    private double latitudeUser;
    private double longitudeUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.all_road_trip_suggested_fragment,container,false);

        ButterKnife.bind(this, view );

        Log.d("RoadSuggest", "onCreateView: ");

        presenterRoadTripSuggested = new PresenterRoadTripSuggested(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this.getActivity())
                .addApi(Places.PLACE_DETECTION_API)
                .addApi(Places.GEO_DATA_API)
                .addConnectionCallbacks(this)
                .build();

        if (mGoogleApiClient.isConnected()){
            Log.d("RoadTripSuggest", "onCreateView: dans le if");
            if (ContextCompat.checkSelfPermission(this.getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this.getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_CODE);
            } else {
                Log.d("RoadTripSuggest", "onCreateView: dans le else");
                callPlaceDetectionApi();
            }
        }
        else{
            Log.d("RoadTripSuggest", "onCreateView: dans le grand else");
            callPlaceDetectionApi();
        }

        /*RoadTrip roadTrip1 = new RoadTrip("lille" , "lyon" , "découvrir et s'amuser");
        RoadTrip roadTrip2 = new RoadTrip("lens" , "marseille" , "découvrir et s'amuser");
        RoadTrip roadTrip3 = new RoadTrip("sochaux" , "montpellier" , "découvrir et s'amuser");
        ArrayList<RoadTrip> listRoadTrip = new ArrayList<>();
        listRoadTrip.add(roadTrip1);listRoadTrip.add(roadTrip2);listRoadTrip.add(roadTrip3);

        AllRoadTripAdapter mAdapter = new AllRoadTripAdapter(listRoadTrip);
        recycler_view_all_road_trip.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_all_road_trip.setItemAnimator(new DefaultItemAnimator());
        recycler_view_all_road_trip.setAdapter(mAdapter);
        */

        return view;
    }

    @Override
    public void showLoading(boolean bool) {
        if(bool) {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void getAllRoadTripSuggested(ArrayList<RoadTrip> listRoadTrip) {
        RoadTripSuggestedAdapter mAdapter = new RoadTripSuggestedAdapter(listRoadTrip , getActivity(), mGoogleApiClient);
        recyclerViewRoadTripSuggested.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewRoadTripSuggested.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRoadTripSuggested.setAdapter(mAdapter);
    }

    @Override
    public void listRoadTripEmpty() {
        textViewListEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadingListError(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("Fermer" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /******find position ****************/

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("RoadTRIPSUGGEST", "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(getActivity(), "Google Places API connection failed with error code:" + connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d("RoadTripSuggest", "onCreateView: dans le onRequestPermission");
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPlaceDetectionApi();
                }
                break;
        }
    }

    private void callPlaceDetectionApi() throws SecurityException {
        Log.d("RoadTripSuggest", "onCreateView: dans la fonction callPlaceDetection");
        mGoogleApiClient.connect();
        PendingResult<PlaceLikelihoodBuffer> result = Places.PlaceDetectionApi
                .getCurrentPlace(mGoogleApiClient, null);
        result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {
            @Override
            public void onResult(PlaceLikelihoodBuffer likelyPlaces) {
                Log.d("RoadTripSuggest", "onResult: " +likelyPlaces.get(0).getPlace().getName() +" " + likelyPlaces.get(1).getPlace().getName() );
                latitudeUser = likelyPlaces.get(0).getPlace().getLatLng().latitude;
                longitudeUser = likelyPlaces.get(0).getPlace().getLatLng().longitude;
                Log.d("RoadTripSuggest", "onCreateView: " + latitudeUser +" " +longitudeUser );
                presenterRoadTripSuggested.getAllRoadsTripByCity(Utility.getIdUser(getActivity()),latitudeUser , longitudeUser , 250 );
                likelyPlaces.release();
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onStart() {
        if( ! mGoogleApiClient.isConnected()){
            Log.d("AllRoadTripFragment", "onStart: googleapi pas connecté");
            mGoogleApiClient.connect();
        }
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}

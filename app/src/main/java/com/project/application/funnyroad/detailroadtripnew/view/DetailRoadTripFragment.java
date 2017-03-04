package com.project.application.funnyroad.detailroadtripnew.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.project.application.funnyroad.R;
import com.project.application.funnyroad.addplace.view.AddPlaceActivity;
import com.project.application.funnyroad.common.Utility;
import com.project.application.funnyroad.detailroadtripnew.presenter.GeocodeInverse;
import com.project.application.funnyroad.detailroadtripnew.presenter.PresenterDetailRoadTrip;
import com.project.application.funnyroad.home.model.RoadTrip;
import com.project.application.funnyroad.detailroadtripnew.presenter.ItineraireTask;
import com.project.application.funnyroad.detailroadtripnew.presenter.ListEndroitAdapter;
import com.project.application.funnyroad.home.view.ActivityHome2;
import com.project.application.funnyroad.detailroadtripnew.presenter.DetailsRoadTripAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by you on 18/02/2017.
 */

public class DetailRoadTripFragment extends Fragment implements OnMapReadyCallback, IServiceDetailRoadTrip,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    @BindView(R.id.textViewDetailDepart)
    EditText textViewDetailDepart;
    @BindView(R.id.textViewDetailDestination)
    EditText textViewDetailDestination;
    @BindView(R.id.textViewDetailDescription)
    EditText textViewDetailDescription;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    //@BindView(R.id.textViewListPhotoEmpty)
    //TextView textViewListPhotoEmpty;
    @BindView(R.id.textViewListPlaceEmpty)
    TextView textViewListPlaceEmpty;
    @BindView(R.id.butDeleteRoadTrip)
    Button butDeleteRoadTrip;
    @BindView(R.id.butUpload)
    Button butUpload;
    //@BindView(R.id.details_road_recyclerView)  // recycler view des photos dun road trip
    //RecyclerView details_road_recyclerView;
    @BindView(R.id.recycler_view_list_endroit)
    RecyclerView recycler_view_list_endroit;

    RoadTrip roadTrip;
    private SupportMapFragment gMap;
    PresenterDetailRoadTrip presenterDetailRoadTrip;
    private GoogleApiClient mGoogleApiClient;
    private GeocodeInverse geocodeInverse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_detail_road_trip, container, false);

        ButterKnife.bind(this, view);

        presenterDetailRoadTrip = new PresenterDetailRoadTrip(this);

        Intent intent = getActivity().getIntent();
        if (intent != null){
            roadTrip = (RoadTrip) intent.getSerializableExtra("roadTripSelected");
            Geocoder geocoder = new Geocoder(this.getActivity());
            try {
                if(roadTrip.getBegin() != null) {
                    //Ici on récupère la premiere adresse trouvée grace à la position que l'on a récupéré
                    List<Address> adresses = geocoder.getFromLocation(roadTrip.getBegin().getLatitude(), roadTrip.getBegin().getLongitude(), 1);
                    if (adresses != null && adresses.size() == 1) {
                        Address adresse = adresses.get(0);
                        //Si le geocoder a trouver une adresse, alors on l'affiche
                        textViewDetailDepart.setText(adresse.getLocality());
                    } else {
                        //sinon on affiche un message d'erreur
                        textViewDetailDepart.setText("L'adresse n'a pu être déterminée");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                textViewDetailDepart.setText("L'adresse n'a pu être déterminée");
            }
            //textViewDetailDepart.setText(roadTrip.getBegin());
            //textViewDetailDestination.setText(roadTrip.getDestination());
            textViewDetailDescription.setText(roadTrip.getName());
            // si on est pas proprietaire du road trip on desactive les editText
            if(roadTrip.getId() != Utility.getIdUser(getActivity())){
                textViewDetailDepart.setEnabled(false);
                textViewDetailDestination.setEnabled(false);
                textViewDetailDescription.setEnabled(false);
            }
            else if (roadTrip.getId() == Utility.getIdUser(getActivity())){
                butUpload.setVisibility(View.VISIBLE);
                butDeleteRoadTrip.setVisibility(View.VISIBLE);
            }

            presenterDetailRoadTrip.getAllPlacesByRoadId(roadTrip.getId());

        }

        gMap = ((SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.mapRoadTrip));
        gMap.getMapAsync(this);

        ((WorkaroundMapFragment) this.getChildFragmentManager().findFragmentById(R.id.mapRoadTrip)).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });

        return view;
    }

    @OnClick(R.id.addPlace)
    public void goToAddPlace(){
        Intent intent = new Intent(this.getContext() , AddPlaceActivity.class);
        intent.putExtra("roadTripId" , roadTrip.getId());
        startActivity(intent);
    }

    @OnClick(R.id.butDeleteRoadTrip)
    public void deleteRoadTrip(){
        presenterDetailRoadTrip.deleteRoadTrip(roadTrip.getId());
    }

    private ArrayList<com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place> listPlace;

    @OnClick(R.id.butUpload)
    public void uploadRoadTripInformation(){
        textViewDetailDestination.setEnabled(false);

        RoadTrip newRoadTrip = new RoadTrip(roadTrip.getId() , textViewDetailDescription.getText().toString(),
                roadTrip.getBegin() , textViewDetailDestination.getText().toString() );

        presenterDetailRoadTrip.updateRoadTrip(newRoadTrip);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        new GeocodeInverse(this.getContext(), googleMap, listPlace,
               roadTrip.getBegin(), roadTrip.getDestination() , textViewDetailDestination).execute();
    }

    @Override
    public void showLoading(boolean bool) {
        if(bool){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void getListPlacesSuccess(ArrayList<com.project.application.funnyroad.newroadtrip.visualroadtrip.model.Place> places){
        listPlace = places;
        ListEndroitAdapter mListEndroitAdapter = new ListEndroitAdapter(places);
        recycler_view_list_endroit.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view_list_endroit.setItemAnimator(new DefaultItemAnimator());
        recycler_view_list_endroit.setAdapter(mListEndroitAdapter);
    }


    @Override
    public void getListFailed(String msg) {
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

    @Override
    public void listPlacesEmpty() {
        textViewListPlaceEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToListRoadTrip() {
        Intent intent = new Intent(getActivity() , ActivityHome2.class);
        startActivity(intent);
    }

    @Override
    public void deleteRoadTripFailed(String msg) {
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

    @Override
    public void getInformationRoadTrip(RoadTrip roadTrip) {
        textViewDetailDepart.setText(roadTrip.getBegin().getLatitude()+" "+roadTrip.getBegin().getLongitude());
        textViewDetailDestination.setText(roadTrip.getDestination());
        textViewDetailDescription.setText(roadTrip.getName());
        // si on est pas proprietaire du road trip on desactive les editText
        if(roadTrip.getId() != Utility.getIdUser(getActivity())){
            textViewDetailDepart.setEnabled(false);
            textViewDetailDestination.setEnabled(false);
            textViewDetailDescription.setEnabled(false);
        }
        else if (roadTrip.getId() != Utility.getIdUser(getActivity())){
            butDeleteRoadTrip.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(connectionResult.getErrorMessage());
        builder.setCancelable(true);
        builder.setPositiveButton("Réessayer" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mGoogleApiClient.connect();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        //mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }


}
